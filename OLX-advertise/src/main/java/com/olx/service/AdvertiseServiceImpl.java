package com.olx.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.AdvertiseDTO;
import com.olx.dto.AdvertiseList;
import com.olx.dto.CategoryDTO;
import com.olx.dto.StatusDTO;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidAuthorizationTokenException;
import com.olx.repository.AdvertiseRepository;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

	@Autowired
	private AdvertiseRepository advertiseRepo;
	@Autowired
	private CategoryServiceDelegate categoryServiceDelegate;
	@Autowired
	private UserServiceDelegate userServiceDelegate;

	@Override
	public List<AdvertiseDTO> getAllAdvertises() {
		List<AdvertiseEntity> advertiseEntities = advertiseRepo.findAll();
		/*
		 * String usernames =
		 * advertiseEntities.stream().map((advertiseEntity)->advertiseEntity.getUsername
		 * ()).collect(Collectors.joining(",")); List<Map> listMapUsers =
		 * userServiceDelegate.findByUsernames(usernames);
		 */
		List<Map> listMapUsers = new ArrayList<>();

		advertiseEntities.stream().forEach((advertiseEntity) -> {
			listMapUsers.add(userServiceDelegate.findByUsername(advertiseEntity.getUsername()));
		});

		List<AdvertiseDTO> advertises = new ArrayList<AdvertiseDTO>();
		List<Map> categories = categoryServiceDelegate.getAllCategories();

		Map<Long, String> hmapCategories = new HashMap<>();
		categories.stream().forEach((categoryMap) -> {
			System.out.println("categoryMap: " + categoryMap);
			hmapCategories.put(new Long((Integer) categoryMap.get("id")), (String) categoryMap.get("name"));
		});

		advertiseEntities.stream().forEach((advertiseEntity) -> {

			Map tempMap = listMapUsers.stream().filter((mapUser) -> {
				return mapUser.get("username").equals(advertiseEntity.getUsername());
			}).findFirst().get();

			String postedBy = tempMap.get("firstName") + " " + tempMap.get("lastName");
			advertises.add(new AdvertiseDTO(advertiseEntity.getId(), advertiseEntity.getTitle(),
					advertiseEntity.getDescription(), advertiseEntity.getPrice(), advertiseEntity.getCategory(),
					advertiseEntity.getStatus(), advertiseEntity.getCreatedDate(), advertiseEntity.getModifiedDate(),
					advertiseEntity.getUsername(), postedBy));
		});
		return advertises;
	}

	@Override
	public AdvertiseDTO createNewAdvertise(AdvertiseDTO advertiseDto, String authToken) {
		if (!userServiceDelegate.isLoggedInUser(authToken))
			throw new InvalidAuthorizationTokenException(authToken);
		AdvertiseEntity advertiseEntity = new AdvertiseEntity(advertiseDto.getTitle(), advertiseDto.getDescription(),
				advertiseDto.getPrice(), advertiseDto.getCategories(), advertiseDto.getStatus(), LocalDate.now(),
				LocalDate.now(), advertiseDto.getUsername());
		advertiseEntity = advertiseRepo.save(advertiseEntity);
		return new AdvertiseDTO(advertiseEntity.getId(), advertiseEntity.getTitle(), advertiseEntity.getDescription(),
				advertiseEntity.getPrice(), advertiseEntity.getCategory(), advertiseDto.getStatus(),
				advertiseEntity.getCreatedDate(), advertiseEntity.getModifiedDate(), advertiseEntity.getUsername(),
				advertiseDto.getPostedBy());
	}

	@Override
	public List<AdvertiseDTO> getAllAdvertisesByUsername(String username) {
		List<AdvertiseEntity> advertiseEntities = advertiseRepo.findByUsername(username);
		Map mapUser = userServiceDelegate.findByUsername(username);
		List<AdvertiseDTO> advertises = new ArrayList<AdvertiseDTO>();
		List<Map> categories = categoryServiceDelegate.getAllCategories();
		Map<Long, String> hmapCategories = new HashMap<>();
		categories.stream().forEach((categoryMap) -> hmapCategories.put(new Long((Integer) categoryMap.get("id")),
				(String) categoryMap.get("name")));

		advertiseEntities.stream()
				.forEach((advertiseEntity) -> advertises.add(new AdvertiseDTO(advertiseEntity.getId(),
						advertiseEntity.getTitle(), advertiseEntity.getDescription(), advertiseEntity.getPrice(),
						advertiseEntity.getCategory(), advertiseEntity.getStatus(), advertiseEntity.getCreatedDate(),
						advertiseEntity.getModifiedDate(), advertiseEntity.getUsername(),
						(mapUser.get("firstName") + " " + mapUser.get("lastName")))));
		return advertises;
	}

	@Override
	public AdvertiseDTO updateAdvertise(AdvertiseDTO advertiseDto, String authToken, int id) {
		Optional<AdvertiseEntity> optional = advertiseRepo.findById(id);
		if (optional.isPresent()) {
			AdvertiseEntity advertiseEntity = optional.get();
			advertiseEntity.setUsername(advertiseDto.getUsername());
			advertiseEntity.setDescription(advertiseDto.getDescription());
			advertiseEntity.setPrice(advertiseDto.getPrice());
			advertiseEntity.setCategory(advertiseDto.getCategories());
			advertiseEntity.setModifiedDate(advertiseDto.getModifiedDate());
			advertiseEntity.setCreatedDate(advertiseDto.getCreatedDate());
			advertiseEntity.setTitle(advertiseDto.getTitle());
			advertiseRepo.save(advertiseEntity);
			advertiseDto.setId(id);
			return advertiseDto;
		}
		return null;
	}

	@Override
	public AdvertiseDTO getAllAdvertisesById(int id, String authToken) {
		if (userServiceDelegate.isLoggedInUser(authToken)) {
			Optional<AdvertiseEntity> ads = advertiseRepo.findById(id);
			if (ads.isPresent()) {
				AdvertiseEntity advertiseEntity = ads.get();
				CategoryDTO categoryDTO = (CategoryDTO) categoryServiceDelegate.getAllCategories();
				AdvertiseDTO advertiseDTO = new AdvertiseDTO(advertiseEntity.getId(), advertiseEntity.getTitle(),
						advertiseEntity.getDescription(), advertiseEntity.getPrice(), advertiseEntity.getCategory(),
						advertiseEntity.getStatus(), advertiseEntity.getCreatedDate(),
						advertiseEntity.getModifiedDate(), advertiseEntity.getUsername(), null);
				return advertiseDTO;

			}
		}

		return null;
	}

	@Override
	public AdvertiseDTO getAllAdvertiseDetailsById(int id, String authToken) {
		if (userServiceDelegate.isLoggedInUser(authToken)) {
			Optional<AdvertiseEntity> ads = advertiseRepo.findById(id);
			if (ads.isPresent()) {
				AdvertiseEntity advertiseEntity = ads.get();
				CategoryDTO categoryDTO = (CategoryDTO) categoryServiceDelegate.getAllCategories();
				AdvertiseDTO advertiseDTO = new AdvertiseDTO(advertiseEntity.getId(), advertiseEntity.getTitle(),
						advertiseEntity.getDescription(), advertiseEntity.getPrice(), advertiseEntity.getCategory(),
						advertiseEntity.getStatus(), advertiseEntity.getCreatedDate(),
						advertiseEntity.getModifiedDate(), advertiseEntity.getUsername(), null);
				return advertiseDTO;

			}
		}

		return null;

	}

	@Override
	public Boolean deleteAdvertiseById(int id, String authToken) {
		if (userServiceDelegate.isLoggedInUser(authToken)) {
			if (advertiseRepo.existsById(id)) {
				advertiseRepo.deleteById(id);
				return true;
			}
		}
		return false;
	}

	private void set(List<AdvertiseEntity> advertiseList, List<AdvertiseDTO> advertiseResponseList) {

		for (AdvertiseEntity advdata : advertiseList) {
			CategoryDTO category = categoryServiceDelegate.getCategory(advdata.getCategory());
			StatusDTO status = categoryServiceDelegate.getStatus(advdata.getStatus());
			AdvertiseDTO ad = new AdvertiseDTO(advdata.getId(), advdata.getTitle(), advdata.getDescription(),
					advdata.getPrice(), advdata.getCategory(), advdata.getStatus(), advdata.getCreatedDate(),
					advdata.getModifiedDate(), advdata.getUsername(), null);
			advertiseResponseList.add(ad);
		}

	}
	
	private List<AdvertiseEntity> sort(List<AdvertiseEntity> advertiseList, String sortBy) {
		switch (sortBy){
		case "category" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparingInt(AdvertiseEntity::getCategory)).collect(Collectors.toList());
			break;
		case "title" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparing(AdvertiseEntity::getTitle)).collect(Collectors.toList());
			break;
		case "status" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparingInt(AdvertiseEntity::getStatus)).collect(Collectors.toList());
			break;
		case "price" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparingDouble(AdvertiseEntity::getPrice)).collect(Collectors.toList());
			break;
		case "createddate" :
			advertiseList = advertiseList.stream().sorted(Comparator.comparing(AdvertiseEntity::getCreatedDate)).collect(Collectors.toList());
			break;
		default:
	}
	return advertiseList;
}
	
	private Date getDate(String createdDate) throws ParseException {
		if (createdDate == null)
		{
			return new Date();
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(createdDate);
	}

	@Override
	public ResponseEntity<?> findByText(int page, int size, String title, Integer category, Integer status, Double price,
			String dateCondition, String onDate, String fromDate, String toDate, String sortBy, String order) {
		List<AdvertiseEntity> advertiseList = advertiseRepo.findByText(page,size,title,category,status,price,dateCondition,onDate,fromDate,toDate);

		if (sortBy !=null)
			advertiseList = sort(advertiseList,sortBy);
		if (order !=null && order.equals("des"))
			Collections.reverse(advertiseList);
		
		List<AdvertiseDTO> advertiseResponseList = new ArrayList<>();
		set(advertiseList, advertiseResponseList);
		return ResponseEntity.ok(new AdvertiseList(advertiseResponseList));
	}

}
