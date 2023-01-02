package com.deepspace.deanery.model.dictionary;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
@Table(indexes = @Index(columnList = "value"))
@EqualsAndHashCode(callSuper = true)
public class InstructionTypeDic extends AbstractDictionary {

    public InstructionTypeDic(Value value) {
        this.value = value;
    }

    @NotNull
    @Column(nullable = false, updatable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Value value;

    public enum Value {
        ENROLLMENT, // зачисление
        PASS_A_DORM, // получение пропуска на общежитие
        ACADEMIC_LEAVE, // академ. отпуск
        EXPULSION, // отчисление
        GRADUATION, // выпуск
        GROUP_CHANGE, //изменение группы студента
        MOVE_TO_THE_NEXT_COURSE, //перевод на следующий курс
        APPOINT_STUDENT_GROUP // назначение студенту группы
    }
}
