package com.deepspace.deanery.service;

import com.deepspace.deanery.api.DictionaryService;
import com.deepspace.deanery.exception.DeaneryException;
import com.deepspace.deanery.model.dictionary.AbstractDictionary;
import com.deepspace.deanery.repository.AbstractDictionaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {

    private final Map<Class<? extends AbstractDictionary>, AbstractDictionaryRepository<? extends AbstractDictionary, ? extends Enum<?>>> abstractDictionaryRepositories;

    public DictionaryServiceImpl(Collection<AbstractDictionaryRepository<? extends AbstractDictionary, ? extends Enum<?>>> repositories) {
        abstractDictionaryRepositories = new HashMap<>(repositories.size());
        for (var repository : repositories) {
            abstractDictionaryRepositories.put(repository.getSupportedClass(), repository);
        }
    }

    @Override
    public <T extends AbstractDictionary, S extends Enum<S>> List<T> saveAll(List<T> entities) {
        if (entities == null || entities.isEmpty()) {
            return entities;
        }

        var repository = getRepositoryByClass(entities.get(0));
        repository.saveAll(entities);
        return entities;
    }

    private <T extends AbstractDictionary, S extends Enum<S>> AbstractDictionaryRepository<T, S> getRepositoryByClass(T entity) {
        var repository = (AbstractDictionaryRepository<T, S>) abstractDictionaryRepositories.get(entity.getClass());
        if (repository == null) {
            throw new DeaneryException(DeaneryException.Reason.NOT_IMPLEMENTED,
                    "Не реализован репозиторий для класса " + entity.getClass());
        }

        return repository;
    }
}
