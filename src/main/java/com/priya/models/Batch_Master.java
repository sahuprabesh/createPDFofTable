package com.priya.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Batch_Master {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer batchId;
	private String batchName;
	private LocalDate batchStartDate;
	private Integer batchStreangth;
	

}
