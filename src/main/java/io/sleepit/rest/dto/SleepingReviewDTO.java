package io.sleepit.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Optional;

public class SleepingReviewDTO {

    private final Integer value;
    private final String category;

    public SleepingReviewDTO(
            @JsonProperty("value") final Integer value,
            @JsonProperty("category") final String category) {

        this.value = Objects.requireNonNull(value, "value can not be null");
        this.category = category;
        if (value < 0 || value > 10) {
            throw new IllegalArgumentException("value must be between 0 and 10 inclusive");
        }
    }

    public Integer getValue() {
        return value;
    }

    public Optional<String> getCategory() {
        return Optional.ofNullable(category);
    }

}
