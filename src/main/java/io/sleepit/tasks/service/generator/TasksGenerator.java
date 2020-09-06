package io.sleepit.tasks.service.generator;

import io.sleepit.tasks.model.Task;

import java.util.List;

public interface TasksGenerator {

    List<Task> generate(final Integer userId, final Integer numberOfTasks);

}
