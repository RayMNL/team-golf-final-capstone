package com.techelevator.dao;

import com.techelevator.model.Recipe;

import java.util.List;

public interface RecipeDao {


    void addRecipeToDatabaseAndUsersList(int userId, int recipeId);

    List<Integer> getListOfUsersRecipes(int userId);

    Recipe addCustomRecipeToDatabaseAndUsersList(int userId,Recipe recipe);

    List<Recipe> getListOfUsersCustomRecipes(int userId);

    Recipe updateUsersCustomRecipe(Recipe recipe);
    Recipe getCustomRecipeById(int recipeId);

    void deleteCustomRecipeById(int recipeId);
}