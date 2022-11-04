package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.dictionary.AbstractDictionary;
import com.deepspace.deanery.model.dictionary.InstructionBasisDic;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionBasisDicRepository extends AbstractDictionaryRepository<InstructionBasisDic, InstructionBasisDic.Value> {

    Class<InstructionBasisDic> SUPPORTED_CLASS = InstructionBasisDic.class;

    @Override
    default <U extends AbstractDictionary> boolean supports(Class<U> entity) {
        return entity.equals(SUPPORTED_CLASS);
    }

    @Override
    default <U extends AbstractDictionary> Class<U> getSupportedClass() {
        return (Class<U>) SUPPORTED_CLASS;
    }
}
