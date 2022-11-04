package com.deepspace.deanery;

import com.deepspace.deanery.exception.DeaneryException;

public final class DeaneryUtils {

    private DeaneryUtils() {
        throw new DeaneryException(DeaneryException.Reason.NOT_IMPLEMENTED, "Утильный класс");
    }


}
