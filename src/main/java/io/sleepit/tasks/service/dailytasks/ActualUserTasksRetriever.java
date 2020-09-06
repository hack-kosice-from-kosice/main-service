package io.sleepit.tasks.service.dailytasks;

import io.sleepit.tasks.model.PersistedTask;

import java.util.List;

public interface ActualUserTasksRetriever {

    List<PersistedTask> retrieveActualUserTasks(final Integer userId);

}
