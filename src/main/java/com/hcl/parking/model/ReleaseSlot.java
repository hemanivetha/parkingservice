package com.hcl.parking.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReleaseSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String slotId;
	private LocalDate date;
	private Integer occupiedBy;


	public Integer getOccupiedBy() {
		return occupiedBy;
	}

	public void setOccupiedBy(Integer occupiedBy) {
		this.occupiedBy = occupiedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSlotId() {
		return slotId;
	}

	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "ReleaseSlot [id=" + id + ", slotId=" + slotId + ", fromDate=" + date + ", toDate=" + date + "]";
	}
	
	

}
