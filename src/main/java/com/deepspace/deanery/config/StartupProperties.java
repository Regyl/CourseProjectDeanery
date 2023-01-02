package com.deepspace.deanery.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Data
@Configuration
@ConfigurationProperties(value = "application.startup")
@ConditionalOnProperty(name = "application.startup.install", havingValue = "true")
public class StartupProperties {

    private Set<Table> tables;

    private Integer size;

    private Long groupSize;

    public enum Table {
        HUMAN,
        STUDENT,
        INSTRUCTION,
        STUDENT_GROUP,
        INSTRUCTION_GROUP
    }
}
