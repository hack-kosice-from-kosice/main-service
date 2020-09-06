package io.sleepit.tasks.service.dailytasks;

public class FixedRequiredNumberOfDailyTasksDetector implements RequiredNumberOfDailyTasksDetector {

    private final int numberOfRequiredTasksPerDay;

    public FixedRequiredNumberOfDailyTasksDetector(final int numberOfRequiredTasksPerDay) {
        if (numberOfRequiredTasksPerDay <= 0) {
            throw new IllegalArgumentException("numberOfRequiredTasksPerDay can not be less or equal 0");
        }
        this.numberOfRequiredTasksPerDay = numberOfRequiredTasksPerDay;
    }

    @Override
    public int requiredNumberOfTasksPerDay(final Integer userId) {
        return numberOfRequiredTasksPerDay;
    }

}
