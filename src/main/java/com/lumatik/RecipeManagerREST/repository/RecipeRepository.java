package com.lumatik.RecipeManagerREST.repository;

import com.lumatik.RecipeManagerREST.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // No need to implement or declare anything here
    // Already supports
    // Creating new instances
    // Updating existing ones
    // Deleting
    // Finding (one, all, by simple, or complex properties
}
