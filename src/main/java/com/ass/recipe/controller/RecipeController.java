package com.ass.recipe.controller;

import com.ass.recipe.exception.RecipeNotFoundException;
import com.ass.recipe.model.Recipe;
import com.ass.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createRecipe(@RequestBody Recipe recipe) {


        Map<String,Object> responseMap = new HashMap<>();
        Recipe responseRecipe = recipeService.createRecipe(recipe);
        responseMap.put("message","Recipe successfully created!");
        responseMap.put("recipe",responseRecipe);


        return ResponseEntity.ok(responseMap);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        try {
            Recipe updated = recipeService.updateRecipe(id, updatedRecipe);

            // Success response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Recipe successfully updated!");
            response.put("recipe", updated);
            return ResponseEntity.ok(response);
        } catch (RecipeNotFoundException ex) {
            // Error response when recipe is not found
            Map<String, Object> response = new HashMap<>();
            response.put("message", ex.getMessage());
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteRecipe(@PathVariable Long id) {
        try {
            recipeService.deleteRecipe(id);

            // Success response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Recipe successfully removed!");
            return ResponseEntity.ok(response);
        } catch (RecipeNotFoundException ex) {
            // Error response when recipe is not found
            Map<String, Object> response = new HashMap<>();
            response.put("message", ex.getMessage());
            return ResponseEntity.status(404).body(response);
        }

    }

}
