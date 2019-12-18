package com.hcl.parking.service;

import com.hcl.parking.dto.ReleaseSlotDto;

public interface ReleaseSlotService {
	
	String releaseSlot(ReleaseSlotDto releaseSlotDto);
	String approveSlots();
	String getapproveStatus(Integer empId);

}
