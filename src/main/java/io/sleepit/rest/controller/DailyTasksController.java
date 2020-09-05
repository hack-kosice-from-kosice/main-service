package io.sleepit.rest.controller;

import io.sleepit.rest.dto.DailyTasksDTO;
import io.sleepit.rest.dto.TaskActionDTO;
import io.sleepit.tasks.model.PersistedTask;
import io.sleepit.tasks.repository.TasksFetchOperations;
import io.sleepit.tasks.service.MarkTaskAsAchievedAction;
import io.sleepit.tasks.service.MarkTaskAsRejectedAction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class DailyTasksController {

    private final TasksFetchOperations tasksFetchOperations;
    private final MarkTaskAsAchievedAction markTaskAsAchievedAction;
    private final MarkTaskAsRejectedAction markTaskAsRejectedAction;

    public DailyTasksController(
            final TasksFetchOperations tasksFetchOperations,
            final MarkTaskAsAchievedAction markTaskAsAchievedAction,
            final MarkTaskAsRejectedAction markTaskAsRejectedAction) {

        this.tasksFetchOperations = Objects.requireNonNull(tasksFetchOperations, "tasksFetchOperations can not be null");
        this.markTaskAsAchievedAction = Objects.requireNonNull(markTaskAsAchievedAction, "markTaskAsAchievedAction can not be null");
        this.markTaskAsRejectedAction = Objects.requireNonNull(markTaskAsRejectedAction, "markTaskAsRejectedAction can not be null");
    }

    @GetMapping("/users/{userId}/daily-tasks")
    public DailyTasksDTO allUserDailyTasks(
            @PathVariable("userId") final Integer userId) {

        return new DailyTasksDTO(
                tasksFetchOperations.findByUser(userId)
        );
    }

    @PostMapping("/users/{userId}/daily-tasks/{taskId}")
    public ResponseEntity<?> allUserDailyTasks(
            @PathVariable("userId") final Integer userId,
            @PathVariable("taskId") final Integer taskId,
            @RequestBody final TaskActionDTO taskAction) {

        final Optional<PersistedTask> taskToPerformAction = tasksFetchOperations.findByUser(userId).stream()
                .filter(task -> task.id().equals(taskId))
                .findFirst();

        if (taskToPerformAction.isPresent()) {
            switch (taskAction.getAction()) {
                case MARK_AS_ACHIEVED:
                    markTaskAsAchievedAction.markAsAchieved(taskId);
                    break;
                case MARK_AS_REJECTED:
                    markTaskAsRejectedAction.markAsRejected(taskId);
                    break;
                default:
                    throw new IllegalStateException("Action not implemented " + taskAction.getAction());
            }

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
