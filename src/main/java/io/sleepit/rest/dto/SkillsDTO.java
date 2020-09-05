package io.sleepit.rest.dto;

import io.sleepit.skills.model.PersistedSkill;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SkillsDTO {

    private final List<SkillDTO> skills;

    public SkillsDTO(final List<PersistedSkill> skills) {
        this.skills = Objects.requireNonNull(skills, "skills can not be null").stream()
                .map(SkillDTO::new)
                .collect(Collectors.toList());
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

}
