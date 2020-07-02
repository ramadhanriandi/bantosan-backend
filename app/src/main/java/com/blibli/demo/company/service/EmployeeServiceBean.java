package com.blibli.demo.company.service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.blibli.demo.company.constant.CacheNames;
import com.blibli.demo.company.entity.Employee;
import com.blibli.demo.company.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeServiceBean implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceBean.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${configuration.employee.kafka-topic}")
	private String kafkaTopic;

	@Override
	public void create(Employee employee) throws Exception {
		employeeRepository.save(employee);
	}

	@Override
	public Page<Employee> find(Pageable pageable) {
		return employeeRepository.findByMarkForDeleteFalse(pageable);
	}

	@Override
	public Page<Employee> findWithCache(Pageable pageable) {
		Page<Employee> result = findEmployeesFromCache(CacheNames.EMPLOYEES);
		if (!Objects.isNull(result)) {
			return result;
		}
		result = employeeRepository.findByMarkForDeleteFalse(pageable);
		this.redisTemplate.opsForValue().set(CacheNames.EMPLOYEES, result, 5, TimeUnit.MINUTES);
		return result;
	}

	private Page<Employee> findEmployeesFromCache(String key) {
		if (this.redisTemplate.hasKey(key)) {
			return (Page<Employee>) this.redisTemplate.opsForValue().get(key);
		}
		return null;
	}

	@Override
	public Employee findByCode(Integer code) {
		return employeeRepository.findFirstByEmpNoAndMarkForDeleteFalse(code);
	}

	@Override
	public Employee findByCodeWithCache(Integer code) {
		Employee result = findEmployeeFromCache(CacheNames.EMPLOYEE + code);
		if (!Objects.isNull(result)) {
			return result;
		}
		result = employeeRepository.findFirstByEmpNoAndMarkForDeleteFalse(code);
		this.redisTemplate.opsForValue().set(CacheNames.EMPLOYEE + code, result, 5, TimeUnit.MINUTES);
		return result;
	}

	private Employee findEmployeeFromCache(String key) {
		if (this.redisTemplate.hasKey(key)) {
			return (Employee) this.redisTemplate.opsForValue().get(key);
		}
		return null;
	}
}
