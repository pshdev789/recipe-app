package com.ass.recipe.exception;

public class RecipeCreationException extends RuntimeException {
    private final String message;
    private final String required;

    public RecipeCreationException(String message, String required) {
        super(message);
        this.message = message;
        this.required = required;
    }

    public String getMessageContent() {
        return message;
    }

    public String getRequiredFields() {
        return required;
    }
}
