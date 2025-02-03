package com.priya.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.priya.models.Batch_Master;
import com.priya.models.Tech_Master;

public interface Batch_Master_Repo extends JpaRepository<Batch_Master, Integer> {

	
}
