package com.firstbase.codetest;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstbase.codetest.model.dto.EmployeeCreateRequest;

/**
 * Test cases for validating REST APIs.
 */
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class CodetestApplicationTests {

    /** Base URI for employees */
    private static final String EMPLOYEES_BASE_URI = "/api/employees";

    /** Used to map Java objects to JSON */
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test search on empty database.
     * 
     * @throws Exception
     */
    @Test
    void testEmptyDatabase() throws Exception {
	getMockMvc().perform(MockMvcRequestBuilders.get(EMPLOYEES_BASE_URI))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    /**
     * Test adding an employee.
     * 
     * @throws Exception
     */
    @Test
    void testAddEmployee() throws Exception {
	EmployeeCreateRequest request = new EmployeeCreateRequest();
	request.setFirstName("Derek");
	request.setLastName("Adams");
	request.setJobTitle("Engineer");
	request.setPictureUrl("http://pictures.com/derekadams");
	String json = OBJECT_MAPPER.writeValueAsString(request);

	getMockMvc().perform(
		MockMvcRequestBuilders.post(EMPLOYEES_BASE_URI).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	getMockMvc().perform(MockMvcRequestBuilders.get(EMPLOYEES_BASE_URI))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
    }

    /**
     * Test getting a 400 error if attempting to add employee with no job title.
     * 
     * @throws Exception
     */
    @Test
    void testAddEmployeeNoJobTitle() throws Exception {
	EmployeeCreateRequest request = new EmployeeCreateRequest();
	request.setFirstName("Should");
	request.setLastName("Fail");
	request.setPictureUrl("http://pictures.com/failed");
	String json = OBJECT_MAPPER.writeValueAsString(request);

	getMockMvc().perform(
		MockMvcRequestBuilders.post(EMPLOYEES_BASE_URI).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Add three employees and verify search by surname and job title.
     * 
     * @throws Exception
     */
    @Test
    void testSearchBySurnameAndJobTitle() throws Exception {
	EmployeeCreateRequest request = new EmployeeCreateRequest();
	request.setFirstName("Derek");
	request.setLastName("Adams");
	request.setJobTitle("Engineer");
	request.setPictureUrl("http://pictures.com/derekadams");
	String json = OBJECT_MAPPER.writeValueAsString(request);
	getMockMvc().perform(
		MockMvcRequestBuilders.post(EMPLOYEES_BASE_URI).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());

	request = new EmployeeCreateRequest();
	request.setFirstName("Zippy");
	request.setLastName("Zebra");
	request.setJobTitle("Herd Animal");
	request.setPictureUrl("http://pictures.com/zz");
	json = OBJECT_MAPPER.writeValueAsString(request);
	getMockMvc().perform(
		MockMvcRequestBuilders.post(EMPLOYEES_BASE_URI).content(json).contentType(MediaType.APPLICATION_JSON));

	request = new EmployeeCreateRequest();
	request.setFirstName("William");
	request.setLastName("Smith");
	request.setJobTitle("Zookeeper");
	request.setPictureUrl("http://pictures.com/willsmith");
	json = OBJECT_MAPPER.writeValueAsString(request);
	getMockMvc().perform(
		MockMvcRequestBuilders.post(EMPLOYEES_BASE_URI).content(json).contentType(MediaType.APPLICATION_JSON));

	// Test search by surname.
	getMockMvc().perform(MockMvcRequestBuilders.get(EMPLOYEES_BASE_URI + "?order=surname"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[2].lastName", Matchers.is("Zebra")));

	// Test search by job title.
	getMockMvc().perform(MockMvcRequestBuilders.get(EMPLOYEES_BASE_URI + "?order=title"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[2].jobTitle", Matchers.is("Zookeeper")));
    }

    protected MockMvc getMockMvc() {
	return mockMvc;
    }
}