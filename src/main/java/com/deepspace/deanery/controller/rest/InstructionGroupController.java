package com.deepspace.deanery.controller.rest;

import com.deepspace.deanery.model.InstructionGroup;
import com.deepspace.deanery.repository.AbstractJpaRepository;
import com.deepspace.deanery.repository.InstructionGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InstructionGroupController extends AbstractCRUDController<InstructionGroup> {

    private final InstructionGroupRepository repository;

    @Override
    protected AbstractJpaRepository<InstructionGroup> getRepository() {
        return repository;
    }
}
