package com.olx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.CategoryDTO;
import com.olx.dto.StatusDTO;
import com.olx.service.MasterDataService;

@RestController
public class MasterDataController {

	@Autowired
	MasterDataService masterDataService;

	List<StatusDTO> statusDTOs = new ArrayList<StatusDTO>();
	List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();

	@GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CategoryDTO> getAllCategories() {
		return masterDataService.getAllCategories();
	}

	@GetMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDTO getCategoryById(@PathVariable("id") String strCategoryId) {
		return masterDataService.getCategoryById(Integer.parseInt(strCategoryId));
	}

	@GetMapping(value = "/masterdata/status")
	public ResponseEntity<List<StatusDTO>> getStatus() {
		statusDTOs = masterDataService.getStatus();
		return new ResponseEntity<List<StatusDTO>>(statusDTOs, HttpStatus.OK);
	}
}
