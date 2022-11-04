package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.dictionary.AbstractDictionary;
import com.deepspace.deanery.model.dictionary.StudentGroupPrefixDic;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGroupPrefixDicRepository extends AbstractDictionaryRepository<StudentGroupPrefixDic, StudentGroupPrefixDic.Value> {

    Class<StudentGroupPrefixDic> SUPPORTED_CLASS = StudentGroupPrefixDic.class;

    @Override
    default <U extends AbstractDictionary> boolean supports(Class<U> entity) {
        return entity.equals(SUPPORTED_CLASS);
    }

    @Override
    default <U extends AbstractDictionary> Class<U> getSupportedClass() {
        return (Class<U>) SUPPORTED_CLASS;
    }
}
