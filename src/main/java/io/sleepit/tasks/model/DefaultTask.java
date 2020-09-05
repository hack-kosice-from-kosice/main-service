package io.sleepit.tasks.model;

import io.sleepit.skills.model.PersistedSkill;
import io.sleepit.tasks.model.amount.Amount;

import java.util.Objects;
import java.util.Optional;

public class DefaultTask implements Task {

    private final PersistedSkill skill;
    private final Integer userId;
    private final Amount amount;
    private final Status status;

    public DefaultTask(final PersistedSkill skill, final Integer userId, final Amount amount, final Status status) {
        this.skill = Objects.requireNonNull(skill, "skill can not be null");
        this.userId = Objects.requireNonNull(userId, "userId can not be null");
        this.amount = amount;
        this.status = Objects.requireNonNull(status, "status can not be null");
    }

    @Override
    public PersistedSkill skill() {
        return skill;
    }

    @Override
    public Integer userId() {
        return userId;
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
