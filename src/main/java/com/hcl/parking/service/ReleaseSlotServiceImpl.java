package com.hcl.parking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parking.dto.ReleaseSlotDto;
import com.hcl.parking.model.ParkingSlot;
import com.hcl.parking.model.ReleaseSlot;
import com.hcl.parking.model.SlotRequest;
import com.hcl.parking.repository.EmployeeRepository;
import com.hcl.parking.repository.ParkingSlotRepository;
import com.hcl.parking.repository.ReleaseSlotRepository;
import com.hcl.parking.repository.SlotRequestRepository;

@Service
public class ReleaseSlotServiceImpl implements ReleaseSlotService {

	@Autowired
	ReleaseSlotRepository releaseSlotRepository;
	@Autowired
	ParkingSlotRepository parkingSlotRepository;
	@Autowired
	SlotRequestRepository slotRequestRepository;
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public String releaseSlot(ReleaseSlotDto releaseSlotDto) {
		ParkingSlot parkingSlot = parkingSlotRepository.findByEmpId(releaseSlotDto.getEmpId());
		if (parkingSlot == null) {
			return "you dont have any slot to release";
		}
		if (!releaseSlotRepository.findByDate(releaseSlotDto.getFromDate()).isEmpty()) {
			return "you have already released on this date";
		} else {
			LocalDate releaseFromDate = releaseSlotDto.getFromDate();

			do {
				ReleaseSlot releaseSlot = new ReleaseSlot();
				releaseSlot.setSlotId(parkingSlot.getSlotId());
				releaseSlot.setDate(releaseFromDate);
				releaseSlotRepository.save(releaseSlot);
				releaseFromDate = releaseFromDate.plusDays(1);
			} while (!releaseFromDate.isEqual(releaseSlotDto.getToDate().plusDays(1)));
			
			return "Slot " + parkingSlot.getSlotId() + " released successfully from " + releaseSlotDto.getFromDate()
					+ " to " + releaseSlotDto.getToDate();

		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	@Transactional
	public String approveSlots() {
		LocalDate date = LocalDate.now();
		List<ReleaseSlot> releaseSlots = releaseSlotRepository.findByDate(date);
		List<SlotRequest> slotRequests = slotRequestRepository.findByDate(date);

		Integer count = 0;
		while (slotRequests.size() > 0 && releaseSlots.size() > count) {
			Integer random;
			if (slotRequests.size() == 1) {
				random = 0;
			} else {
				random = new Random().nextInt(slotRequests.size() - 1);
			}
			releaseSlots.get(count).setOccupiedBy(slotRequests.get(random).getEmpId());
			releaseSlotRepository.save(releaseSlots.get(count));
			slotRequests.remove(random);
			count++;
		}

		return "Slots allocated successfully for the day " + LocalDate.now();
	}

	@Override
	public String getapproveStatus(Integer empId) {
		if (employeeRepository.findById(empId).orElse(null) == null) {
			return "you are not an HCL employee";
		} else {
			ReleaseSlot releaseSlot = releaseSlotRepository.findByOccupiedByAndDate(empId, LocalDate.now());
			if (releaseSlot == null) {
				return "no slot allocated for " + empId + " on " + LocalDate.now();
			} else {
				return "slot " + releaseSlot.getSlotId() + " allocated for you on " + LocalDate.now();
			}
		}

	}

}
