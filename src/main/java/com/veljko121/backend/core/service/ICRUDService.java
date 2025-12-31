package com.veljko121.backend.core.service;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.lang.NonNull;

public interface ICRUDService<T, ID> {

    T findById(@NonNull ID id) throws NoSuchElementException;

    Collection<T> findAll();

    Collection<T> findAllByIds(@NonNull Iterable<ID> ids);

    T save(@NonNull T entity);

    void deleteById(@NonNull ID id);

    void delete(@NonNull T entity);

    void deleteAll();
    
}
