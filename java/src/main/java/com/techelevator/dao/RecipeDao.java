package com.techelevator.dao;

import com.techelevator.model.Recipe;

import java.security.Principal;
import java.util.List;

public interface RecipeDao {


    void addRecipeToDatabaseAndUsersList(int userId, int recipeId);

    List<Integer> getListOfUsersRecipes(int userId);
}
