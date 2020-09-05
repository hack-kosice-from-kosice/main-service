package io.sleepit.tasks.model;

import io.sleepit.skills.model.PersistedSkill;
import io.sleepit.tasks.model.amount.Amount;

import java.util.Optional;

public interface Task {

    PersistedSkill skill();

    Integer userId();

    Optional<Amount> amount();

    Status status();

    enum Status {
        ACTIVE, ACHIEVED, REJECTED
    }

}
