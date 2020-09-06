package io.sleepit.rest.dto;

import io.sleepit.skills.model.PersistedSkill;
import io.sleepit.skills.model.Skill;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<DescriptionDTO> getDescriptions() {
        return skill.descriptions().stream()
                .map(DescriptionDTO::new)
                .collect(Collectors.toList());
    }

    private static final class DescriptionDTO {

        private final String description;

        private DescriptionDTO(final Skill.Description description) {
            this.description = description.getDescription();
        }

        public String getDescription() {
            return description;
        }

    }

}
