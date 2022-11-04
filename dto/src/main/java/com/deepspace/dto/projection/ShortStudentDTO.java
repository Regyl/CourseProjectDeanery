package com.deepspace.dto.projection;

import java.util.UUID;

public interface ShortStudentDTO {

    UUID getId();

    FullName getFullName();

    interface FullName {

        String getFirstName();
    }
}
