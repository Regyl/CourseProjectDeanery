package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.api.StudentService;
import com.deepspace.dto.StudentCountResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Student controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/status")
    public List<StudentCountResponse> findAllGroupByStatus() {
        return new ArrayList<>(); //FIXME
    }

}
