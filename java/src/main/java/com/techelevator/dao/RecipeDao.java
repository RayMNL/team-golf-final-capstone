package com.techelevator.dao;

import com.techelevator.model.Recipe;

import java.security.Principal;

public interface RecipeDao {


    void addRecipeToDatabaseAndUsersList(int userId, int recipeId);
}
