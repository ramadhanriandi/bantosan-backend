package com.blibli.demo.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.blibli.demo.company.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Employee findFirstByEmpNoAndMarkForDeleteFalse(Integer empNo);

	Page<Employee> findByMarkForDeleteFalse(Pageable pageable);
}
