package io.sleepit.sleepingstats.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class DefaultPersistedSleepingStatistic implements PersistedSleepingStatistic {

    private final Integer id;
    private final SleepingStatistic delegate;

    public DefaultPersistedSleepingStatistic(final Integer id, final SleepingStatistic delegate) {
        this.id = Objects.requireNonNull(id, "id can not be null");
        this.delegate = Objects.requireNonNull(delegate, "delegate can not be null");
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public Integer userId() {
        return delegate.userId();
    }

    @Override
    public Integer value() {
        return delegate.value();
    }

    @Override
    public ZonedDateTime date() {
        return delegate.date();
    }

    @Override
    public Category category() {
        return delegate.category();
    }

}
