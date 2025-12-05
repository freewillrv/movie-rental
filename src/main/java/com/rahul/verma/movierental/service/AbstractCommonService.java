package com.rahul.verma.movierental.service;

import com.rahul.verma.movierental.entity.CommonEntity;
import com.rahul.verma.movierental.exception.BaseException;
import com.rahul.verma.movierental.exception.InternalServerException;
import com.rahul.verma.movierental.exception.NotFoundException;
import com.rahul.verma.movierental.exception.ValidationFailedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Set;

@Slf4j
public class AbstractCommonService<T extends CommonEntity> {

    protected final JpaRepository<T, Integer> repository;
    protected final String resourceName;
    protected final Set<String> sortableAttributes;

    public AbstractCommonService(final JpaRepository<T, Integer> repository, final String resourceName,
                                 final Set<String> sortableAttributes) {
        this.repository = repository;
        this.resourceName = resourceName;
        this.sortableAttributes = sortableAttributes;
    }

    protected void validateCreate(@Valid final T entity) {
        // Do nothing classes can override and custom validations and throw exceptions
    }

    protected void validateDelete(final T id) {
        // Do nothing classes can override and custom validations and throw exceptions
    }

    protected void validateUpdate(T updatedEntity, T entityFromDB) {
        // Do nothing classes can override and custom validations and throw exceptions
    }

    @Transactional
    public T create(@Valid final T entity) {
        try {
            validateCreate(entity);
            if (ObjectUtils.isEmpty(entity)) {
                throw new ValidationFailedException(String.format("{} cannot be empty", resourceName));
            }
            return repository.save(entity);
        } catch (BaseException baseException) {
            throw baseException;
        } catch (Exception e) {
            throw new InternalServerException(e, String.format("Error saving {} to the system. Please try again later",
                    resourceName));
        }
    }

    @Transactional
    public void delete(final Integer id) {
        try {
            validateDelete(repository.getReferenceById(id));
            repository.deleteById(id);
        } catch (BaseException baseException) {
            throw baseException;
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException, String.format("{} with id: {} not found",
                    resourceName, id));
        } catch (Exception e) {
            throw new InternalServerException(e, String.format("Error deleting {} with id: {} from the system. Please try again later"
                    , resourceName, id));
        }
    }

    @Transactional
    public T update(final T updatedEntity) {
        try {
            validateUpdate(updatedEntity, repository.getReferenceById(updatedEntity.getId()));
            return repository.save(updatedEntity);
        } catch (BaseException baseException) {
            throw baseException;
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException, String.format("{} with id: {} not found",
                    resourceName, updatedEntity.getId()));
        }
    }


}
