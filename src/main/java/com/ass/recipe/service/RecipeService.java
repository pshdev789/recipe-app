package com.ass.recipe.service;

import com.ass.recipe.exception.RecipeCreationException;
import com.ass.recipe.exception.RecipeNotFoundException;
import com.ass.recipe.reopository.RecipeRepository;
import com.ass.recipe.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional
    public Recipe createRecipe(Recipe recipe) {

        // Validate required fields
        if (recipe.getTitle() == null || recipe.getTitle().isEmpty() ||
                recipe.getMakingTime() == null || recipe.getMakingTime().isEmpty() ||
                recipe.getServes() == null || recipe.getServes().isEmpty() ||
                recipe.getIngredients() == null || recipe.getIngredients().isEmpty() ||
                recipe.getCost() == 0) {

            throw new RecipeCreationException("Recipe creation failed!", "title, making_time, serves, ingredients, cost");
        }

        // Set created and updated times
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setUpdatedAt(LocalDateTime.now());

        return recipeRepository.saveAndFlush(recipe);
    }


    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @Transactional
    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setTitle(updatedRecipe.getTitle());
                    recipe.setMakingTime(updatedRecipe.getMakingTime());
                    recipe.setServes(updatedRecipe.getServes());
                    recipe.setIngredients(updatedRecipe.getIngredients());
                    recipe.setCost(updatedRecipe.getCost());
                    return recipeRepository.save(recipe);
                })
                .orElseThrow(() -> new RecipeNotFoundException("No recipe found"));
    }


    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("No recipe found"));
        recipeRepository.delete(recipe);
    }

}
