package io.sleepit.init.db.inmemory;

import io.sleepit.sleepingstats.model.DefaultPersistedSleepingStatistic;
import io.sleepit.sleepingstats.model.PersistedSleepingStatistic;
import io.sleepit.sleepingstats.model.SleepingStatistic;
import io.sleepit.sleepingstats.repository.SleepingStatisticsFetchOperations;
import io.sleepit.sleepingstats.repository.SleepingStatisticsPersistOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SleepingStatisticsInMemoryRepository implements SleepingStatisticsFetchOperations, SleepingStatisticsPersistOperations {

    private final AtomicInteger idSeq = new AtomicInteger(1);
    private final List<PersistedSleepingStatistic> sleepingStatistics = new ArrayList<>();

    @Override
    public List<PersistedSleepingStatistic> findByUser(final Integer userId) {
        return sleepingStatistics.stream()
                .filter(sleepingStatistic -> sleepingStatistic.userId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public PersistedSleepingStatistic create(final SleepingStatistic sleepingStatistic) {
        final DefaultPersistedSleepingStatistic statistic = new DefaultPersistedSleepingStatistic(
                idSeq.getAndIncrement(),
                sleepingStatistic
        );

        this.sleepingStatistics.add(statistic);

        return statistic;
    }

    @Override
    public List<PersistedSleepingStatistic> createAll(final List<SleepingStatistic> sleepingStatistics) {
        return sleepingStatistics.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }

}
