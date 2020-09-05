package io.sleepit.model.skill;

import java.util.Objects;

public class DefaultSkill implements Skill {

    private final String name;
    private final String imageUrl;

    public DefaultSkill(final String name, final String imageUrl) {
        this.name = Objects.requireNonNull(name, "name can not be null");
        this.imageUrl = Objects.requireNonNull(imageUrl, "imageUrl can not be null");
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String imageUrl() {
        return imageUrl;
    }

}
