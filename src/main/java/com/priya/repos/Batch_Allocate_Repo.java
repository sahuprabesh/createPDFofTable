package com.priya.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.priya.models.Batch_Allocate;
import com.priya.models.Tech_Master;

public interface Batch_Allocate_Repo extends JpaRepository<Batch_Allocate, Integer> {

	@Query("select b.techId from Batch_Allocate b where b.techId.techId=:batchId")
	public List<Tech_Master> findTechByBatchId(@Param("batchId")Integer batchId);
	
	@Query("select b from Batch_Allocate b where b.batchId.batchId=:Id")
	public List<Batch_Allocate> findAllByBatch(@Param("Id")Integer Id);
}
