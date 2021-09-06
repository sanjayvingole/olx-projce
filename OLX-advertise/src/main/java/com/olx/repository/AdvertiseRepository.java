package com.olx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer> {
	public List<AdvertiseEntity> findByUsername(String username);

	List<AdvertiseEntity> findByText(int page, int size, String title, Integer category, Integer status,
			Double price, String dateCondition, String onDate, String fromDate, String toDate);
}
