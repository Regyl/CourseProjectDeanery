package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.dictionary.AbstractDictionary;
import com.deepspace.deanery.model.dictionary.InstructionTypeDic;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionTypeDicRepository extends AbstractDictionaryRepository<InstructionTypeDic, InstructionTypeDic.Value> {

    Class<InstructionTypeDic> SUPPORTED_CLASS = InstructionTypeDic.class;

    @Override
    default <U extends AbstractDictionary> boolean supports(Class<U> entity) {
        return entity.equals(SUPPORTED_CLASS);
    }

    @Override
    default <U extends AbstractDictionary> Class<U> getSupportedClass() {
        return (Class<U>) SUPPORTED_CLASS;
    }
}
