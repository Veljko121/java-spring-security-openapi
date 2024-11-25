package com.veljko121.backend.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veljko121.backend.core.model.GenericEntity;

public interface GenericEntityRepository<T extends GenericEntity> extends JpaRepository<T, Integer> {
    
}