package com.deepspace.dto.projection;

import java.util.List;
import java.util.UUID;


public interface ShortInstructionDTO {

    UUID getId();

    String getNumber();

    List<ShortStudentDTO> getStudents();
}
