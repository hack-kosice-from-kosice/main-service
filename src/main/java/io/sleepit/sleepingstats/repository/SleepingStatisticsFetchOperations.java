package io.sleepit.sleepingstats.repository;

import io.sleepit.sleepingstats.model.PersistedSleepingStatistic;

import java.util.List;

public interface SleepingStatisticsFetchOperations {

    List<PersistedSleepingStatistic> findByUser(final Integer userId);

}
