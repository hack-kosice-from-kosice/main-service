package io.sleepit.model.skill;

import java.util.Objects;

public class DefaultPersistedSkill implements PersistedSkill {

    private final Integer id;
    private final Skill delegate;

    public DefaultPersistedSkill(final Integer id, final Skill delegate) {
        this.id = Objects.requireNonNull(id, "id can not be null");
        this.delegate = Objects.requireNonNull(delegate, "delegate can not be null");
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public Code code() {
        return delegate.code();
    }

    @Override
    public String name() {
        return delegate.name();
    }

    @Override
    public String imageUrl() {
        return delegate.imageUrl();
    }

}
