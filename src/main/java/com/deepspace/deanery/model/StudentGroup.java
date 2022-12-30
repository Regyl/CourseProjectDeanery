package com.deepspace.deanery.model;

import com.deepspace.deanery.model.dictionary.CathedraDic;
import com.deepspace.deanery.model.dictionary.StudentGroupPrefixDic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentGroup extends AbstractEntity {

    @NotNull
    @ManyToOne(optional = false)
    private CathedraDic cathedra;

    @NotNull
    @ManyToOne(optional = false)
    private StudentGroupPrefixDic studentGroupPrefix;

    @NotNull
    @Column(nullable = false)
    private Short number;

    @NotNull
    @Column(nullable = false)
    private Integer course;

    @Override
    public String toString() {
        return studentGroupPrefix.getValue().toString() + "-" + number;
    }
}
