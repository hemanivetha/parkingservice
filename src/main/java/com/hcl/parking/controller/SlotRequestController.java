package com.hcl.parking.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.service.SlotRequestService;

@RestController
@RequestMapping("slotRequest/")
public class SlotRequestController {

	@Autowired
	SlotRequestService slotRequestService;

	@PostMapping("")
	public ResponseEntity<String> requestSlot(@RequestBody RequestSlotDto requestSlotDto) {
		if (requestSlotDto.getFromDate().isBefore(LocalDate.now())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("fromDate is less than current date");
		} else if (requestSlotDto.getToDate().isBefore(requestSlotDto.getFromDate())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("toDate is less than fromDate");
		} else if (requestSlotDto.getToDate().isAfter(LocalDate.now().plusDays(2))) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you can apply request only upto next 2 days");
		} else {
			String message = slotRequestService.getRequest(requestSlotDto);
			return ResponseEntity.ok().body(message);
		}
	}

}
