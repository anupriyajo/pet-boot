package com.anu.pets;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PetRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Pet("Bilbo Baggins", 12, Status.AVAILABLE, List.of())));
            log.info("Preloading " + repository.save(new Pet("Tilbo Taggins", 18, Status.SOLD, List.of())));
        };
    }
}
