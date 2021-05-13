package com.firstbase.codetest.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.firstbase.codetest.model.Employee;
import com.firstbase.codetest.model.dto.EmployeeCreateRequest;
import com.firstbase.codetest.model.dto.EmployeeUpdateRequest;

/**
 * Service interface for {@link Employee} model functionality.
 */
public interface EmployeeService {

    /**
     * Create a new employee.
     * 
     * @param request
     * @return
     */
    Employee createEmployee(EmployeeCreateRequest request);

    /**
     * Update an existing employee.
     * 
     * @param id
     * @param request
     * @return
     */
    Employee updateEmployee(Long id, EmployeeUpdateRequest request);

    /**
     * Search employees and apply the given sort order.
     * 
     * @param sort
     * @param pageable
     * @return
     */
    List<Employee> searchEmployees(EmployeeSort sort, Pageable pageable);

    /**
     * Bootstrap a given number of employees by calling an external service to
     * generate test data.
     * 
     * @param count
     * @return
     */
    List<Employee> bootstrapEmployees(int count);
}
