package io.sleepit.skills.repository;

import io.sleepit.model.skill.PersistedSkill;
import io.sleepit.model.skill.Skill;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface SkillsPersistOperations {

    PersistedSkill create(final Skill skill);

    default List<PersistedSkill> createAll(final Skill... skills) {
        return Arrays.stream(skills)
                .map(this::create)
                .collect(Collectors.toList());
    }

    void update(final PersistedSkill skill);

}
