package io.sleepit.init.db.inmemory;

import io.sleepit.model.skill.DefaultPersistedSkill;
import io.sleepit.model.skill.PersistedSkill;
import io.sleepit.model.skill.Skill;
import io.sleepit.skills.repository.SkillsFetchOperations;
import io.sleepit.skills.repository.SkillsPersistOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class SkillsInMemoryRepository implements SkillsFetchOperations, SkillsPersistOperations {

    private final AtomicInteger idSeq = new AtomicInteger(1);
    private final List<PersistedSkill> skills = new ArrayList<>();

    @Override
    public Optional<PersistedSkill> findByCode(final Skill.Code code) {
        return skills.stream()
                .filter(skill -> skill.code().equals(code))
                .findFirst();
    }

    @Override
    public List<PersistedSkill> findAll() {
        return Collections.unmodifiableList(skills);
    }

    @Override
    public PersistedSkill create(final Skill skill) {
        final DefaultPersistedSkill newSkill = new DefaultPersistedSkill(idSeq.getAndIncrement(), skill);

        this.skills.add(newSkill);

        return newSkill;
    }

    @Override
    public void update(final PersistedSkill skill) {
        final PersistedSkill alreadyPersistedSkill = this.skills.stream()
                .filter(persistedSkill -> persistedSkill.id().equals(skill.id()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nothing to update. Skill with id " + skill.id() + " is not present in DB"));

        skills.remove(alreadyPersistedSkill);
        skills.add(skill);
    }

}
