package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface AbstractJpaRepository<T extends AbstractEntity> extends JpaRepository<T, UUID> {
}
