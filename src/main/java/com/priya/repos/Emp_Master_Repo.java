package com.priya.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.priya.models.Emp_Master;

public interface Emp_Master_Repo extends JpaRepository<Emp_Master, Integer> {

	@Query("select e from Emp_Master e where e.techObj.techId=:techId")
	public List<Emp_Master> getAllEmpByTechId(@Param("techId")Integer techId);
	
	public Emp_Master findByEmpName(String name);
}
