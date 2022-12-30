package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends AbstractJpaRepository<Student> {

}
