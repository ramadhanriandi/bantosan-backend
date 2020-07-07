package com.blibli.demo.company.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blibli.demo.base.BaseResponse;
import com.blibli.demo.base.ListBaseResponse;
import com.blibli.demo.base.Metadata;
import com.blibli.demo.base.SingleBaseResponse;
import com.blibli.demo.company.entity.Employee;
import com.blibli.demo.company.security.service.EmployeeService;
import com.blibli.demo.dto.EmployeeRequest;
import com.blibli.demo.dto.EmployeeResponse;

@RestController
@RequestMapping(value = EmployeeControllerPath.BASE_PATH)
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse create(@RequestParam String storeId, @RequestParam String channelId,
			@RequestParam String clientId, @RequestParam String requestId, @RequestParam String username,
			@RequestBody EmployeeRequest request) throws Exception {
		Employee employee = Employee.builder().build();
		BeanUtils.copyProperties(request, employee);
		this.employeeService.create(employee);
		return new BaseResponse(null, null, true, requestId);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ListBaseResponse<EmployeeResponse> find(@RequestParam String storeId, @RequestParam String channelId,
			@RequestParam String clientId, @RequestParam String requestId, @RequestParam String username,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size)
			throws Exception {
		Pageable pageable = PageRequest.of(page, size);
		Page<Employee> employees = this.employeeService.findWithCache(pageable);
		List<EmployeeResponse> employeeResponses = new ArrayList<>();
		for (Employee employee : employees.getContent()) {
			EmployeeResponse employeeResponse = EmployeeResponse.builder().build();
			BeanUtils.copyProperties(employee, employeeResponse);
			employeeResponses.add(employeeResponse);
		}
		return new ListBaseResponse<>(null, null, true, requestId, employeeResponses,
				new Metadata(page, size, employees.getTotalElements()));
	}

	@RequestMapping(value = EmployeeControllerPath.FIND_BY_CODE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SingleBaseResponse<EmployeeResponse> findByEmpNo(@RequestParam String storeId,
			@RequestParam String channelId, @RequestParam String clientId, @RequestParam String requestId,
			@RequestParam String username, @RequestParam Integer code) throws Exception {
		Employee employee = this.employeeService.findByCodeWithCache(code);
		EmployeeResponse employeeResponse = EmployeeResponse.builder().build();
		BeanUtils.copyProperties(employee, employeeResponse);
		return new SingleBaseResponse<>(null, null, true, requestId, employeeResponse);
	}

}
