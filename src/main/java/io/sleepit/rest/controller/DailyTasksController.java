package io.sleepit.rest.controller;

import io.sleepit.rest.dto.DailyTasksDTO;
import io.sleepit.rest.dto.TaskActionDTO;
import io.sleepit.tasks.model.PersistedTask;
import io.sleepit.tasks.repository.TasksFetchOperations;
import io.sleepit.tasks.service.dailytasks.ActualUserTasksRetriever;
import io.sleepit.tasks.service.taskmarking.MarkTaskAsAchievedAction;
import io.sleepit.tasks.service.taskmarking.MarkTaskAsRejectedAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
public class DailyTasksController {

    private static final Logger log = LoggerFactory.getLogger(DailyTasksController.class);

    private final TasksFetchOperations tasksFetchOperations;
    private final ActualUserTasksRetriever actualUserTasksRetriever;
    private final MarkTaskAsAchievedAction markTaskAsAchievedAction;
    private final MarkTaskAsRejectedAction markTaskAsRejectedAction;

    public DailyTasksController(
            final TasksFetchOperations tasksFetchOperations,
            final ActualUserTasksRetriever actualUserTasksRetriever,
            final MarkTaskAsAchievedAction markTaskAsAchievedAction,
            final MarkTaskAsRejectedAction markTaskAsRejectedAction) {

        this.tasksFetchOperations = Objects.requireNonNull(tasksFetchOperations, "tasksFetchOperations can not be null");
        this.actualUserTasksRetriever = Objects.requireNonNull(actualUserTasksRetriever, "actualUserTasksRetriever can not be null");
        this.markTaskAsAchievedAction = Objects.requireNonNull(markTaskAsAchievedAction, "markTaskAsAchievedAction can not be null");
        this.markTaskAsRejectedAction = Objects.requireNonNull(markTaskAsRejectedAction, "markTaskAsRejectedAction can not be null");
    }

    @GetMapping("/users/{userId}/daily-tasks")
    public DailyTasksDTO allUserDailyTasks(
            @PathVariable("userId") final Integer userId) {

        log.info("action=allUserDailyTasks userId={}", userId);
        return new DailyTasksDTO(
                actualUserTasksRetriever.retrieveActualUserTasks(userId)
        );
    }

    @PostMapping("/users/{userId}/daily-tasks/{taskId}")
    public ResponseEntity<?> performActionOnTask(
            @PathVariable("userId") final Integer userId,
            @PathVariable("taskId") final Integer taskId,
            @RequestBody final TaskActionDTO taskAction) {

        log.info("action=performActionOnTask userId={} taskId={} action={}", userId, taskId, taskAction.getAction().name());

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

            log.info("action=performActionOnTask result=success userId={} taskId={} action={}", userId, taskId, taskAction.getAction().name());
            return ResponseEntity.noContent().build();
        } else {
            log.info("action=performActionOnTask result=not_found userId={} taskId={} action={}", userId, taskId, taskAction.getAction().name());
            return ResponseEntity.notFound().build();
        }

    }

}
