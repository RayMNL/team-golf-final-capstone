package com.techelevator.dao;

import com.techelevator.model.Recipe;
public interface RecipeDao {

Recipe getRecipeById(int recipeId);

int addRecipeToUserLibrary(int recipeId, int UserId);

Recipe[] getRecipeByUserId(int userId);
}
