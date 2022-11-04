package com.deepspace.deanery.api;

import com.deepspace.deanery.model.dictionary.AbstractDictionary;

import java.util.List;

public interface DictionaryService {

    <T extends AbstractDictionary, S extends Enum<S>> List<T> saveAll(List<T> entities);
}
