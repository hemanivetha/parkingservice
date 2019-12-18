package com.hcl.parking.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parking.dto.ReleaseSlotDto;
import com.hcl.parking.service.ReleaseSlotService;

@RestController
@RequestMapping("releasedSlot/")
public class ReleaseSlotController {

	@Autowired
	ReleaseSlotService releaseSlotService;

	@PostMapping("")
	public ResponseEntity<String> releaseSlot(@RequestBody ReleaseSlotDto releaseSlotDto) {
		if (releaseSlotDto.getFromDate().isBefore(LocalDate.now())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("From date cannot be less than current date");
		} else if (releaseSlotDto.getToDate().isBefore(releaseSlotDto.getFromDate())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("toDate is less than fromDate");
		} else {
			String message = releaseSlotService.releaseSlot(releaseSlotDto);
			return ResponseEntity.ok().body(message);
		}

	}

	@GetMapping("/approve")
	public ResponseEntity<String> approveSlot() {
		String message = releaseSlotService.approveSlots();
		return ResponseEntity.ok().body(message);
	}

	@GetMapping("/approve/{empId}")
	public ResponseEntity<String> getApproveStatus(@PathVariable("empId") Integer empId) {
		if (empId < 1) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("please enter valid employee Id");
		}
		String message = releaseSlotService.getapproveStatus(empId);
		return ResponseEntity.ok().body(message);
	}

}
