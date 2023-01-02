package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Instruction;
import com.deepspace.dto.projection.ExpulsionPercentageCourseResponse;
import com.deepspace.dto.projection.ExpulsionPercentageYearResponse;
import com.deepspace.dto.projection.LastInstructionForEachStudent;
import com.deepspace.dto.projection.ShortInstructionDTO;
import com.deepspace.dto.projection.StudentQuantityByInstructionTypeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructionRepository extends AbstractJpaRepository<Instruction> {

    @Query(value = """
                    with s1 as (select sg.course, count(is2.students_id) as quantity
                    from instruction_students is2
                        inner join instruction i on i.id = is2.instruction_id
                        inner join instruction_type_dic itd on itd.id = i.instruction_type_id
                        inner join student s on s.id = is2.students_id
                        inner join student_group sg on s.student_group_id = sg.id
                    where itd.value='EXPULSION'
                    group by sg.course),
                    s2 as (
                    select sg.course, count(is2.students_id) as quantity
                    from instruction_students is2
                             inner join instruction i on i.id = is2.instruction_id
                             inner join instruction_type_dic itd on itd.id = i.instruction_type_id
                             inner join student s on s.id = is2.students_id
                             inner join student_group sg on s.student_group_id = sg.id
                    where itd.value in ('MOVE_TO_THE_NEXT_COURSE', 'ENROLLMENT', 'EXPULSION')
                    group by sg.course)
                    select s1.quantity*100/s2.quantity as percentage, s1.course
                    from s1
                    left join s2 on s1.course=s2.course
                    order by course desc
                    """, nativeQuery = true)
    List<ExpulsionPercentageCourseResponse> getExpulsionPercentageByCourse();

    @Query(value = """
                    with s1 as (select date_part('year', i.date) as queryYear, count(is2.students_id) as quantity
                    from instruction_students is2
                    inner join instruction i on i.id = is2.instruction_id
                    inner join instruction_type_dic itd on itd.id = i.instruction_type_id
                    where itd.value='EXPULSION'
                    group by queryYear),
                    s2 as (select date_part('year', i.date) as queryYear, count(is2.students_id) as quantity
                            from instruction_students is2
                            inner join instruction i on i.id = is2.instruction_id
                            inner join instruction_type_dic itd on itd.id = i.instruction_type_id
                           where itd.value in ('MOVE_TO_THE_NEXT_COURSE', 'ENROLLMENT', 'EXPULSION')
                           group by queryYear)
                    select s1.quantity*100/s2.quantity as percentage, s1.queryYear from s1
                    left join s2 on s1.queryYear=s2.queryYear
                    order by queryYear desc
                    """, nativeQuery = true)
    List<ExpulsionPercentageYearResponse> getExpulsionPercentageByYear();

    @Query(value = """
                    select h.last_name || ' ' || h.first_name || ' ' || coalesce(h.middle_name, '') as humanName, i.number as instructionNumber, cast(s.id as varchar) as studentId
                    from human h
                    inner join student s on s.human_id = h.id
                    inner join instruction_students si on s.id = si.students_id
                    inner join instruction i on i.id = (select i1.id from instruction i1 where si.instruction_id = i1.id order by i1.date desc limit 1)
                    group by studentId, humanName, instructionNumber
                    """, nativeQuery = true)
    List<LastInstructionForEachStudent> findLastInstructionForEachStudent();

    @Query(value = """
                    select itd.value as instructionType, sgpd.value || '-' || sg.number as groupNo, count(is2.students_id) as quantity
                    from instruction i
                    inner join instruction_type_dic itd on itd.id = i.instruction_type_id
                    inner join instruction_students is2 on i.id = is2.instruction_id
                    inner join student s on is2.students_id = s.id
                    inner join student_group sg on s.student_group_id = sg.id
                    inner join student_group_prefix_dic sgpd on sg.student_group_prefix_id = sgpd.id
                    group by itd.value, sg.id, sgpd.value
                    """, nativeQuery = true)
    List<StudentQuantityByInstructionTypeResponse> quantityOfStudentsForEachInstructionTypeGroupByGroups();

    Page<ShortInstructionDTO> findAllBy(Pageable pageable);
}
