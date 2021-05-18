package com.firstbase.codetest.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.firstbase.codetest.model.Employee;
import com.firstbase.codetest.service.EmployeeService;
import com.firstbase.codetest.service.EmployeeSort;

/**
 * GraphQL query resolver implementation.
 */
@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private EmployeeService employeeService;

    /**
     * List employees that match the given criteria.
     * 
     * @param page
     * @param pageSize
     * @return
     */
    public List<Employee> getEmployees(int page, int pageSize) {
	Pageable paging = PageRequest.of(page, pageSize);
	return getEmployeeService().searchEmployees(EmployeeSort.SURNAME, paging);
    }

    protected EmployeeService getEmployeeService() {
	return employeeService;
    }
}
