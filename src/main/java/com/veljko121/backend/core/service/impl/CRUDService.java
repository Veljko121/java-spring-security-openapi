package com.veljko121.backend.core.service.impl;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veljko121.backend.core.service.ICRUDService;

public abstract class CRUDService<T, ID> implements ICRUDService<T, ID> {

    private JpaRepository<T, ID> repository;
    
    public CRUDService(JpaRepository<T, ID> repository) {
        super();
        this.repository = repository;
    }

    @Override
    public T findById(ID id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Collection<T> findAllByIds(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    
    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
    
}
