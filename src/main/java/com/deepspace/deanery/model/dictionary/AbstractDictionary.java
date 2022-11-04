package com.deepspace.deanery.model.dictionary;

import com.deepspace.deanery.model.AbstractEntity;

public abstract class AbstractDictionary extends AbstractEntity {

    public abstract <T extends Enum<T>> T getValue();
}
