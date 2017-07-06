package com.amarpreet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amarpreet.model.CollocationModel;

@Repository
public interface CollocationRepository extends JpaRepository<CollocationModel, String>{
	
	public Page<CollocationModel> findById(String id,Pageable pageable);

}
