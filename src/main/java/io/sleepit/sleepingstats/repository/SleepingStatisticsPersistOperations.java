package io.sleepit.sleepingstats.repository;

import io.sleepit.sleepingstats.model.PersistedSleepingStatistic;
import io.sleepit.sleepingstats.model.SleepingStatistic;

import java.util.List;
import java.util.stream.Collectors;

public interface SleepingStatisticsPersistOperations {

    PersistedSleepingStatistic create(final SleepingStatistic sleepingStatistic);

    default List<PersistedSleepingStatistic> createAll(final List<SleepingStatistic> sleepingStatistics) {
        return sleepingStatistics.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

}
