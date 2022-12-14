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
@EqualsAndHashCode(callSuper = false)
public class CathedraDic extends AbstractDictionary {

    public CathedraDic(Value value) {
        this.value = value;
    }

    @NotNull
    @Column(nullable = false, updatable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Value value;

    public enum Value {
        DIGITAL_TECHNOLOGIES_OF_TRANSPORT_MANAGEMENT,
        COMPUTER_SYSTEMS_NETWORKS_AND_INFORMATION_SECURITY,
        RAILWAY_STATIONS_AND_TRANSPORT_HUBS,
        TRANSPORT_LOGISTICS_AND_MANAGEMENT,
        LOGISTICS_TRANSPORT_SYSTEMS_AND_TECHNOLOGIES,
        TRANSPORT_BUSINESS_MANAGEMENT_AND_INTELLIGENT_SYSTEMS,
        MANAGEMENT_OF_TRANSPORT_OPERATIONS_AND_SAFETY,
        CHEMISTRY_AND_ENGINEERING_ECOLOGY
    }
}
