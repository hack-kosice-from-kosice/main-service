package io.sleepit.tasks.repository;

import io.sleepit.tasks.model.PersistedTask;

import java.util.List;
import java.util.Optional;

public interface TasksFetchOperations {

    Optional<PersistedTask> findById(final Integer id);

    List<PersistedTask> findByUser(final Integer userId);

    List<PersistedTask> findAll();

    default PersistedTask getById(final Integer id) {
        return findById(id)
                .orElseThrow(() -> new IllegalStateException("No Task with id " + id));
    }

}
