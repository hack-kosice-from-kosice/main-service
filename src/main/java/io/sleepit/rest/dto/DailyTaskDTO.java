package io.sleepit.rest.dto;

import java.util.Objects;

public class DailyTaskDTO {

    final Integer id;
    final SkillDTO skill;
    final String amount;
    final String status;
    final String description;

    public DailyTaskDTO(final Integer id, final SkillDTO skill, final String amount, final String status, final String description) {
        this.id = Objects.requireNonNull(id, "id can not be null");
        this.skill = Objects.requireNonNull(skill, "skill can not be null");
        this.status = Objects.requireNonNull(status, "status can not be null");
        this.amount = amount == null ? "" : amount;
        this.description = description == null ? "" : description;
    }

    public Integer getId() {
        return id;
    }

    public SkillDTO getSkill() {
        return skill;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

}
