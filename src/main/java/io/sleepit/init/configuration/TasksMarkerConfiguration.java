package io.sleepit.init.configuration;

import io.sleepit.tasks.repository.TasksFetchOperations;
import io.sleepit.tasks.repository.TasksPersistOperations;
import io.sleepit.tasks.service.TaskMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TasksMarkerConfiguration {

    @Bean
    public TaskMarker taskMarker(
            final TasksFetchOperations tasksFetchOperations,
            final TasksPersistOperations tasksPersistOperations) {

        return new TaskMarker(tasksFetchOperations, tasksPersistOperations);
    }

}
