package io.sleepit.init.configuration.properties;

import io.sleepit.skills.model.DefaultSkill;
import io.sleepit.skills.model.Skill;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "sleep-it")
public class SkillsConfiguration {

    private Map<Skill.Code, SkillData> skills;

    public Map<Skill.Code, SkillData> getSkills() {
        return Collections.unmodifiableMap(skills);
    }

    public List<Skill> getSkillsList() {
        return Collections.unmodifiableList(
                skills.entrySet().stream()
                        .map(entry -> new DefaultSkill(entry.getKey(), entry.getValue().getName(), entry.getValue().getImageUrl()))
                        .collect(Collectors.toList())
        );
    }

    public void setSkills(final Map<Skill.Code, SkillData> skills) {
        this.skills = Objects.requireNonNull(skills, "skills can not be null");
    }

    private static final class SkillData {

        private String name;
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

    }

}
