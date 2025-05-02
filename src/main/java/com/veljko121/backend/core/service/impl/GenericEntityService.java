package com.veljko121.backend.core.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veljko121.backend.core.model.GenericEntity;
import com.veljko121.backend.core.service.IGenericEntityService;

public abstract class GenericEntityService<T extends GenericEntity> extends CRUDService<T, Integer> implements IGenericEntityService<T> {

    public GenericEntityService(JpaRepository<T, Integer> repository) {
        super(repository);
    }

}
