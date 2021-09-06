package com.olx.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvertiseDTO;
import com.olx.dto.CategoryDTO;
import com.olx.service.AdvertiseService;

@RestController
public class AdvertiseController {

	@Autowired
	private AdvertiseService advertiseService;

	@GetMapping(value = "/advertise", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AdvertiseDTO> getAllAdvertises() {
		return advertiseService.getAllAdvertises();
	}

	@GetMapping(value = "/advertise/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AdvertiseDTO> getAdvertisesByUsername(@PathVariable("username") String username) {
		return advertiseService.getAllAdvertisesByUsername(username);
	}

	@PostMapping(value = "/advertise", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDTO> createNewAdvertise(@RequestBody AdvertiseDTO advertiseDto,
			@RequestHeader("Authorization") String authToken) {
		AdvertiseDTO advertiseDTO = advertiseService.createNewAdvertise(advertiseDto, authToken);
		return new ResponseEntity<AdvertiseDTO>(advertiseDTO, HttpStatus.OK);
	}

	@PutMapping(value = "/advertise/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDTO> updateAdvertise(@RequestBody AdvertiseDTO advertiseDto,
			@RequestHeader("Authorization") String authToken, @PathVariable("id") int id) {
		AdvertiseDTO advertiseDTO = advertiseService.updateAdvertise(advertiseDto, authToken, id);
		return new ResponseEntity<AdvertiseDTO>(advertiseDTO, HttpStatus.OK);

	}

	public ResponseEntity<?> filterandsearch(@RequestParam(required = false) String title,
			@RequestParam(required = false) Integer category,
			@RequestParam(required = false) Integer status,
			@RequestParam(required = false) Double price,
			@RequestParam(required = false) String dateCondition,
			@RequestParam(required = false) String onDate,
			@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate,
			@RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String order,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
   return advertiseService.findByText(page, size, title, category, status, price, dateCondition, onDate, fromDate, toDate, sortBy, order);
}

}
