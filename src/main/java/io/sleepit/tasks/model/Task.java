package io.sleepit.tasks.model;

import io.sleepit.skills.model.PersistedSkill;
import io.sleepit.tasks.model.amount.Amount;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

public interface Task {

    PersistedSkill skill();

    Integer userId();

    Optional<Amount> amount();

    Status status();

    ValidityRange validityRange();

    default boolean isValidFor(final ZonedDateTime dateTime) {
        return validityRange().isValidFor(dateTime);
    }

    enum Status {
        ACTIVE, ACHIEVED, REJECTED
    }

    class ValidityRange {

        private final ZonedDateTime validFromInclusive;
        private final ZonedDateTime validToInclusive;

        public ValidityRange(final ZonedDateTime validFromInclusive, final ZonedDateTime validToInclusive) {
            this.validFromInclusive = Objects.requireNonNull(validFromInclusive, "validFromInclusive can not be null");
            this.validToInclusive = Objects.requireNonNull(validToInclusive, "validToInclusive can not be null");
        }

        public ZonedDateTime getValidFromInclusive() {
            return validFromInclusive;
        }

        public ZonedDateTime getValidToInclusive() {
            return validToInclusive;
        }

        public boolean isValidFor(final ZonedDateTime dateTime) {
            return (dateTime.isEqual(validFromInclusive) || dateTime.isAfter(validFromInclusive)) &&
                    (dateTime.isEqual(validToInclusive) || dateTime.isBefore(validToInclusive));
        }

    }

}
