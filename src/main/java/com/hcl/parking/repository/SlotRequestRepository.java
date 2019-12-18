package com.hcl.parking.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.parking.model.SlotRequest;

@Repository
public interface SlotRequestRepository extends JpaRepository<SlotRequest, Integer>{
	
	@Query("FROM SlotRequest WHERE ?1 BETWEEN fromDate AND toDate")
	List<SlotRequest> findByDate(LocalDate date);
	
	@Query("FROM SlotRequest WHERE toDate >= ?1 AND empId = ?2")
	List<SlotRequest> findByEmpIdAndDate(LocalDate date, Integer empId);

}
