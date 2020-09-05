package io.sleepit.init.db.inmemory;

import io.sleepit.model.skill.DefaultSkill;
import io.sleepit.model.skill.Skill;
import io.sleepit.model.skill.Skill.Code;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbComponentConfiguration {

    @Bean
    public SkillsInMemoryRepository skillsDb() {
        return new SkillsInMemoryRepository();
    }

    @Bean
    public CommandLineRunner fillDbWithSkills(final SkillsInMemoryRepository skillsInMemoryRepository) {
        return args -> {
            final Skill drinkWaterSkill = new DefaultSkill(Code.WATER, "Water", "http://google.com");
            final Skill dontWatchTvSkill = new DefaultSkill(Code.NO_TV_PC_SMARTPHONE, "NoTvOrPcOrSmartphone", "http://google.com");
            final Skill bodybuildingSkill = new DefaultSkill(Code.EXERCISE, "Exercise", "http://google.com");
            final Skill sunSkill = new DefaultSkill(Code.SUN, "Sun", "http://google.com");
            final Skill coffeeSkill = new DefaultSkill(Code.NO_COFFEE, "No Coffee", "http://google.com");

            skillsInMemoryRepository.createAll(
                    drinkWaterSkill,
                    dontWatchTvSkill,
                    bodybuildingSkill,
                    coffeeSkill,
                    sunSkill
            );
        };
    }

}
