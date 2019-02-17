package com.lumatik.RecipeManagerREST.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RecipeNotFoundAdvice {

    // @ResponseBody - this advice is rendered straight into the response body
    @ResponseBody
    // @ExceptionHandler - configures advice to only respond if RecipeNotFoundException is thrown
    @ExceptionHandler(RecipeNotFoundException.class)
    // @ResponseStatus - issue a HTTP 404 status
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String recipeNotFoundHandler(RecipeNotFoundException ex) {
        return ex.getMessage();
    }
}
