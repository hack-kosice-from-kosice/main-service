package io.sleepit.init.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
@ConfigurationProperties(prefix = "sleep-it.tasks-generating")
public class TaskGeneratingProperties {

    private Integer numberOfRequiredTasksPerDay;

    private Integer tasksValidityUntilNextDayHours;

    @DurationUnit(value = ChronoUnit.HOURS)
    private Duration tasksValidityFor;

    public Integer getNumberOfRequiredTasksPerDay() {
        return numberOfRequiredTasksPerDay;
    }

    public void setNumberOfRequiredTasksPerDay(final Integer numberOfRequiredTasksPerDay) {
        this.numberOfRequiredTasksPerDay = numberOfRequiredTasksPerDay;
    }

    public Integer getTasksValidityUntilNextDayHours() {
        return tasksValidityUntilNextDayHours;
    }

    public void setTasksValidityUntilNextDayHours(final Integer tasksValidityUntilNextDayHours) {
        this.tasksValidityUntilNextDayHours = tasksValidityUntilNextDayHours;
    }

    public Duration getTasksValidityFor() {
        return tasksValidityFor;
    }

    public void setTasksValidityFor(final Duration tasksValidityFor) {
        this.tasksValidityFor = tasksValidityFor;
    }

}
