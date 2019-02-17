package com.lumatik.RecipeManagerREST.model;

import com.lumatik.RecipeManagerREST.controller.RecipeController;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RecipeResourceAssembler implements ResourceAssembler<Recipe, Resource<Recipe>> {

    @Override
    // Resource<T> is a generic container from Spring HATEOAS that includes not only the
    // data but a collection of links
    public Resource<Recipe> toResource(Recipe recipe) {

        // linkTo() asks Spring HATEOAS to build a link to the one method and flag it as a self link
        // withRel() asks Spring HATEOAS to build a link to the aggregate root all() and call it
        // "recipes"
        // Link is a core type in Spring HATEOAS. It includes a URI and relation (rel)
        // HATEOAS - Hypermedia As The Engine Of Application State - this is a constraint
        // of REST application architecture - implies that hypertext should be used to find one's way
        // through the API
        // Response from this method with be formatted using HAL - Hypertext Application Language
        // Having all these links allows REST services to evolve over time. Existing links are maintained,
        // while new ones can be added and new vs. legacy clients won't break.
        return new Resource<>(recipe,
                linkTo(methodOn(RecipeController.class).one(recipe.getId())).withSelfRel(),
                linkTo(methodOn(RecipeController.class).all()).withRel("recipes"));
    }
}
