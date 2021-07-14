package com.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.StatusEntity;

public interface MasterDataStatusRepository extends JpaRepository<StatusEntity, Integer> {

}
