package io.sleepit.dailytasks.controller;

import io.sleepit.dailytasks.dto.DailyTasksDTO;
import io.sleepit.model.skill.DefaultPersistedSkill;
import io.sleepit.model.skill.DefaultSkill;
import io.sleepit.model.task.DefaultPersistedTask;
import io.sleepit.model.task.DefaultTask;
import io.sleepit.model.task.Task;
import io.sleepit.model.task.amount.DurationAmount;
import io.sleepit.model.task.amount.MilliliterAmount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class DailyTasksController {

    @GetMapping("/users/{userIdentifier}/daily-tasks")
    public DailyTasksDTO allUserDailyTasks(
            @PathVariable("userIdentifier") final String userIdentifier) {

        final DefaultPersistedSkill drinkWaterSkill = new DefaultPersistedSkill(
                1, new DefaultSkill("Drink Water", "http://google.com")
        );

        final DefaultPersistedSkill dontWatchTvSkill = new DefaultPersistedSkill(
                2, new DefaultSkill("Don't watch TV before sleeping", "http://google.com")
        );

        final DefaultPersistedSkill bodybuildingSkill = new DefaultPersistedSkill(
                3, new DefaultSkill("Bodybuilding", "http://google.com")
        );

        return new DailyTasksDTO(
                Arrays.asList(
                        new DefaultPersistedTask(
                                1,
                                new DefaultTask(
                                        drinkWaterSkill,
                                        MilliliterAmount.of(10),
                                        Task.Status.ACTIVE
                                )
                        ),
                        new DefaultPersistedTask(
                                2,
                                new DefaultTask(
                                        dontWatchTvSkill,
                                        DurationAmount.ofMinutes(30),
                                        Task.Status.ACHIEVED
                                )
                        ),
                        new DefaultPersistedTask(
                                3,
                                new DefaultTask(
                                        bodybuildingSkill,
                                        DurationAmount.ofMinutes(30),
                                        Task.Status.REJECTED
                                )
                        )
                )
        );
    }

}
