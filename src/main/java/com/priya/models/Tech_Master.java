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
public class Tech_Master {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Integer techId;
	private String techName;
	
	
	@ManyToOne
    @JoinColumn(name = "batchId") 
    private Batch_Master batchObj;
    
	
}