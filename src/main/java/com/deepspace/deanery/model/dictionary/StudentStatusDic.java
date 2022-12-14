package com.deepspace.deanery.model.dictionary;

import com.deepspace.deanery.model.AbstractEntity;
import lombok.Data;
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
public class StudentStatusDic extends AbstractDictionary {

    public StudentStatusDic(Value value) {
        this.value = value;
    }

    @NotNull
    @Column(nullable = false, updatable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Value value;

    public enum Value {
        ACTIVE,
        EXPELLED,
        ACADEMIC_LEAVE,
        GRADUATED
    }
}
