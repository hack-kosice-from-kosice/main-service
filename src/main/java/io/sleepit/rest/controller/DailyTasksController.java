package io.sleepit.rest.controller;

import io.sleepit.rest.dto.DailyTasksDTO;
import io.sleepit.tasks.repository.TasksFetchOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class DailyTasksController {

    private final TasksFetchOperations tasksFetchOperations;

    public DailyTasksController(final TasksFetchOperations tasksFetchOperations) {
        this.tasksFetchOperations = Objects.requireNonNull(tasksFetchOperations, "tasksFetchOperations can not be null");
    }

    @GetMapping("/users/{userId}/daily-tasks")
    public DailyTasksDTO allUserDailyTasks(
            @PathVariable("userId") final Integer userId) {

        return new DailyTasksDTO(
                tasksFetchOperations.findByUser(userId)
        );
    }

}
