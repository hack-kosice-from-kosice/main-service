package io.sleepit.skills.repository;

import io.sleepit.skills.model.PersistedSkill;
import io.sleepit.skills.model.Skill;

import java.util.List;
import java.util.stream.Collectors;

public interface SkillsPersistOperations {

    PersistedSkill create(final Skill skill);

    default List<PersistedSkill> createAll(final List<Skill> skills) {
        return skills.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    void update(final PersistedSkill skill);

}
