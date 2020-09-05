package io.sleepit.dailytasks.dto;

import java.util.Objects;

public class DailyTaskDTO {

    final Integer id;
    final SkillDTO skill;
    final String description;
    final String status;

    public DailyTaskDTO(final Integer id, final SkillDTO skill, final String description, final String status) {
        this.id = Objects.requireNonNull(id, "id can not be null");
        this.skill = Objects.requireNonNull(skill, "skill can not be null");
        this.status = Objects.requireNonNull(status, "status can not be null");
        this.description = description == null ? "" : description;
    }

    public Integer getId() {
        return id;
    }

    public SkillDTO getSkill() {
        return skill;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

}
