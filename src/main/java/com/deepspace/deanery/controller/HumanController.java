package com.deepspace.deanery.controller;

import com.deepspace.deanery.model.Human;
import com.deepspace.deanery.repository.AbstractJpaRepository;
import com.deepspace.deanery.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/humans")
public class HumanController extends AbstractCRUDController<Human> {

    private final HumanRepository repository;

    @Override
    protected String getTableFilename() {
        return "human-table";
    }

    @Override
    protected AbstractJpaRepository<Human> getRepository() {
        return repository;
    }
}
