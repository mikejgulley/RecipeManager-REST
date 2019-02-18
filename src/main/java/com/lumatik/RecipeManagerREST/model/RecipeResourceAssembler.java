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
        Resource<Recipe> recipeResource = new Resource<>(recipe,
                linkTo(methodOn(RecipeController.class).one(recipe.getId())).withSelfRel(),
                linkTo(methodOn(RecipeController.class).all()).withRel("recipes"));

        // Hypermedia as the Engine of Application State. Instead of clients parsing the payload,
        // give them links to signal valid actions. Decouple state-based actions from the payload of data.
        // In other words, when ARCHIVE and UNARCHIVE are valid actions, dynamically add them to the list of links.
        // Clients only need show users the corresponding buttons when the links exist.
        // This decouples clients from having to know WHEN such actions are valid, reducing the risk of the server
        // and its clients getting out of sync on the logic of state transitions.
            if (recipe.getStatus() == Status.ACTIVE) {
                recipeResource.add(
                        linkTo(methodOn(RecipeController.class)
                                .archive(recipe.getId())).withRel("archive")
                );
            } else {
                recipeResource.add(
                        linkTo(methodOn(RecipeController.class)
                                .unarchive(recipe.getId())).withRel("unarchive")
                );
            }

            return recipeResource;
        }
}
