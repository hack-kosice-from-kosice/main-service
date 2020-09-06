package io.sleepit.sleepingstats.model;

import java.time.ZonedDateTime;

public interface SleepingStatistic {

    Integer userId();

    Integer value();

    ZonedDateTime date();

    Category category();

    enum Category {
        OVERALL_STATISTIC
    }

}
