package io.sleepit.rest.controller;

import io.sleepit.rest.dto.SkillDTO;
import io.sleepit.rest.dto.SkillsDTO;
import io.sleepit.skills.repository.SkillsFetchOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class SkillsController {

    private static final Logger log = LoggerFactory.getLogger(SkillsController.class);

    private final SkillsFetchOperations skillsFetchOperations;

    public SkillsController(final SkillsFetchOperations skillsFetchOperations) {
        this.skillsFetchOperations = Objects.requireNonNull(skillsFetchOperations, "skillsFetchOperations can not be null");
    }

    @GetMapping("/skills")
    public SkillsDTO allSkills() {
        log.info("action=allSkills");

        return new SkillsDTO(
                skillsFetchOperations.findAll()
        );
    }

    @GetMapping("/skills/{skillId}")
    public SkillDTO oneSkill(@PathVariable("skillId") final Integer skillId) {
        log.info("action=oneSkill skillId={}", skillId);

        return new SkillDTO(
                skillsFetchOperations.getById(skillId)
        );
    }

}
