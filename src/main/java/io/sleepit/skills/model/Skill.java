package io.sleepit.skills.model;

public interface Skill {

    Code code();

    String name();

    String imageUrl();

    enum Code {
        WATER, NO_COFFEE, NO_TV_PC_SMARTPHONE, SUN, EXERCISE
    }

}
