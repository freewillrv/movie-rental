package com.rahul.verma.movierental.service;

import com.rahul.verma.movierental.entity.CommonEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface CommonService<T extends CommonEntity> {

    T getById(final int id);

    List<T> findAll();

    T create(@Valid final T entity);

    void delete(final Integer id);

    T update(final T updatedEntity);
}
