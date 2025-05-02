package com.veljko121.backend.core.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veljko121.backend.core.model.GenericEntity;
import com.veljko121.backend.core.service.IGenericEntityCRUDService;

public abstract class GenericEntityService<T extends GenericEntity> extends CRUDService<T, Integer> implements IGenericEntityCRUDService<T> {

    public GenericEntityService(JpaRepository<T, Integer> repository) {
        super(repository);
    }

}
