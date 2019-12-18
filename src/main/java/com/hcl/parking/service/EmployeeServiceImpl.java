package com.hcl.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parking.dto.EmployeeDto;
import com.hcl.parking.model.Employee;
import com.hcl.parking.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public boolean authenticate(EmployeeDto employeeDto) {
		Employee employee = employeeRepository.findById(employeeDto.getEmpId()).orElse(null);
		return employee != null && employee.getPassword().equals(employeeDto.getPassword());
	}

}
