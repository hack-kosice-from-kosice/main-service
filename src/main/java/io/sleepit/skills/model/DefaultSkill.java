package io.sleepit.skills.model;

import java.util.Objects;

public class DefaultSkill implements Skill {

    private final Code code;
    private final String name;
    private final String imageUrl;

    public DefaultSkill(final Code code, final String name, final String imageUrl) {
        this.code = Objects.requireNonNull(code, "code can not be null");
        this.name = Objects.requireNonNull(name, "name can not be null");
        this.imageUrl = Objects.requireNonNull(imageUrl, "imageUrl can not be null");
    }

    @Override
    public Code code() {
        return code;
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
