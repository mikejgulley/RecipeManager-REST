package com.lumatik.RecipeManagerREST.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

// @Data - Lombok annotation to create all getters, setters, equals, hash, and toString methods based on fields
@Data
// @Entity - JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
public class Recipe {

    // @Id - JPA annotation to indicate primary key
    @Id
    // @GeneratedValue - JPA annotation to automatically populate the id
    @GeneratedValue
    @ApiModelProperty(notes = "The database generated recipe id")
    private Long id;
    @NotNull
    @NotBlank
//    @Column(columnDefinition = "VARCHAR", length = 100)
    @ApiModelProperty(notes = "The recipe title")
    private String title;
    @NotNull
    @NotBlank
//    @Column(columnDefinition = "VARCHAR", length = 1000)
    @ApiModelProperty(notes = "The recipe description")
    private String description;
    @ApiModelProperty(notes = "The style of cuisine of the recipe")
    private String cuisine;
    @ApiModelProperty(notes = "The list of ingredients of the recipe")
    @ElementCollection
    private List<String> ingredients;
    @ElementCollection
    @ApiModelProperty(notes = "A list of ingredient substitutes")
    private List<String> altIngredients;
    @ElementCollection
    @ApiModelProperty(notes = "The list of required cookware")
    private List<String> cookware;
    @ElementCollection
    @ApiModelProperty(notes = "The list of directions")
    private List<String> directions;
    @ApiModelProperty(notes = "The rating of the recipe")
    private Rating rating;
    @ApiModelProperty(notes = "The difficulty of the recipe")
    private Difficulty difficulty;
    @ApiModelProperty(notes = "The status of the recipe")
    private Status status;
    @ApiModelProperty(notes = "Is the recipe a favorite?")
    private boolean isFavorite;
    @ElementCollection
    @ApiModelProperty(notes = "A list of beverage and other food pairing options")
    private List<String> pairWithOptions;
    @ElementCollection
    @ApiModelProperty(notes = "Notes about the recipe")
    private List<String> notes;
    @ApiModelProperty(notes = "The url of the recipe's picture")
    private String pictureUrl;
    @ApiModelProperty(notes = "The url of the recipe's video")
    private String videoUrl;
    @ApiModelProperty(notes = "The date the recipe was created")
    private LocalDate createDate;
    @ApiModelProperty(notes = "The date the recipe was last made")
    private LocalDate lastMadeDate;
    @ApiModelProperty(notes = "The date the recipe was last updated")
    private LocalDate lastUpdatedDate;

    public Recipe() {
        this.difficulty = Difficulty.NONE;
        this.rating = Rating.NONE;
        this.status = Status.ACTIVE;
        this.createDate = LocalDate.now();
        this.lastUpdatedDate = LocalDate.now();
    }

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
        this.difficulty = Difficulty.NONE;
        this.rating = Rating.NONE;
        this.status = Status.ACTIVE;
        this.createDate = LocalDate.now();
        this.lastUpdatedDate = LocalDate.now();
    }

    public Recipe(String title, String description, Status status) {
        this.title = title;
        this.description = description;
        this.difficulty = Difficulty.NONE;
        this.rating = Rating.NONE;
        this.status = status;
        this.createDate = LocalDate.now();
        this.lastUpdatedDate = LocalDate.now();
    }

//    public Recipe(String title, String description, List<String> ingredients, List<String> directions) {
//        this.title = title;
//        this.description = description;
//        this.difficulty = Difficulty.NONE;
//        this.rating = Rating.NONE;
//        this.ingredients = ingredients;
//        this.directions = directions;
//        this.createDate = LocalDate.now();
//        this.lastUpdatedDate = LocalDate.now();
//    }

    public Recipe(String title, String description, String cuisine,
                  Rating rating,
                  Difficulty difficulty, boolean isFavorite,
                  String pictureUrl, String videoUrl) {
        this.title = title;
        this.description = description;
        this.cuisine = cuisine;
        //this.ingredients = ingredients;
        //this.altIngredients = altIngredients;
        //this.cookware = cookware;
        //this.directions = directions;
        this.difficulty = difficulty;
        this.rating = rating;
        this.isFavorite = isFavorite;
        //this.pairWithOptions = pairWithOptions;
        //this.notes = notes;
        this.pictureUrl = pictureUrl;
        this.videoUrl = videoUrl;
        this.createDate = LocalDate.now();
        this.lastUpdatedDate = LocalDate.now();
    }
}
