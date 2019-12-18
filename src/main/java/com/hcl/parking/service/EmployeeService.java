package com.hcl.parking.service;

import com.hcl.parking.dto.EmployeeDto;

public interface EmployeeService {
	
	public boolean authenticate(EmployeeDto employeeDto);

}
