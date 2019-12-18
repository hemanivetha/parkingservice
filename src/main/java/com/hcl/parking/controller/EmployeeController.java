package com.hcl.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parking.dto.EmployeeDto;
import com.hcl.parking.service.EmployeeService;

@RestController
@RequestMapping("employees/")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/authentication")
	public ResponseEntity<String> authenticate(@RequestBody EmployeeDto employeeDto) {
		if (employeeDto == null) {
			return ResponseEntity.ok().body("please send valid entity");
		} else if (employeeDto.getEmpId() == null) {
			return ResponseEntity.ok().body("employeeId is missing");
		} else if (employeeDto.getPassword() == null) {
			return ResponseEntity.ok().body("password is missing");
		} else {
			boolean flag = employeeService.authenticate(employeeDto);
			if (flag) {
				return ResponseEntity.ok().body("successfully logged in");
			} else {
				return ResponseEntity.ok().body("employeeId and password did not match");
			}

		}

	}

}
