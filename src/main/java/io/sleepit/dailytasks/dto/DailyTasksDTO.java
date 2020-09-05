package io.sleepit.dailytasks.dto;

import io.sleepit.model.skill.PersistedSkill;
import io.sleepit.model.task.amount.Amount;
import io.sleepit.model.task.PersistedTask;

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
                toSkillDto(task.skill()),
                task.amount().map(Amount::asHumanReadableString).orElse(""),
                task.status().name()
        );
    }

    private SkillDTO toSkillDto(final PersistedSkill skill) {
        return new SkillDTO(
                skill.id(),
                skill.name(),
                skill.imageUrl()
        );
    }

}
