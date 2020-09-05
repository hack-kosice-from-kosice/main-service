package io.sleepit.model.task;

import io.sleepit.model.skill.PersistedSkill;
import io.sleepit.model.task.amount.Amount;

import java.util.Objects;
import java.util.Optional;

public class DefaultTask implements Task {

    private final PersistedSkill skill;
    private final Amount amount;
    private final Status status;

    public DefaultTask(final PersistedSkill skill, final Amount amount, final Status status) {
        this.skill = Objects.requireNonNull(skill, "skill can not be null");
        this.amount = amount;
        this.status = Objects.requireNonNull(status, "status can not be null");
    }

    public DefaultTask(final PersistedSkill skill, final Status status) {
        this(skill, null, status);
    }

    @Override
    public PersistedSkill skill() {
        return skill;
    }

    @Override
    public Optional<Amount> amount() {
        return amount == null ? Optional.empty() : Optional.of(amount);
    }

    @Override
    public Status status() {
        return status;
    }

}
