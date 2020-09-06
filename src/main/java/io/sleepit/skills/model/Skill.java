package io.sleepit.skills.model;

import java.util.List;
import java.util.Objects;

public interface Skill {

    Code code();

    String name();

    String imageUrl();

    List<Description> descriptions();

    enum Code {
        WATER, NO_COFFEE, NO_TV_PC_SMARTPHONE, SUN, EXERCISE
    }

    class Description {

        private final String description;

        public Description(final String description) {
            this.description = Objects.requireNonNull(description, "description can not be null");
        }

        public String getDescription() {
            return description;
        }

    }

}
