package io.sleepit.init.db.inmemory;

import io.sleepit.tasks.model.DefaultPersistedTask;
import io.sleepit.tasks.model.PersistedTask;
import io.sleepit.tasks.model.Task;
import io.sleepit.tasks.repository.TasksFetchOperations;
import io.sleepit.tasks.repository.TasksPersistOperations;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TasksInMemoryRepository implements TasksFetchOperations, TasksPersistOperations {

    private final AtomicInteger idSeq = new AtomicInteger(1);
    private final List<PersistedTask> tasks = new ArrayList<>();

    @Override
    public Optional<PersistedTask> findById(final Integer id) {
        return tasks.stream()
                .filter(task -> task.id().equals(id))
                .findFirst();
    }

    @Override
    public List<PersistedTask> findByUser(final Integer userId) {
        return tasks.stream()
                .filter(task -> task.userId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<PersistedTask> findValidTasksForUser(final ZonedDateTime validatedDate, final Integer userId) {
        return findByUser(userId).stream()
                .filter(task -> task.isValidFor(validatedDate))
                .collect(Collectors.toList());
    }

    @Override
    public PersistedTask create(final Task task) {
        final DefaultPersistedTask newTask = new DefaultPersistedTask(
                idSeq.getAndIncrement(),
                task
        );

        tasks.add(newTask);

        return newTask;
    }

    @Override
    public void update(final PersistedTask task) {
        final PersistedTask alreadyPersistedTask = this.tasks.stream()
                .filter(persistedTask -> persistedTask.id().equals(task.id()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nothing to update. Task with id " + task.id() + " is not present in DB"));

        tasks.remove(alreadyPersistedTask);
        tasks.add(task);
    }



}
