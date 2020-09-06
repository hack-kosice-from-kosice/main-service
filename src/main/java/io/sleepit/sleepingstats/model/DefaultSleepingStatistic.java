package io.sleepit.sleepingstats.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class DefaultSleepingStatistic implements SleepingStatistic {

    private final Integer userId;
    private final Integer value;
    private final ZonedDateTime date;

    public DefaultSleepingStatistic(final Integer userId, final Integer value, final ZonedDateTime date) {
        this.userId = Objects.requireNonNull(userId, "userId can not be null");
        this.value = Objects.requireNonNull(value, "value can not be null");
        if (value < 0 || value > 5) {
            throw new IllegalArgumentException("value must be between 0 and 5 inclusive");
        }
        this.date = Objects.requireNonNull(date, "date can not be null");
    }

    @Override
    public Integer userId() {
        return userId;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public ZonedDateTime date() {
        return date;
    }

}
