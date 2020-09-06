package io.sleepit.rest.controller;

import io.sleepit.rest.dto.SleepingReviewDTO;
import io.sleepit.rest.dto.SleepingStatisticDTO;
import io.sleepit.rest.dto.SleepingStatisticsDTO;
import io.sleepit.sleepingstats.model.DefaultSleepingStatistic;
import io.sleepit.sleepingstats.model.SleepingStatistic;
import io.sleepit.sleepingstats.repository.SleepingStatisticsFetchOperations;
import io.sleepit.sleepingstats.repository.SleepingStatisticsPersistOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.sleepit.sleepingstats.model.SleepingStatistic.Category.*;

@RestController
public class SleepingStatisticsController {

    private final SleepingStatisticsPersistOperations sleepingStatisticsPersistOperations;
    private final SleepingStatisticsFetchOperations sleepingStatisticsFetchOperations;

    public SleepingStatisticsController(
            final SleepingStatisticsPersistOperations sleepingStatisticsPersistOperations,
            final SleepingStatisticsFetchOperations sleepingStatisticsFetchOperations) {

        this.sleepingStatisticsPersistOperations = Objects.requireNonNull(sleepingStatisticsPersistOperations, "sleepingStatisticsPersistOperations can not be null");
        this.sleepingStatisticsFetchOperations = Objects.requireNonNull(sleepingStatisticsFetchOperations, "sleepingStatisticsFetchOperations can not be null");
    }

    @PostMapping("/users/{userId}/sleeping-stats")
    public ResponseEntity<?> reviewSleeping(
            @PathVariable("userId") final Integer userId,
            @RequestBody final SleepingReviewDTO sleepingReviewDTO) {

        sleepingStatisticsPersistOperations.create(
                new DefaultSleepingStatistic(
                        userId,
                        sleepingReviewDTO.getValue(),
                        ZonedDateTime.now(),
                        sleepingReviewDTO.getCategory()
                                .map(SleepingStatistic.Category::valueOf)
                                .orElse(OVERALL_STATISTIC)
                )
        );

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{userId}/sleeping-stats")
    public SleepingStatisticsDTO getSleepingStatisticsForUser(
            @PathVariable("userId") final Integer userId) {

        return new SleepingStatisticsDTO(
                sleepingStatisticsFetchOperations.findByUser(userId).stream()
                        .map(SleepingStatisticDTO::new)
                        .sorted(Comparator.comparing(SleepingStatisticDTO::getDate))
                        .collect(Collectors.toList())
        );
    }

}
