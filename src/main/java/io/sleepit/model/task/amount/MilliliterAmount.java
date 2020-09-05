package io.sleepit.model.task.amount;

import java.math.BigDecimal;

public class MilliliterAmount implements Amount {

    private final BigDecimal value;

    private MilliliterAmount(final BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("value can not be null or less or equal than 0");
        }

        this.value = value;
    }

    @Override
    public BigDecimal value() {
        return this.value;
    }

    @Override
    public String unit() {
        return "milliliter";
    }

    @Override
    public String asHumanReadableString() {
        return value().compareTo(BigDecimal.ONE) == 0 ? value() + " milliliter" : value() + " milliliters";
    }

    public static MilliliterAmount of(final BigDecimal value) {
        return new MilliliterAmount(value);
    }

    public static MilliliterAmount of(final int value) {
        return new MilliliterAmount(new BigDecimal(value));
    }

}
