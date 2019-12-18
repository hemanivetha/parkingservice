package com.hcl.parking.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParkingSlot {

	@Id
	private String slotId;
	private Integer empId;

	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public Integer getSlotOwnerId() {
		return empId;
	}

	public void setSlotOwnerId(Integer slotOwnerId) {
		this.empId = slotOwnerId;
	}

}
