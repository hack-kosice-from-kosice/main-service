package io.sleepit.dailytasks.controller;

import io.sleepit.dailytasks.dto.DailyTasksDTO;
import io.sleepit.model.skill.Skill;
import io.sleepit.model.task.DefaultPersistedTask;
import io.sleepit.model.task.DefaultTask;
import io.sleepit.model.task.Task;
import io.sleepit.model.task.amount.DurationAmount;
import io.sleepit.model.task.amount.MilliliterAmount;
import io.sleepit.skills.repository.SkillsFetchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Objects;

@RestController
public class DailyTasksController {

    private final SkillsFetchOperations skillsFetchOperations;

    public DailyTasksController(final SkillsFetchOperations skillsFetchOperations) {
        this.skillsFetchOperations = Objects.requireNonNull(skillsFetchOperations, "skillsFetchOperations can not be null");
    }

    @GetMapping("/users/{userIdentifier}/daily-tasks")
    public DailyTasksDTO allUserDailyTasks(
            @PathVariable("userIdentifier") final String userIdentifier) {

        return new DailyTasksDTO(
                Arrays.asList(
                        new DefaultPersistedTask(
                                1,
                                new DefaultTask(
                                        skillsFetchOperations.getByCode(Skill.Code.WATER),
                                        MilliliterAmount.of(10),
                                        Task.Status.ACTIVE
                                )
                        ),
                        new DefaultPersistedTask(
                                2,
                                new DefaultTask(
                                        skillsFetchOperations.getByCode(Skill.Code.EXERCISE),
                                        DurationAmount.ofMinutes(30),
                                        Task.Status.ACHIEVED
                                )
                        ),
                        new DefaultPersistedTask(
                                3,
                                new DefaultTask(
                                        skillsFetchOperations.getByCode(Skill.Code.NO_TV_PC_SMARTPHONE),
                                        DurationAmount.ofMinutes(30),
                                        Task.Status.REJECTED
                                )
                        )
                )
        );
    }

}
