package io.sleepit.rest.dto;

import io.sleepit.tasks.model.PersistedTask;
import io.sleepit.tasks.model.amount.Amount;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DailyTasksDTO {

    private final List<DailyTaskDTO> dailyTasks;

    public DailyTasksDTO(final List<PersistedTask> tasks) {
        this.dailyTasks = Objects.requireNonNull(tasks, "tasks can not be null")
                .stream().map(this::toDailyTaskDto)
                .collect(Collectors.toList());
    }

    public List<DailyTaskDTO> getDailyTasks() {
        return dailyTasks;
    }

    private DailyTaskDTO toDailyTaskDto(final PersistedTask task) {
        return new DailyTaskDTO(
                task.id(),
                new SkillDTO(task.skill()),
                task.amount().map(Amount::asHumanReadableString).orElse(""),
                task.status().name()
        );
    }

}
