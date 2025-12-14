package com.rahul.verma.movierental.service;

import com.rahul.verma.movierental.dto.*;
import com.rahul.verma.movierental.entity.CommonEntity;
import com.rahul.verma.movierental.exception.BaseException;
import com.rahul.verma.movierental.exception.ConflictException;
import com.rahul.verma.movierental.exception.InternalServerException;
import com.rahul.verma.movierental.exception.NotFoundException;
import com.rahul.verma.movierental.exception.ValidationFailedException;
import com.rahul.verma.movierental.util.Constants;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
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

    protected T preCreateTransform(@Valid final T entity) {
        // Do nothing classes can override and custom validations and throw exceptions
        return entity;
    }

    protected void validateDelete(final T id) {
        // Do nothing classes can override and custom validations and throw exceptions
    }

    protected T preUpdateTransform(T updatedEntity, T entityFromDB) {
        // Do nothing classes can override and custom validations and throw exceptions
        return updatedEntity;
    }

    protected void validateUpdate(T updatedEntity, T entityFromDB) {
        // Do nothing classes can override and custom validations and throw exceptions
    }

    @Transactional
    public T create(@Valid final T entity) {
        try {
            validateCreate(entity);
            final T entityForSave = preCreateTransform(entity);
            validateCreate(entityForSave);
            if (ObjectUtils.isEmpty(entityForSave)) {
                throw new ValidationFailedException(String.format("{} cannot be empty", resourceName));
            }
            return repository.save(entityForSave);
        } catch (BaseException baseException) {
            throw baseException;
        } catch (DataIntegrityViolationException duplicate) {
            log.info("Duplicate User create blocked for user {}.", entity.getId());
            throw new ConflictException(duplicate, String.format(resourceName + " already exists."));
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
            T entityInDB = repository.getReferenceById(updatedEntity.getId());
            validateUpdate(updatedEntity, entityInDB);
            T entityToUpdate = preUpdateTransform(updatedEntity, entityInDB);
            return repository.save(updatedEntity);
        } catch (BaseException baseException) {
            throw baseException;
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException, String.format("{} with id: {} not found",
                    resourceName, updatedEntity.getId()));
        } catch (Exception e) {
            throw new InternalServerException(e, String.format("Error updating {} with id: {} from the system. Please try again later"
                    , resourceName, updatedEntity.getId()));
        }
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public Page<@NonNull T> findAll(PageableInput input) {
        try {
            Pageable pageable = getPageable(input);
            return repository.findAll(pageable);
        } catch (BaseException baseException) {
            throw baseException;
        } catch (Exception e) {
            throw new InternalServerException(e, String.format("error in getting all {} from the database.", resourceName));
        }
    }

    private Pageable getPageable(PageableInput input) {

        if (!sortableAttributes.contains(input.getSortBy())) {
            throw new ValidationFailedException(String.format("sorting is not allowed on this attribute {}", input.getSortBy()));
        }
        final String sortBy = input.getSortBy();
        final Sort.Direction direction =
                Constants.DESC.equalsIgnoreCase(input.getDirection()) ?
                        Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(
                input.getPage(),
                input.getSize(),
                Sort.by(direction, sortBy)
        );
    }

    public T getById(final int id) {
        try {
            return repository.getReferenceById(id);
        } catch (BaseException baseException) {
            throw baseException;
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException, String.format("{} with id: {} not found",
                    resourceName, id));
        } catch (Exception e) {
            throw new InternalServerException(e, String.format("Error fetching {} with id: {} from the system. Please try again later"
                    , resourceName, id));
        }
    }

}
