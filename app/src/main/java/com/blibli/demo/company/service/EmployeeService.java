package com.blibli.demo.company.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blibli.demo.company.entity.Employee;

public interface EmployeeService {

	void create(Employee employee) throws Exception;

	Page<Employee> find(Pageable pageable);

	Page<Employee> findWithCache(Pageable pageable);

	Employee findByCode(Integer code);
	
	Employee findByCodeWithCache(Integer code);
}
