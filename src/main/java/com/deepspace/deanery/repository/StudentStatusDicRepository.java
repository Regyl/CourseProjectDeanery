package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.dictionary.AbstractDictionary;
import com.deepspace.deanery.model.dictionary.StudentStatusDic;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentStatusDicRepository extends AbstractDictionaryRepository<StudentStatusDic, StudentStatusDic.Value> {

    Class<StudentStatusDic> SUPPORTED_CLASS = StudentStatusDic.class;

    @Override
    default <U extends AbstractDictionary> boolean supports(Class<U> entity) {
        return entity.equals(SUPPORTED_CLASS);
    }

    @Override
    default <U extends AbstractDictionary> Class<U> getSupportedClass() {
        return (Class<U>) SUPPORTED_CLASS;
    }
}
