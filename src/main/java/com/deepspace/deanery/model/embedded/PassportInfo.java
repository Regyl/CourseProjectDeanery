package com.deepspace.deanery.model.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PassportInfo {

    @NotNull
    @Column(nullable = false)
    private String series;

    @NotNull
    @Column(nullable = false)
    private String number;

    @Override
    public String toString() {
        return String.join(" ", series, number);
    }
}
