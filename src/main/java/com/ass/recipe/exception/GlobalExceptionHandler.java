package com.ass.recipe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecipeCreationException.class)
    public ResponseEntity<Object> handleRecipeCreationException(RecipeCreationException ex) {
        // Create a response body with the error message and required fields
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(ex.getMessageContent(), ex.getRequiredFields())
        );
    }

    // ErrorResponse is a simple POJO to hold the failure response details
    public static class ErrorResponse {
        private String message;
        private String required;

        public ErrorResponse(String message, String required) {
            this.message = message;
            this.required = required;
        }

        // Getters and Setters
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRequired() {
            return required;
        }

        public void setRequired(String required) {
            this.required = required;
        }
    }
}
