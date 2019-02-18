package com.lumatik.RecipeManagerREST.model;

public enum Rating {
    NONE("Not yet rated."),
    ONE_STAR("*"),
    TWO_STARS("**"),
    THREE_STARS("***"),
    FOUR_STARS("****"),
    FIVE_STARS("*****");

    private final String rating;

    Rating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating != null ? rating : NONE.getRating();
    }
}