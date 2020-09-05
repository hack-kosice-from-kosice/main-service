package io.sleepit;

import io.sleepit.init.configuration.SkillsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {SkillsConfiguration.class})
public class MainServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MainServiceApplication.class, args);
    }

}
