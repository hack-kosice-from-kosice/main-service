package io.sleepit.skills.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DefaultSkill implements Skill {

    private final Code code;
    private final String name;
    private final String imageUrl;
    private final List<Description> descriptions;

    public DefaultSkill(final Code code, final String name, final String imageUrl, final List<Description> descriptions) {
        this.code = Objects.requireNonNull(code, "code can not be null");
        this.name = Objects.requireNonNull(name, "name can not be null");
        this.imageUrl = Objects.requireNonNull(imageUrl, "imageUrl can not be null");
        this.descriptions = Objects.requireNonNull(descriptions, "descriptions can not be null");
    }

    public DefaultSkill(final Code code, final String name, final String imageUrl) {
        this(code, name, imageUrl, Collections.emptyList());
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

    @Override
    public List<Description> descriptions() {
        return descriptions;
    }

}
