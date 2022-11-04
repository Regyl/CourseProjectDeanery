package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends AbstractJpaRepository<Student> {

    Long countAllByCourse(int course);
}
