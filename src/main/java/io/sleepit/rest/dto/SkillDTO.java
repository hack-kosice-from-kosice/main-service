package io.sleepit.rest.dto;

import io.sleepit.skills.model.PersistedSkill;

import java.util.Objects;

public class SkillDTO {

    private final PersistedSkill skill;

    public SkillDTO(final PersistedSkill skill) {
        this.skill = Objects.requireNonNull(skill, "skill can not be null");
    }

    public Integer getId() {
        return skill.id();
    }

    public String getCode() {
        return skill.code().name();
    }

    public String getName() {
        return skill.name();
    }

    public String getImageUrl() {
        return skill.imageUrl();
    }

}
