package io.sleepit.init.configuration.beans;

import io.sleepit.init.configuration.properties.TaskGeneratingProperties;
import io.sleepit.skills.repository.SkillsFetchOperations;
import io.sleepit.tasks.repository.TasksFetchOperations;
import io.sleepit.tasks.repository.TasksPersistOperations;
import io.sleepit.tasks.service.dailytasks.DefaultActualUserTasksRetriever;
import io.sleepit.tasks.service.dailytasks.FixedRequiredNumberOfDailyTasksDetector;
import io.sleepit.tasks.service.dailytasks.RequiredNumberOfDailyTasksDetector;
import io.sleepit.tasks.service.generator.RandomTasksGenerator;
import io.sleepit.tasks.service.generator.TasksGenerator;
import io.sleepit.tasks.service.taskmarking.TaskMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TasksRelatedConfiguration {

    @Bean
    public RandomTasksGenerator randomTasksGenerator(
            final SkillsFetchOperations skillsFetchOperations,
            final TaskGeneratingProperties taskGeneratingProperties) {

        return new RandomTasksGenerator(skillsFetchOperations, taskGeneratingProperties.getTasksValidityUntilNextDayHours(), taskGeneratingProperties.getTasksValidityFor());
    }

    @Bean
    public TaskMarker taskMarker(
            final TasksFetchOperations tasksFetchOperations,
            final TasksPersistOperations tasksPersistOperations) {

        return new TaskMarker(tasksFetchOperations, tasksPersistOperations);
    }

    @Bean
    public RequiredNumberOfDailyTasksDetector requiredNumberOfDailyTasksDetector(final TaskGeneratingProperties taskGeneratingProperties) {
        return new FixedRequiredNumberOfDailyTasksDetector(taskGeneratingProperties.getNumberOfRequiredTasksPerDay());
    }

    @Bean
    public DefaultActualUserTasksRetriever defaultActualUserTasksRetriever(
            final TasksGenerator tasksGenerator,
            final TasksFetchOperations tasksFetchOperations,
            final TasksPersistOperations tasksPersistOperations,
            final RequiredNumberOfDailyTasksDetector requiredNumberOfDailyTasksDetector) {

        return new DefaultActualUserTasksRetriever(tasksGenerator, tasksFetchOperations, tasksPersistOperations, requiredNumberOfDailyTasksDetector);
    }

}
