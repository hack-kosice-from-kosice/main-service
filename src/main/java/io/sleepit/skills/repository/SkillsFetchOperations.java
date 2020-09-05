package io.sleepit.skills.repository;

import io.sleepit.skills.model.PersistedSkill;
import io.sleepit.skills.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillsFetchOperations {

    Optional<PersistedSkill> findById(final Integer id);

    Optional<PersistedSkill> findByCode(final Skill.Code code);

    List<PersistedSkill> findAll();

    default PersistedSkill getByCode(final Skill.Code code) {
        return findByCode(code)
                .orElseThrow(() -> new IllegalStateException("No Skill with code " + code.name()));
    }

    default PersistedSkill getById(final Integer id) {
        return findById(id)
                .orElseThrow(() -> new IllegalStateException("No Skill with id " + id));
    }

}
