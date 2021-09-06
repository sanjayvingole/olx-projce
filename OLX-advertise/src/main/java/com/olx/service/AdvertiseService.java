package com.olx.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.olx.dto.AdvertiseDTO;
import com.olx.dto.CategoryDTO;

public interface AdvertiseService {
	public List<AdvertiseDTO> getAllAdvertises();

	public List<AdvertiseDTO> getAllAdvertisesByUsername(String username);

	public AdvertiseDTO getAllAdvertisesById(int id, String authToken);

	public AdvertiseDTO getAllAdvertiseDetailsById(int id, String authToken);

	public AdvertiseDTO createNewAdvertise(AdvertiseDTO advertiseDto, String authToken);

	public AdvertiseDTO updateAdvertise(AdvertiseDTO advertiseDto, String authToken, int id);

	public Boolean deleteAdvertiseById(int id, String authToken);

	ResponseEntity<?> findByText(int page, int size, String title, Integer category, Integer status, Double price,
			String dateCondition, String onDate, String fromDate, String toDate, String sortBy, String order);

}
