package io.sleepit.rest.dto;

import io.sleepit.sleepingstats.model.PersistedSleepingStatistic;

import java.time.ZonedDateTime;
import java.util.Objects;

public class SleepingStatisticDTO {

    private final PersistedSleepingStatistic sleepingStatistic;

    public SleepingStatisticDTO(final PersistedSleepingStatistic sleepingStatistic) {
        this.sleepingStatistic = Objects.requireNonNull(sleepingStatistic, "sleepingStatistic can not be null");
    }

    public Integer getValue() {
        return sleepingStatistic.value();
    }

    public ZonedDateTime getDate() {
        return sleepingStatistic.date();
    }

    public String getCategory() {
        return sleepingStatistic.category().name();
    }

}
