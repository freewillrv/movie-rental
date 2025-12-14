package com.rahul.verma.movierental.service;

import com.rahul.verma.movierental.dto.PageableInput;
import com.rahul.verma.movierental.entity.CommonEntity;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonService<T extends CommonEntity> {

    T getById(final int id);

    List<T> findAll();

    T create(@Valid final T entity);

    void delete(final Integer id);

    T update(final T updatedEntity);
    Page<@NonNull T> findAll(PageableInput input);
}
