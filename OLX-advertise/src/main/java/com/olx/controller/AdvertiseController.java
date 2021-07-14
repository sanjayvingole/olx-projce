package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvertiseDTO;
import com.olx.service.AdvertiseService;

@RestController
public class AdvertiseController {

	@Autowired
	private AdvertiseService advertiseService;
	
	@GetMapping(value="/advertise", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AdvertiseDTO> getAllAdvertises() {
		return advertiseService.getAllAdvertises();
	}

	@GetMapping(value="/advertise/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AdvertiseDTO> getAdvertisesById(@PathVariable("id") String username) {
		return advertiseService.getAllAdvertisesByUsername(username);
	}

	@PostMapping(value="/advertise", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDTO> createNewAdvertise(@RequestBody AdvertiseDTO advertiseDto, @RequestHeader("Authorization") String authToken) {
		AdvertiseDTO advertiseDTO = this.advertiseService.createNewAdvertise(advertiseDto, authToken);
		return new ResponseEntity<AdvertiseDTO>(advertiseDTO, HttpStatus.OK);
	}

}
