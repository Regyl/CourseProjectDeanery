package com.deepspace.deanery.controller;

import com.deepspace.deanery.model.InstructionGroup;
import com.deepspace.deanery.repository.AbstractJpaRepository;
import com.deepspace.deanery.repository.InstructionGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/instruction-groups")
public class InstructionGroupController extends AbstractCRUDController<InstructionGroup> {

    private final InstructionGroupRepository repository;

    @Override
    protected String getTableFilename() {
        return "instruction-group-table";
    }

    @Override
    protected AbstractJpaRepository<InstructionGroup> getRepository() {
        return repository;
    }
}
