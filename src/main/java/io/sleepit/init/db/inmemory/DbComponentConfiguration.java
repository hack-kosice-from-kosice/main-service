package io.sleepit.init.db.inmemory;

import io.sleepit.init.configuration.SkillsConfiguration;
import io.sleepit.skills.model.Skill.Code;
import io.sleepit.skills.repository.SkillsFetchOperations;
import io.sleepit.tasks.model.DefaultTask;
import io.sleepit.tasks.model.Task;
import io.sleepit.tasks.model.amount.DurationAmount;
import io.sleepit.tasks.model.amount.MilliliterAmount;
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
    public TasksInMemoryRepository tasksDb() {
        return new TasksInMemoryRepository();
    }

    @Bean
    public CommandLineRunner fillDbWithSkills(
            final SkillsInMemoryRepository skillsInMemoryRepository,
            final SkillsConfiguration skillsConfiguration) {

        return args -> skillsInMemoryRepository.createAll(
                skillsConfiguration.getSkillsList()
        );
    }

    @Bean
    public CommandLineRunner fillDbWithTasks(final TasksInMemoryRepository tasksInMemoryRepository, final SkillsFetchOperations skillsFetchOperations) {
        return args -> {
            final Task waterTask = new DefaultTask(
                    skillsFetchOperations.getByCode(Code.WATER),
                    123,
                    MilliliterAmount.of(10),
                    Task.Status.ACTIVE
            );

            final Task exerciseTask = new DefaultTask(
                    skillsFetchOperations.getByCode(Code.EXERCISE),
                    123,
                    DurationAmount.ofMinutes(30),
                    Task.Status.ACHIEVED
            );

            final Task bodyBuildingTask = new DefaultTask(
                    skillsFetchOperations.getByCode(Code.NO_TV_PC_SMARTPHONE),
                    123,
                    DurationAmount.ofMinutes(30),
                    Task.Status.REJECTED
            );

            tasksInMemoryRepository.createAll(
                    waterTask,
                    exerciseTask,
                    bodyBuildingTask
            );
        };
    }

}
