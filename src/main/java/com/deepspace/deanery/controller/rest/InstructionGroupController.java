package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.model.InstructionGroup;
import com.deepspace.deanery.repository.AbstractJpaRepository;
import com.deepspace.deanery.repository.InstructionGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
