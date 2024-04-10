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
public class JdbcRecipeDao implements RecipeDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        Recipe recipe = null;
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
            if (results.next()) {
                recipe = mapRowToRecipe(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        }
        return recipe;
    }

    @Override
    public int addRecipeToUserLibrary(int recipeId, int UserId) {
        return 0;
    }

    @Override
    public Recipe[] getRecipeByUserId(int userId) {
        return new Recipe[0];
    }

    // Helper methods for our 2 List instance variables in our recipe object
    private List<ExtendedIngredient> getIngredientsForRecipe(int recipeId) {
        String sql = "SELECT ingredients.ingredient_id, ingredients.name " +
                "FROM ingredients " +
                "JOIN recipes_ingredients ON recipes_ingredients.ingredient_id = ingredients.ingredient_id " +
                "WHERE recipes_ingredients.recipe_id = ?";
        List<ExtendedIngredient> ingredients = new ArrayList<>();

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, recipeId);
        while (rows.next()) {
            ingredients.add(new ExtendedIngredient(rows.getString("name")));
        }
        return ingredients;
    }

    private List<String> getInstructionsForRecipe(int recipeId) {
        String sql = "SELECT instructions " +
                "FROM recipes " +
                "WHERE recipe_id = ?";
        List<String> instructions = new ArrayList<>();

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, recipeId);

        while (rows.next()) {
            instructions.add(rows.getString("instructions"));
        }
        return instructions;
    }


    private Recipe mapRowToRecipe(SqlRowSet rs) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(rs.getInt("recipe_id"));
        recipe.setName(rs.getString("name"));
        recipe.setImg(rs.getString("image"));
        recipe.setDietTags(rs.getString("tags"));
        recipe.setIngredients(getIngredientsForRecipe(recipe.getRecipeId()));
        recipe.setInstructions(getInstructionsForRecipe(recipe.getRecipeId()));

        return recipe;
    }
}
