package com.lumatik.RecipeManagerREST.controller;

import com.lumatik.RecipeManagerREST.model.Recipe;
import com.lumatik.RecipeManagerREST.error.RecipeNotFoundException;
import com.lumatik.RecipeManagerREST.model.RecipeResourceAssembler;
import com.lumatik.RecipeManagerREST.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeResourceAssembler recipeResourceAssembler;

    @GetMapping("/recipes")
    // Resources<T> - Spring HATEOAS container that encapsulates collections of type T Resources
    // :: is Java 8 syntax for a method reference
    public Resources<Resource<Recipe>> all() {
        List<Resource<Recipe>> recipes = recipeRepository.findAll().stream()
                .map(recipeResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(recipes,
                linkTo(methodOn(RecipeController.class).all()).withSelfRel());
    }

    @PostMapping("/recipes")
    ResponseEntity<?> newRecipe(@RequestBody Recipe newRecipe) throws URISyntaxException {
        Resource<Recipe> recipeResource = recipeResourceAssembler
                .toResource(recipeRepository.save(newRecipe));

        // ResponseEntity creates a HTTP 201 Created status message
        return ResponseEntity
                .created(new URI(recipeResource.getId().expand().getHref()))
                .body(recipeResource);
    }

    @GetMapping("/recipes/{id}")
    public Resource<Recipe> one(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));

        return recipeResourceAssembler.toResource(recipe);
    }

    @PutMapping("/recipes/{id}")
    Recipe replaceRecipe(@RequestBody Recipe newRecipe, @PathVariable Long id) {

        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setTitle(newRecipe.getTitle());
                    recipe.setDescription(newRecipe.getDescription());
                    return recipeRepository.save(recipe);
                })
                .orElseGet(() -> {
                    newRecipe.setId(id);
                    return recipeRepository.save(newRecipe);
                });
    }

    @DeleteMapping("/recipes/{id}")
    ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        recipeRepository.deleteById(id);

        // Response Entity returns a 204 No Content response
        return ResponseEntity.noContent().build();
    }
}
