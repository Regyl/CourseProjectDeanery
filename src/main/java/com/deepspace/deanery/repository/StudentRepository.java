package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends AbstractJpaRepository<Student> {

    @Query("select count(id) from Student where course = ?1 and isDeleted=FALSE")
    Long countAllByCourse(int course);

    @Query("select count(id) from Student where year(eduStart) = ?1 and isDeleted=FALSE")
    Long countAllByEduStartYear(int year);
}
