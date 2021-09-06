package com.olx.service;

import java.util.List;
import java.util.Map;

import com.olx.dto.CategoryDTO;
import com.olx.dto.StatusDTO;

public interface CategoryServiceDelegate {
	public List<Map> getAllCategories();
	CategoryDTO getCategory(Integer id);
	StatusDTO getStatus(Integer id);
}
