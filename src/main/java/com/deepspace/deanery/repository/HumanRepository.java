package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Human;
import org.springframework.stereotype.Repository;

@Repository
public interface HumanRepository extends AbstractJpaRepository<Human> {
}
