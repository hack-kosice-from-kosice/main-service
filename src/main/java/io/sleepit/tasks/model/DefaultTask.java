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
    private final ValidityRange validityRange;
    private final String description;

    public DefaultTask(final PersistedSkill skill, final Integer userId, final Amount amount, final Status status, final ValidityRange validityRange, final String description) {
        this.skill = Objects.requireNonNull(skill, "skill can not be null");
        this.userId = Objects.requireNonNull(userId, "userId can not be null");
        this.amount = amount;
        this.status = Objects.requireNonNull(status, "status can not be null");
        this.validityRange = Objects.requireNonNull(validityRange, "validityRange can not be null");
        this.description = description;
    }

    public DefaultTask(final PersistedSkill skill, final Integer userId, final Amount amount, final Status status, final ValidityRange validityRange) {
        this(skill, userId, amount, status, validityRange, null);
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
    public String description() {
        return description == null ? "" : description;
    }

    @Override
    public Status status() {
        return status;
    }

    @Override
    public ValidityRange validityRange() {
        return validityRange;
    }

}
