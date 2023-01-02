package com.deepspace.deanery.model.embedded;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullName {

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @Column
    private String middleName;

    @Override
    public String toString() {
        return Stream.of(lastName, firstName, middleName)
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(" "));
//        return String.join(" ", lastName, firstName, middleName);
    }
}
