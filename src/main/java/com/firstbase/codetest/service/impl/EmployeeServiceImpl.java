package com.firstbase.codetest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.firstbase.codetest.model.Employee;
import com.firstbase.codetest.model.dto.EmployeeCreateRequest;
import com.firstbase.codetest.model.dto.EmployeeUpdateRequest;
import com.firstbase.codetest.model.dto.RandomUser;
import com.firstbase.codetest.repository.EmployeeRepository;
import com.firstbase.codetest.service.EmployeeService;
import com.firstbase.codetest.service.EmployeeSort;
import com.firstbase.codetest.service.RandomUserService;

/**
 * Implementation of {@link EmployeeService} service interface.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RandomUserService randomUserService;

    /*
     * @see
     * com.firstbase.codetest.service.EmployeeService#createEmployee(com.firstbase.
     * codetest.model.dto.EmployeeCreateRequest)
     */
    @Override
    public Employee createEmployee(EmployeeCreateRequest request) {
	Employee employee = new Employee();
	employee.setFirstName(request.getFirstName());
	employee.setLastName(request.getLastName());
	employee.setJobTitle(request.getJobTitle());
	employee.setPictureUrl(request.getPictureUrl());
	return getEmployeeRepository().save(employee);
    }

    /*
     * @see
     * com.firstbase.codetest.service.EmployeeService#updateEmployee(java.lang.Long,
     * com.firstbase.codetest.model.dto.EmployeeUpdateRequest)
     */
    @Override
    public Employee updateEmployee(Long id, EmployeeUpdateRequest request) {
	Optional<Employee> result = getEmployeeRepository().findById(id);
	if (result.isPresent()) {
	    Employee employee = result.get();
	    employee.setFirstName(request.getFirstName());
	    employee.setLastName(request.getLastName());
	    employee.setJobTitle(request.getJobTitle());
	    employee.setPictureUrl(request.getPictureUrl());
	    return getEmployeeRepository().save(employee);
	} else {
	    return null;
	}
    }

    /*
     * @see
     * com.firstbase.codetest.service.EmployeeService#searchEmployees(com.firstbase.
     * codetest.service.EmployeeSort, org.springframework.data.domain.Pageable)
     */
    @Override
    public List<Employee> searchEmployees(EmployeeSort sort, Pageable pageable) {
	// Could also use findAll with a Sort parameter..
	if (sort != null) {
	    switch (sort) {
	    case SURNAME: {
		return getEmployeeRepository().findByOrderByLastName(pageable);
	    }
	    case JOB_TITLE: {
		return getEmployeeRepository().findByOrderByJobTitle(pageable);
	    }
	    }
	}
	return getEmployeeRepository().findAll(pageable).getContent();
    }

    /*
     * @see com.firstbase.codetest.service.EmployeeService#bootstrapEmployees(int)
     */
    @Override
    public List<Employee> bootstrapEmployees(int count) {
	List<Employee> results = new ArrayList<>();
	List<RandomUser> users = getRandomUserService().createRandomUsers(count);
	for (RandomUser user : users) {
	    EmployeeCreateRequest request = new EmployeeCreateRequest();
	    request.setFirstName(user.getName().getFirst());
	    request.setLastName(user.getName().getLast());
	    request.setPictureUrl(user.getPicture().getMedium());
	    request.setJobTitle(getRandomUserService().createRandomJobTitle());
	    results.add(createEmployee(request));
	}
	return results;
    }

    protected EmployeeRepository getEmployeeRepository() {
	return employeeRepository;
    }

    protected RandomUserService getRandomUserService() {
	return randomUserService;
    }
}
