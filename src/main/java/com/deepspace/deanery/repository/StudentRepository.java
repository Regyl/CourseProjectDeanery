package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends AbstractJpaRepository<Student> {

    @Query(value = """
                    select h.last_name || ' ' || h.first_name || ' ' || h.middle_name as ФИО,
                           i.number as Номер_приказа, itd.value as Тип_приказа, i.payload
                    from human h
                    inner join student s on h.id = s.human_id
                    inner join instruction_students si on s.id = si.students_id
                    inner join instruction i on si.instruction_id = i.id
                    inner join instruction_type_dic itd on i.instruction_type_id = itd.id
                    where itd.value='GROUP_CHANGE'
                    and s.id = 'f155e357-be3e-4e1d-b646-9240e275b66d'::uuid
                    group by s.id
                    
                    """, nativeQuery = true)
    Student getStudentMoves(UUID id);
}
