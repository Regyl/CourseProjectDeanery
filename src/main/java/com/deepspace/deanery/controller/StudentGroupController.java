package com.deepspace.deanery.controller;

import com.deepspace.deanery.model.StudentGroup;
import com.deepspace.deanery.repository.AbstractJpaRepository;
import com.deepspace.deanery.repository.StudentGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student-groups")
public class StudentGroupController extends AbstractCRUDController<StudentGroup> {

    private final StudentGroupRepository repository;

    @Override
    protected String getTableFilename() {
        return "student-group-table";
    }

    @Override
    protected AbstractJpaRepository<StudentGroup> getRepository() {
        return repository;
    }

}
