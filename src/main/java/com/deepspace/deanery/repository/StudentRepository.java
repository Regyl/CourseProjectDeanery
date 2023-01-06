package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Student;
import com.deepspace.dto.projection.AcademicRestQuantityResponse;
import com.deepspace.dto.projection.StudentGroupChangeResponse;
import com.deepspace.dto.projection.StudentMoveResponse;
import com.deepspace.dto.projection.StudentStatisticsItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends AbstractJpaRepository<Student> {

    @Query(value = """
                    select h.last_name || ' ' || h.first_name || ' ' || coalesce(h.middle_name, '') as fullName,
                           i.number as instructionNumber, i.payload
                    from human h
                    inner join student s on h.id = s.human_id
                    inner join instruction_students si on s.id = si.students_id
                    inner join instruction i on si.instruction_id = i.id
                    inner join instruction_type_dic itd on i.instruction_type_id = itd.id
                    where itd.value='GROUP_CHANGE'
                    and s.id = :studentId
                    group by s.id, fullName, instructionNumber, payload
                    """, nativeQuery = true)
    StudentMoveResponse getStudentMoves(@Param("studentId") UUID id);

    @Query(value = """
                    select 'ACTIVE' as key, count(s.id) as value
                    from student s
                    inner join student_status_dic ssd on ssd.id = s.student_status_id
                    where ssd.value='ACTIVE'
                    UNION ALL
                    select 'ACADEMIC_LEAVE' as key, count(s.id) as value
                    from student s
                    inner join student_status_dic ssd on ssd.id = s.student_status_id
                    where ssd.value='ACADEMIC_LEAVE'
                    UNION ALL
                    select 'GRADUATED_FROM_' || cd.value as key, count(s.id) as value
                    from student s
                    inner join student_status_dic ssd on ssd.id = s.student_status_id
                    inner join student_group sg on s.student_group_id = sg.id
                    inner join cathedra_dic cd on cd.id = sg.cathedra_id
                    group by cd.value
                    UNION ALL
                    select 'EXPULSION' as key, count(is2.students_id) as value
                    from instruction i
                    inner join instruction_type_dic itd on itd.id = i.instruction_type_id
                    inner join instruction_students is2 on i.id = is2.instruction_id
                    where itd.value='EXPULSION' and i.date >= now() - INTERVAL '1 YEAR'
                    """, nativeQuery = true)
    List<StudentStatisticsItem> allStudentStatistics();

    @Query(value = """
                    select i.number as instructionNumber, cast(is2.students_id as varchar) as studentId, i.payload as instructionText, h.last_name || ' ' || h.first_name || ' ' || coalesce(h.middle_name, '') as studentName
                    from instruction i
                    inner join instruction_students is2 on i.id = is2.instruction_id
                    inner join instruction_type_dic itd on itd.id = i.instruction_type_id
                    inner join student s on is2.students_id = s.id
                    inner join human h on s.human_id = h.id
                    where itd.value in ('GROUP_CHANGE', 'APPOINT_STUDENT_GROUP')
                    and is2.students_id = #{id}
                    order by i.date
                    """, nativeQuery = true)
    List<StudentGroupChangeResponse> findAllStudentGroupChanges(@Param("id") UUID studentId);

    @Query(value = """
                    select count(is2.students_id) as quantity, cast(is2.students_id as varchar) as id
                    from instruction_students is2
                    inner join instruction i on i.id = is2.instruction_id
                    inner join instruction_type_dic itd on itd.id = i.instruction_type_id
                    where itd.value='ACADEMIC_LEAVE'
                    group by is2.students_id
                    having count(is2.students_id) >= 2
                    """, nativeQuery = true)
    List<AcademicRestQuantityResponse> findAllTookAcademicRestMoreThanOnce();
}
