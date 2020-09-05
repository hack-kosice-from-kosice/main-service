package io.sleepit.model.task;

import io.sleepit.model.skill.PersistedSkill;
import io.sleepit.model.task.amount.Amount;

import java.util.Objects;
import java.util.Optional;

public class DefaultPersistedTask implements PersistedTask {

    private final Integer id;
    private final Task delegate;

    public DefaultPersistedTask(final Integer id, final Task delegate) {
        this.id = Objects.requireNonNull(id, "id can not be null");
        this.delegate = Objects.requireNonNull(delegate, "delegate can not be null");
    }

    @Override
    public Integer id() {
        return this.id;
    }

    @Override
    public PersistedSkill skill() {
        return delegate.skill();
    }

    @Override
    public Optional<Amount> amount() {
        return delegate.amount();
    }

    @Override
    public Status status() {
        return delegate.status();
    }

}
