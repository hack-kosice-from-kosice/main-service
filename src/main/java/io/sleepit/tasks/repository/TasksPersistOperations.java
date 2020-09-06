package io.sleepit.tasks.repository;

import io.sleepit.tasks.model.PersistedTask;
import io.sleepit.tasks.model.Task;

import java.util.List;
import java.util.stream.Collectors;

public interface TasksPersistOperations {

    PersistedTask create(final Task task);

    default List<PersistedTask> createAll(final List<Task> tasks) {
        return tasks.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

    void update(final PersistedTask task);

}
