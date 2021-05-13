package com.firstbase.codetest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firstbase.codetest.model.Employee;
import com.firstbase.codetest.model.dto.EmployeeCreateRequest;
import com.firstbase.codetest.model.dto.EmployeeUpdateRequest;
import com.firstbase.codetest.service.EmployeeService;
import com.firstbase.codetest.service.EmployeeSort;

/**
 * REST controller providing APIs for {@link Employee} operations.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Create a new employee.
     * 
     * @param request
     * @return
     */
    @PostMapping
    public Employee createEmployee(@Valid @RequestBody EmployeeCreateRequest request) {
	return getEmployeeService().createEmployee(request);
    }

    /**
     * Update an existing employee with the given id.
     * 
     * @param employeeId
     * @param request
     * @return
     */
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable long employeeId, @Valid @RequestBody EmployeeUpdateRequest request) {
	return getEmployeeService().updateEmployee(employeeId, request);
    }

    /**
     * Search the list of employees, optionally providing a sort order.
     * 
     * @param order
     * @return
     */
    @GetMapping
    public List<Employee> searchEmployees(@RequestParam(required = false) String order,
	    @RequestParam(required = false, defaultValue = "0") int page,
	    @RequestParam(required = false, defaultValue = "10") int pageSize) {
	Pageable paging = PageRequest.of(page, pageSize);
	if (order == null) {
	    return getEmployeeService().searchEmployees(null, paging);
	} else if ("surname".equals(order)) {
	    return getEmployeeService().searchEmployees(EmployeeSort.SURNAME, paging);
	} else if ("title".equals(order)) {
	    return getEmployeeService().searchEmployees(EmployeeSort.JOB_TITLE, paging);
	} else {
	    return getEmployeeService().searchEmployees(null, paging);
	}
    }

    /**
     * Bootstrap a given number of employees from random data.
     * 
     * @param count
     * @return
     */
    @PostMapping("/bootstrap")
    public List<Employee> bootstrapEmployees(@RequestParam(required = false) Integer count) {
	int numUsers = count != null ? count : 10;
	return getEmployeeService().bootstrapEmployees(numUsers);
    }

    protected EmployeeService getEmployeeService() {
	return employeeService;
    }
}
