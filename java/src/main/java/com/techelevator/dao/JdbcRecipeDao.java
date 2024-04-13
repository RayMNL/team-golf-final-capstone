package com.techelevator.dao;

import com.techelevator.model.ExtendedIngredient;
import com.techelevator.model.Recipe;
import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeDao implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addRecipeToDatabaseAndUsersList(int userId, int recipeId) {
        String sqlToAddRecipeToDatabase = "insert into recipes (recipe_id) values (?)";
        jdbcTemplate.update(sqlToAddRecipeToDatabase, recipeId);

        String sqlToAddRecipeToUserList = "insert into user_recipes (user_id, recipe_id) values (?, ?)";
        jdbcTemplate.update(sqlToAddRecipeToUserList, userId, recipeId);
    }

    @Override
    public List<Integer> getListOfUsersRecipes(int userId) {
        List<Integer> listOfTheUsersRecipesIds = new ArrayList<>();
        String sql = "select recipe_id from user_recipes where user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            int recipeId = results.getInt("recipe_id");
            listOfTheUsersRecipesIds.add(recipeId);
        }
        return listOfTheUsersRecipesIds;
    }

    @Override
    public Recipe addCustomRecipeToDatabaseAndUsersList(int userId, Recipe recipe) {
        String sqlToAddCustomRecipeToDatabase = "INSERT INTO custom_recipes (name, ingredients, instructions, image) VALUES (?, ?, ?, ?) returning custom_recipe_id;";
        int newRecipeId = jdbcTemplate.queryForObject(sqlToAddCustomRecipeToDatabase, int.class, recipe.getName(), recipe.getIngredients(), recipe.getInstructions(), recipe.getImg());
        recipe.setRecipeId(newRecipeId);

        String sqlToAddRecipeToUserList = "INSERT INTO user_custom_recipes (user_id, custom_recipe_id) VALUES (?, ?)";
        jdbcTemplate.update(sqlToAddRecipeToUserList, userId, recipe.getRecipeId());

        return recipe;
    }


    /**
     *
     * @Override
     *     public Recipe addCustomRecipeToDatabaseAndUsersList(int userId, Recipe recipe) {
     *         String sqlToAddCustomRecipeToDatabase = "INSERT INTO recipes (name, ingredients, instructions, image) VALUES (?, ?, ?, ?) returning recipe_id;";
     *         int newRecipeId = jdbcTemplate.queryForObject(sqlToAddCustomRecipeToDatabase, int.class, recipe.getName(), recipe.getIngredients(), recipe.getInstructions(), recipe.getImg());
     *         recipe.setRecipeId(newRecipeId);
     *
     *         String sqlToAddRecipeToUserList = "INSERT INTO user_recipes (user_id, recipe_id) VALUES (?, ?)";
     *         jdbcTemplate.update(sqlToAddRecipeToUserList, userId, recipe.getRecipeId());
     *
     *         return recipe;
     *     }
     */
}


