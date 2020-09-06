package io.sleepit.init.configuration.beans;

import io.sleepit.init.configuration.properties.SkillsConfiguration;
import io.sleepit.init.db.inmemory.SkillsInMemoryRepository;
import io.sleepit.init.db.inmemory.SleepingStatisticsInMemoryRepository;
import io.sleepit.init.db.inmemory.TasksInMemoryRepository;
import io.sleepit.sleepingstats.model.DefaultSleepingStatistic;
import io.sleepit.sleepingstats.repository.SleepingStatisticsPersistOperations;
import io.sleepit.tasks.service.generator.TasksGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.util.Random;
import java.util.stream.IntStream;

@Configuration
public class DbComponentConfiguration {

    @Bean
    public SkillsInMemoryRepository skillsRepository() {
        return new SkillsInMemoryRepository();
    }

    @Bean
    public TasksInMemoryRepository tasksRepository() {
        return new TasksInMemoryRepository();
    }

    @Bean
    public SleepingStatisticsInMemoryRepository sleepingStatisticsRepository() {
        return new SleepingStatisticsInMemoryRepository();
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
    public CommandLineRunner fillDbWithTasks(final TasksInMemoryRepository tasksInMemoryRepository, final TasksGenerator tasksGenerator) {
        return args -> tasksInMemoryRepository.createAll(
                tasksGenerator.generate(123, 3)
        );
    }

    @Bean
    public CommandLineRunner fillDbWithSleepingStatistics(final SleepingStatisticsPersistOperations sleepingStatisticsPersistOperations) {
        return args -> IntStream.range(0, 100)
                .mapToObj(i -> new DefaultSleepingStatistic(
                        123,
                        new Random().nextInt(5),
                        ZonedDateTime.now().minusDays(i))
                ).forEach(sleepingStatisticsPersistOperations::create);
    }

}
