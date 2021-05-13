package com.firstbase.codetest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstbase.codetest.model.Employee;

/**
 * Manages database interactions for the employee model.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Method that sorts by employee last name.
     * 
     * @param pageable
     * @return
     */
    List<Employee> findByOrderByLastName(Pageable pageable);

    /**
     * Method that sorts by employee job title.
     * 
     * @param pageable
     * @return
     */
    List<Employee> findByOrderByJobTitle(Pageable pageable);
}