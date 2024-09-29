package com.veljko121.backend.core.service;

import com.veljko121.backend.core.model.GenericEntity;

public interface IGenericEntityCRUDService<T extends GenericEntity> extends ICRUDService<T, Integer> {
    
}
