package com.lumatik.RecipeManagerREST.repository;

import com.lumatik.RecipeManagerREST.model.Recipe;
import com.lumatik.RecipeManagerREST.model.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @Slf4j - Lombok annotation to auto-create an Slf4j-based LoggerFactory as log
@Slf4j
public class LoadDatabase {

    @Bean
    // Spring Boot will run all CommandLineRunners once the application context is loaded
    CommandLineRunner initDatabase(RecipeRepository recipeRepository) {
        return args -> {
            log.info("Preloading " + recipeRepository.save(new Recipe("Pizza a la Sniff and Snaff",
                    "Ze besta pizza evaaaa!")));
            log.info("Preloading " + recipeRepository.save(new Recipe("Stuffed Peppers",
                    "Rockin' stuffed peppers sure to be a hit at a dinner party.")));
            log.info("Preloading " + recipeRepository.save(new Recipe("Meatballs",
                    "That's a spicy meataball!", Status.ARCHIVED)));
            log.info("Preloading " + recipeRepository.save(new Recipe("Healthy Chicken Stir Fry",
                    "A healthy take on stir fry.", Status.ARCHIVED)));
        };
    }
}
