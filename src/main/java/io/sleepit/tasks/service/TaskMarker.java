package io.sleepit.tasks.service;

import io.sleepit.tasks.model.DefaultPersistedTask;
import io.sleepit.tasks.model.PersistedTask;
import io.sleepit.tasks.model.Task.Status;
import io.sleepit.tasks.repository.TasksFetchOperations;
import io.sleepit.tasks.repository.TasksPersistOperations;

import java.util.Objects;

public class TaskMarker implements MarkTaskAsAchievedAction, MarkTaskAsRejectedAction {

    private final TasksFetchOperations tasksFetchOperations;
    private final TasksPersistOperations tasksPersistOperations;

    public TaskMarker(
            final TasksFetchOperations tasksFetchOperations,
            final TasksPersistOperations tasksPersistOperations) {

        this.tasksFetchOperations = Objects.requireNonNull(tasksFetchOperations, "tasksFetchOperations can not be null");
        this.tasksPersistOperations = Objects.requireNonNull(tasksPersistOperations, "tasksPersistOperations can not be null");
    }

    @Override
    public void markAsAchieved(final Integer taskId) {
        final PersistedTask originalTask = tasksFetchOperations.getById(taskId);

        if (originalTask.status() == Status.ACTIVE) {
            final PersistedTask taskToStore = DefaultPersistedTask.toBuilder(originalTask)
                    .withStatus(Status.ACHIEVED)
                    .build();

            tasksPersistOperations.update(taskToStore);
        } else {
            throw new IllegalStateException("Unable to achieve task " + taskId + " which is no longer active");
        }
    }

    @Override
    public void markAsRejected(final Integer taskId) {
        final PersistedTask originalTask = tasksFetchOperations.getById(taskId);

        if (originalTask.status() == Status.ACTIVE) {
            final PersistedTask taskToStore = DefaultPersistedTask.toBuilder(originalTask)
                    .withStatus(Status.REJECTED)
                    .build();

            tasksPersistOperations.update(taskToStore);
        } else {
            throw new IllegalStateException("Unable to reject task " + taskId + " which is no longer active");
        }
    }

}
