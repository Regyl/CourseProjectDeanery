package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.dictionary.AbstractDictionary;
import com.deepspace.deanery.model.dictionary.CathedraDic;
import org.springframework.stereotype.Repository;

@Repository
public interface CathedraDicRepository extends AbstractDictionaryRepository<CathedraDic, CathedraDic.Value> {

    Class<CathedraDic> SUPPORTED_CLASS = CathedraDic.class;

    @Override
    default <U extends AbstractDictionary> boolean supports(Class<U> entity) {
        return entity.equals(SUPPORTED_CLASS);
    }

    @Override
    default <U extends AbstractDictionary> Class<U> getSupportedClass() {
        return (Class<U>) SUPPORTED_CLASS;
    }
}
