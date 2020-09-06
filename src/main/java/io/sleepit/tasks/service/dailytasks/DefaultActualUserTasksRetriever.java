package io.sleepit.tasks.service.dailytasks;

import io.sleepit.tasks.model.PersistedTask;
import io.sleepit.tasks.model.Task;
import io.sleepit.tasks.repository.TasksFetchOperations;
import io.sleepit.tasks.repository.TasksPersistOperations;
import io.sleepit.tasks.service.generator.TasksGenerator;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DefaultActualUserTasksRetriever implements ActualUserTasksRetriever {

    private final TasksGenerator tasksGenerator;
    private final TasksFetchOperations tasksFetchOperations;
    private final TasksPersistOperations tasksPersistOperations;
    private final RequiredNumberOfDailyTasksDetector requiredNumberOfDailyTasksDetector;

    public DefaultActualUserTasksRetriever(
            final TasksGenerator tasksGenerator,
            final TasksFetchOperations tasksFetchOperations,
            final TasksPersistOperations tasksPersistOperations,
            final RequiredNumberOfDailyTasksDetector requiredNumberOfDailyTasksDetector) {

        this.tasksGenerator = Objects.requireNonNull(tasksGenerator, "tasksGenerator can not be null");
        this.tasksFetchOperations = Objects.requireNonNull(tasksFetchOperations, "tasksFetchOperations can not be null");
        this.tasksPersistOperations = Objects.requireNonNull(tasksPersistOperations, "tasksPersistOperations can not be null");
        this.requiredNumberOfDailyTasksDetector = Objects.requireNonNull(requiredNumberOfDailyTasksDetector, "requiredNumberOfDailyTasksDetector can not be null");
    }

    @Override
    public List<PersistedTask> retrieveActualUserTasks(final Integer userId) {
        final int requiredNumberOfDailyTasks = requiredNumberOfDailyTasksDetector.requiredNumberOfTasksPerDay(userId);

        final List<PersistedTask> actuallyValidTasks = tasksFetchOperations.findValidTasksForUser(ZonedDateTime.now(), userId);

        if (actuallyValidTasks.size() >= requiredNumberOfDailyTasks) {
            return actuallyValidTasks;
        } else {
            final List<Task> generatedTasks = tasksGenerator.generate(userId, requiredNumberOfDailyTasks - actuallyValidTasks.size());
            final List<PersistedTask> newlyPersisted = tasksPersistOperations.createAll(generatedTasks);

            final List<PersistedTask> finalTasks = new ArrayList<>(newlyPersisted);
            finalTasks.addAll(actuallyValidTasks);

            return Collections.unmodifiableList(finalTasks);
        }
    }

}
