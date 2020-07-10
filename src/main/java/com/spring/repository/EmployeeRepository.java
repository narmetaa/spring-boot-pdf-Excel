package com.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Employee;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
