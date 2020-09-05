package io.sleepit.tasks.model.amount;

import java.math.BigDecimal;
import java.time.Duration;

public class DurationAmount implements Amount {

    private final Duration value;

    private DurationAmount(final Duration value) {
        if (value == null || value.toMinutes() <= 0) {
            throw new IllegalArgumentException("value can not be null or less or equal than 0");
        }

        this.value = value;
    }

    @Override
    public BigDecimal value() {
        return new BigDecimal(this.value.toMinutes());
    }

    @Override
    public String unit() {
        return "minute";
    }

    @Override
    public String asHumanReadableString() {
        return value().compareTo(BigDecimal.ONE) == 0 ? value() + " minute" : value() + " minutes";
    }

    public static DurationAmount of(final Duration value) {
        return new DurationAmount(value);
    }

    public static DurationAmount ofMinutes(final int value) {
        return new DurationAmount(Duration.ofMinutes(value));
    }

}
