package io.sleepit.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CreateSleepingStatisticRequestDTO {

    private final Integer value;

    public CreateSleepingStatisticRequestDTO(@JsonProperty("value") final Integer value) {
        this.value = Objects.requireNonNull(value, "value can not be null");
        if (value < 0 || value > 5) {
            throw new IllegalArgumentException("value must be between 0 and 5 inclusive");
        }
    }

    public Integer getValue() {
        return value;
    }

}
