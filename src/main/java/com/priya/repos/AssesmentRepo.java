package com.priya.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priya.models.Assesment_Mark;

public interface AssesmentRepo extends JpaRepository<Assesment_Mark, Integer> {

}
