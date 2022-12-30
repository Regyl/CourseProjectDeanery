package com.deepspace.deanery.api;

import com.deepspace.deanery.model.AbstractEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public interface CRUDController<T extends AbstractEntity> {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    T create(@RequestBody T entity);

    @PatchMapping("/update")
    T update(@RequestBody T entity, @RequestParam("id") UUID id);

    @DeleteMapping("/{id}")
    void delete(@PathVariable UUID id);

    @GetMapping("/view/{id}")
    T get(@PathVariable UUID id);

    @GetMapping("/list")
    Iterable<T> getAll();

}
