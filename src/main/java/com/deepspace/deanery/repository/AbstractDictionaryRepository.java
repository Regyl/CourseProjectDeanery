package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.dictionary.AbstractDictionary;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface AbstractDictionaryRepository<T extends AbstractDictionary, S extends Enum<S>> extends CrudRepository<T, UUID> {

    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    Optional<T> findByValue(S value);

    @Deprecated
    default <U extends AbstractDictionary> boolean supports(Class<U> entity) {
        return Boolean.FALSE;
    }

    default <U extends AbstractDictionary> Class<U> getSupportedClass() {
        return (Class<U>) AbstractDictionary.class;
    }

}
