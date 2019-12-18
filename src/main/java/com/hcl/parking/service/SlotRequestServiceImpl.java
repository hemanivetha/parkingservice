package com.hcl.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parking.dto.RequestSlotDto;
import com.hcl.parking.model.SlotRequest;
import com.hcl.parking.repository.EmployeeRepository;
import com.hcl.parking.repository.ParkingSlotRepository;
import com.hcl.parking.repository.SlotRequestRepository;

@Service
public class SlotRequestServiceImpl implements SlotRequestService {

	@Autowired
	SlotRequestRepository slotRequestRepository;

	@Autowired
	ParkingSlotRepository parkingSlotRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String getRequest(RequestSlotDto requestSlotDto) {

		if (!slotRequestRepository.findByEmpIdAndDate(requestSlotDto.getFromDate(), requestSlotDto.getEmpId())
				.isEmpty()) {
			return "you have already requested for this time period";
		}
		if (employeeRepository.findById(requestSlotDto.getEmpId()).orElse(null) == null) {
			return "you are not an HCL employee";
		}
		if (parkingSlotRepository.findByEmpId(requestSlotDto.getEmpId()) != null) {
			return "you have a reserved slot";
		} else {
			SlotRequest slotRequest = new SlotRequest();
			slotRequest.setEmpId(requestSlotDto.getEmpId());
			slotRequest.setFromDate(requestSlotDto.getFromDate());
			slotRequest.setToDate(requestSlotDto.getToDate());
			slotRequestRepository.save(slotRequest);
			return ("slot requested successfully");
		}
	}
}
