package io.sleepit.rest.controller;

import io.sleepit.rest.dto.SkillDTO;
import io.sleepit.rest.dto.SkillsDTO;
import io.sleepit.skills.repository.SkillsFetchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class SkillsController {

    private final SkillsFetchOperations skillsFetchOperations;

    public SkillsController(final SkillsFetchOperations skillsFetchOperations) {
        this.skillsFetchOperations = Objects.requireNonNull(skillsFetchOperations, "skillsFetchOperations can not be null");
    }

    @GetMapping("/skills")
    public SkillsDTO allSkills() {
        return new SkillsDTO(
            skillsFetchOperations.findAll()
        );
    }

    @GetMapping("/skills/{skillId}")
    public SkillDTO oneSkill(@PathVariable("skillId") final Integer skillId) {
        return new SkillDTO(
            skillsFetchOperations.getById(skillId)
        );
    }

}
