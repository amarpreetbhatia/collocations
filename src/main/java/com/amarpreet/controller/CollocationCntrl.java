package com.amarpreet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amarpreet.CollocationRepository;
import com.amarpreet.model.CollocationModel;

@RestController
public class CollocationCntrl {
	
	@Autowired
	CollocationRepository collocationRepo;
	
	@GetMapping("/collocations")
	public Page<CollocationModel> getAllData(Pageable pageable){
		return collocationRepo.findAll(pageable);
	}

}
