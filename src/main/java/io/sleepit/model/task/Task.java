package io.sleepit.model.task;

import io.sleepit.model.skill.PersistedSkill;
import io.sleepit.model.task.amount.Amount;

import java.util.Optional;

public interface Task {

    PersistedSkill skill();

    Optional<Amount> amount();

    Status status();

    enum Status {
        ACTIVE, ACHIEVED, REJECTED
    }

}
