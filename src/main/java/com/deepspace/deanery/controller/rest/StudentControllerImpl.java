package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.api.StudentController;
import com.deepspace.deanery.api.StudentService;
import com.deepspace.deanery.model.Student;
import com.deepspace.dto.StudentCountResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "Student controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentControllerImpl implements StudentController {

    private final StudentService studentService;

    @GetMapping("/status")
    public List<StudentCountResponse> findAllGroupByStatus() {
        return new ArrayList<>(); //FIXME
    }


    @Override
    public Student create(Student entity) {
        return null;
    }

    @Override
    public Student update(Student entity, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Student get(UUID id) {
        return null;
    }

    @Override
    public Iterable<Student> getAll() {
        return null;
    }
}
