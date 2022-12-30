package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.api.StudentController;
import com.deepspace.deanery.model.Student;
import com.deepspace.deanery.repository.AbstractJpaRepository;
import com.deepspace.deanery.repository.StudentRepository;
import com.deepspace.dto.StudentCountResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Student controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentControllerImpl extends AbstractCRUDController<Student> implements StudentController {

    private final StudentRepository repository;

    @GetMapping("/status")
    public List<StudentCountResponse> findAllGroupByStatus() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected AbstractJpaRepository<Student> getRepository() {
        return repository;
    }
}
