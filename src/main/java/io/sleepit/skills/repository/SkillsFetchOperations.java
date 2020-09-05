package io.sleepit.skills.repository;

import io.sleepit.model.skill.PersistedSkill;
import io.sleepit.model.skill.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillsFetchOperations {

    Optional<PersistedSkill> findByCode(final Skill.Code code);

    List<PersistedSkill> findAll();

    default PersistedSkill getByCode(final Skill.Code code) {
        return findByCode(code).get();
    }

}
