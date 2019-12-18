package com.hcl.parking.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.parking.model.ReleaseSlot;

@Repository
public interface ReleaseSlotRepository extends JpaRepository<ReleaseSlot, Integer> {

	List<ReleaseSlot> findByDate(LocalDate date);
	ReleaseSlot findByOccupiedByAndDate(Integer empId, LocalDate date);
}
