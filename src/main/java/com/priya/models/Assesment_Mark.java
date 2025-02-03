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
public class Assesment_Mark {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer slNo;
	private Integer mark;

	@ManyToOne
	@JoinColumn(name="empId")
	private Emp_Master empObject;
	
}
