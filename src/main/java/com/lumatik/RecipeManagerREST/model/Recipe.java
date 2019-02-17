package com.lumatik.RecipeManagerREST.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// @Data - Lombok annotation to create all getters, setters, equals, hash, and toString methods based on fields
@Data
// @Entity - JPA annotation to make this object ready for storage in a JPA-based data store
@Entity
public class Recipe {

    // @Id - JPA annotation to indicate primary key
    @Id
    // @GeneratedValue - JPA annotation to automatically populate the id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotBlank
//    @Column(columnDefinition = "VARCHAR", length = 100)
    private String title;
    @NotNull
    @NotBlank
//    @Column(columnDefinition = "VARCHAR", length = 1000)
    private String description;

    public Recipe() {
    }

    public Recipe(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
