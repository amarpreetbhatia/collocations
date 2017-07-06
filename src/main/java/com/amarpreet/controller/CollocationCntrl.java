package com.amarpreet.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
 
import com.amarpreet.CollocationRepository;
import com.amarpreet.model.CollocationModel;

@RestController
public class CollocationCntrl {
	
	@Autowired
	CollocationRepository collocationRepo;
	
	@GetMapping({"/","/collocations","/collocations/{id}","/collocation/{id}"})
	public Page<CollocationModel> getAllData(@PathVariable(required=false) Optional<String> id, Pageable pageable){
		if(id.isPresent()){
			return collocationRepo.findById(id.get(), pageable);
		}
		return collocationRepo.findAll(pageable);
	}

}
