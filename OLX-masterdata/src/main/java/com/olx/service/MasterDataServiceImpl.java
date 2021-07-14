package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.CategoryDTO;
import com.olx.dto.StatusDTO;
import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.repository.MasterDataCategoryRepository;
import com.olx.repository.MasterDataStatusRepository;

@Service
public class MasterDataServiceImpl implements MasterDataService {

	List<CategoryDTO> categoryDTOs = new ArrayList<CategoryDTO>();
	List<StatusDTO> statusDTOs = new ArrayList<StatusDTO>();

	@Autowired
	MasterDataCategoryRepository masterDataCategoryRepository;
	@Autowired
	MasterDataStatusRepository masterDataStatusRepository;

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<CategoryEntity> categoryEntities = masterDataCategoryRepository.findAll();
		List<CategoryDTO> categories = new ArrayList<>();
		categoryEntities.stream().forEach((categoryEntity) -> categories.add(
				new CategoryDTO(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription())));
		return categories;
	}

	@Override
	public CategoryDTO getCategoryById(int categoryId) {
		Optional<CategoryEntity> opCategoryEntity = masterDataCategoryRepository.findById(categoryId);
		if (opCategoryEntity.isPresent()) {
			CategoryEntity categoryEntity = opCategoryEntity.get();
			return new CategoryDTO(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription());
		}
		throw new InvalidCategoryIdException(" " + categoryId);
	}

	@Override
	public List<StatusDTO> getStatus() {

		List<StatusEntity> statusEntities = masterDataStatusRepository.findAll();

		for (StatusEntity statusEntity : statusEntities) {
			StatusDTO statusDTO = new StatusDTO(statusEntity.getId(), statusEntity.getStatus());
			statusDTOs.add(statusDTO);
		}

		return statusDTOs;
	}
}
