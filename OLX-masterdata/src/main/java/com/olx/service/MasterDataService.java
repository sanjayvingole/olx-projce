package com.olx.service;

import java.util.List;

import com.olx.dto.CategoryDTO;
import com.olx.dto.StatusDTO;

public interface MasterDataService {
	
	public List<CategoryDTO> getAllCategories();

	public CategoryDTO getCategoryById(int categoryId);

	List<StatusDTO> getStatus();
}
