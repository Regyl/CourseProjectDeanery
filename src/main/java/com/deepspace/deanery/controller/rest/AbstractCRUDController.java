package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.api.CRUDController;
import com.deepspace.deanery.exception.DeaneryException;
import com.deepspace.deanery.model.AbstractEntity;
import com.deepspace.deanery.repository.AbstractJpaRepository;

import java.util.UUID;
import java.util.function.Supplier;

public abstract class AbstractCRUDController<T extends AbstractEntity> implements CRUDController<T> {

    private static final Supplier<DeaneryException> DEFAULT_SUPPLIER = () -> new
            DeaneryException(DeaneryException.Reason.INVALID_ARGUMENT, "Сущность не найдена");

    protected abstract AbstractJpaRepository<T> getRepository();

    @Override
    public T create(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T update(T entity, UUID id) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(UUID id) {
        T entity = get(id);
        getRepository().delete(entity);
    }

    @Override
    public T get(UUID id) {
        return getRepository().findById(id)
                .orElseThrow(DEFAULT_SUPPLIER);
    }

    @Override
    public Iterable<T> getAll() {
        return getRepository().findAll();
    }
}
