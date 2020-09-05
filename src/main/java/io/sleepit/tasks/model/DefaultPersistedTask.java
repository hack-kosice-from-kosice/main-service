package io.sleepit.tasks.model;

import io.sleepit.skills.model.PersistedSkill;
import io.sleepit.tasks.model.amount.Amount;

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
    public Integer userId() {
        return delegate.userId();
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
