package com.techelevator.dao;

import com.techelevator.model.Recipe;

import java.security.Principal;
import java.util.List;

public interface RecipeDao {


    void addRecipeToDatabaseAndUsersList(int userId, int recipeId);

    List<Integer> getListOfUsersRecipes(int userId);

    void addCustomRecipeToDatabaseAndUsersList(int userId, int recipeId, String recipeName, String ingredients, String instructions, String imageUrl);
}
