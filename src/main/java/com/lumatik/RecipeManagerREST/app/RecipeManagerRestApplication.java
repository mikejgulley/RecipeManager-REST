package com.lumatik.RecipeManagerREST.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

// @SpringBootApplication - a meta-annotation that pulls in component scanning, auto-configuration, and property support.
// It will fire up a servlet container and serve up our service.
@SpringBootApplication(scanBasePackages = { "com.lumatik.RecipeManagerREST" })
// @EnableJpaRepositories - tells Spring where to look for JPA repositories
@EnableJpaRepositories(basePackages = { "com.lumatik.RecipeManagerREST.repository" })
// @EnableJpaRepositories - tells Spring where to look for Entities
@EntityScan(basePackages = { "com.lumatik.RecipeManagerREST.model" })
public class RecipeManagerRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeManagerRestApplication.class, args);
	}

}

