package io.sleepit.rest.dto;

import java.util.List;
import java.util.Objects;

public class SleepingStatisticsDTO {

    private final List<SleepingStatisticDTO> sleepingStatistics;

    public SleepingStatisticsDTO(final List<SleepingStatisticDTO> sleepingStatistics) {
        this.sleepingStatistics = Objects.requireNonNull(sleepingStatistics, "sleepingStatistics can not be null");
    }

    public List<SleepingStatisticDTO> getSleepingStatistics() {
        return sleepingStatistics;
    }

}
