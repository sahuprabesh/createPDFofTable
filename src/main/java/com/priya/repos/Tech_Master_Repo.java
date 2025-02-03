package com.priya.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
*/


import com.priya.models.Tech_Master;

public interface Tech_Master_Repo extends JpaRepository<Tech_Master, Integer> {

	@Query("select t from Tech_Master t where t.batchObj.batchId=:batchId")
	public List<Tech_Master> findTechByBatchId(@Param("batchId")Integer batchId);
	

	
	@Query("select t from Tech_Master t where UPPER(t.techName) = UPPER(:techName)")
	public Tech_Master getTech(@Param("techName") String techName);

	public Tech_Master findByTechName(String name);
}
