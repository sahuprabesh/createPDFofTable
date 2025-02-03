package com.priya.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Batch_Allocate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer batchAllocateId;
	@ManyToOne
	@JoinColumn(name="BatchId")
	private Batch_Master batchId;
	@ManyToOne
	@JoinColumn(name="techId")
	private Tech_Master techId;
	@ManyToOne
	@JoinColumn(name="empId")
	private Emp_Master empId;
	
	@ManyToOne
	@JoinColumn(name="slNo")
	private Assesment_Mark markk;
}
