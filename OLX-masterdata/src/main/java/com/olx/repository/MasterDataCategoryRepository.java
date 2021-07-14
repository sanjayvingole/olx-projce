package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.CategoryEntity;

public interface MasterDataCategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
