package com.techelevator.controller;

import com.techelevator.Service.RecipeService;
import com.techelevator.dao.JdbcRecipeDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Recipe;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class RecipeController {

    public RecipeController(JdbcUserDao jdbcUserDao, JdbcRecipeDao jdbcRecipeDao) {
        this.jdbcUserDao = jdbcUserDao;
        this.jdbcRecipeDao = jdbcRecipeDao;
    }

    private JdbcRecipeDao jdbcRecipeDao;
    private JdbcUserDao jdbcUserDao;


    @RequestMapping(path = "/recipes/{id}/add-to-list", method = RequestMethod.POST)
    public ResponseEntity addRecipeToUsersList(@PathVariable("id") int recipeId, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = jdbcUserDao.getUserByUsername(username);
            if (user != null) {
                jdbcRecipeDao.addRecipeToDatabaseAndUsersList(user.getId(), recipeId);
                return ResponseEntity.ok("Recipe added to the user's list");
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
    }

    @RequestMapping(path = "/users/recipes", method = RequestMethod.GET)
    public ResponseEntity<List<Integer>> getUserRecipeIds(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = jdbcUserDao.getUserByUsername(username);
            if (user != null) {
                List<Integer> recipeIds = jdbcRecipeDao.getListOfUsersRecipes(user.getId());
                return ResponseEntity.ok(recipeIds);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/add-custom-recipe", method = RequestMethod.POST)
    public ResponseEntity<String> addCustomRecipeToUsersList(@RequestBody Recipe recipe, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = jdbcUserDao.getUserByUsername(username);
            if (user != null) {
                jdbcRecipeDao.addCustomRecipeToDatabaseAndUsersList(user.getId(), recipe);
                return ResponseEntity.ok("Recipe added to the user's list");
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
    }

    @RequestMapping(path = "/users/list-of-custom-recipes", method = RequestMethod.GET)
    public ResponseEntity<List<Recipe>> getUserCustomRecipes(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = jdbcUserDao.getUserByUsername(username);
            if (user != null) {
                List<Recipe> recipes = jdbcRecipeDao.getListOfUsersCustomRecipes(user.getId());
                return ResponseEntity.ok(recipes);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
    }
    @RequestMapping(path = "/users/update-custom-recipe", method = RequestMethod.PUT)
    public ResponseEntity<String> updateACustomRecipe(@RequestBody Recipe recipe) {
        Recipe existingRecipeId = jdbcRecipeDao.getCustomRecipeById(recipe.getRecipeId());
        try {
            if (recipe.getRecipeId() != existingRecipeId.getRecipeId()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recipe Id does not match");
            }
            if (existingRecipeId == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe Not Found");
            }
            jdbcRecipeDao.updateUsersCustomRecipe(recipe);
            return ResponseEntity.ok("Recipe updated successfully");
        } catch (DaoException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating recipe: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @RequestMapping(path = "/custom-recipes/{recipeId}", method = RequestMethod.GET)
    public ResponseEntity<Recipe> getCustomRecipeById(@PathVariable int recipeId) {
        Recipe customRecipe = jdbcRecipeDao.getCustomRecipeById(recipeId);
        if (customRecipe != null) {
            return ResponseEntity.ok(customRecipe);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Custom recipe not found");
        }
    }

}