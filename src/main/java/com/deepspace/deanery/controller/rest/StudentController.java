package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.model.Student;
import com.deepspace.deanery.repository.AbstractJpaRepository;
import com.deepspace.deanery.repository.StudentRepository;
import com.deepspace.dto.projection.AcademicRestQuantityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController extends AbstractCRUDController<Student> {

    private final StudentRepository repository;

    @GetMapping("/academic-rest/more-than-one")
    public String findAllTookAcademicRestMoreThanOnce(Model model) {
        List<AcademicRestQuantityResponse> students = repository.findAllTookAcademicRestMoreThanOnce();
        model.addAttribute("entities", students);
        return "academic-rest-table";
    }

    @Override
    protected String getTableFilename() {
        return "student-table";
    }

    @Override
    protected AbstractJpaRepository<Student> getRepository() {
        return repository;
    }
}
