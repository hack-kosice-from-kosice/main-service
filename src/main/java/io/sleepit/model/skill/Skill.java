package io.sleepit.model.skill;

public interface Skill {

    Code code();

    String name();

    String imageUrl();

    public enum Code {
        WATER, NO_COFFEE, NO_TV_PC_SMARTPHONE, SUN, EXERCISE
    }

}
