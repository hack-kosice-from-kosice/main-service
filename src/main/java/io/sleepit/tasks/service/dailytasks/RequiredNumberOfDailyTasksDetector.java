package io.sleepit.tasks.service.dailytasks;

public interface RequiredNumberOfDailyTasksDetector {

    int requiredNumberOfTasksPerDay(final Integer userId);

}
