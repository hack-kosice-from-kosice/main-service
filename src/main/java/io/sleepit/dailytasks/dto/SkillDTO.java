package io.sleepit.dailytasks.dto;

import java.util.Objects;

public class SkillDTO {

    private final Integer id;
    private final String name;
    private final String imageUrl;

    public SkillDTO(final Integer id, final String name, final String imageUrl) {
        this.id = Objects.requireNonNull(id, "id can not be null");
        this.name = Objects.requireNonNull(name, "name can not be null");
        this.imageUrl = Objects.requireNonNull(imageUrl, "imageUrl can not be null");
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
