package com.deepspace.deanery.repository;

import com.deepspace.deanery.model.Instruction;
import com.deepspace.dto.ExpulsionPercentageCourseResponse;
import com.deepspace.dto.ExpulsionPercentageYearResponse;
import com.deepspace.dto.projection.ShortInstructionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructionRepository extends AbstractJpaRepository<Instruction> {

    @Query(value = """
select
""", nativeQuery = true)
    List<ExpulsionPercentageCourseResponse> getExpulsionPercentageByCourse();

    @Query(value = """
select
""", nativeQuery = true)
    List<ExpulsionPercentageYearResponse> getExpulsionPercentageByYear(int start, int end);

    @Query(value = """
                    select h.last_name || ' ' || h.first_name || ' ' || h.middle_name as ФИО, i.number as Номер_приказа
                    from human h
                    inner join student s on s.human_id = h.id
                    inner join instruction_students si on s.id = si.students_id
                    inner join instruction i on i.id = (select i1.id from instruction i1 where si.instruction_id = i1.id order by i1.date desc limit 1)
                    group by s.id, ФИО, Номер_приказа
                    """, nativeQuery = true)
    Optional<Instruction> findLastInstructionForEachStudent();

    Page<ShortInstructionDTO> findAllBy(Pageable pageable);
}
