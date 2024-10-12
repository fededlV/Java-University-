package spring_boot_beggining.springBoot.model.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRespository respository
    ) {
        return args -> {
            Student federico = new Student(
                                "fedegmail",
                                LocalDate.of(2000, Month.DECEMBER, 30),
                    "federico"
                        );
            Student alex = new Student(
                    "alex",
                    LocalDate.of(2001, Month.DECEMBER, 20),
                    "alexgmail"
            );
            respository.saveAll(
                    List.of(federico, alex)
            );
        };

    }
}
