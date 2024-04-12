package com.techelevator.controller;

import com.techelevator.Service.RecipeService;
import com.techelevator.dao.JdbcRecipeDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Recipe;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class RecipeController {

    public RecipeController(JdbcUserDao jdbcUserDao, JdbcRecipeDao jdbcRecipeDao, RecipeService recipeService) {
        this.jdbcUserDao = jdbcUserDao;
        this.jdbcRecipeDao = jdbcRecipeDao;
        this.recipeService = recipeService;
    }
private RecipeService recipeService;
private JdbcRecipeDao jdbcRecipeDao;
private JdbcUserDao jdbcUserDao;



    @RequestMapping(path="/recipes/{id}/add-to-list", method = RequestMethod.POST)
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
    public ResponseEntity<List<Recipe>> getUserRecipes(Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = jdbcUserDao.getUserByUsername(username);
            if (user != null) {
                List<Integer> recipeIds = jdbcRecipeDao.getListOfUsersRecipes(user.getId());
                List<Recipe> userRecipes = new ArrayList<>();
                for (int recipeId : recipeIds) {
                    Recipe recipe = recipeService.getRecipeById(recipeId);
                    if (recipe != null) {
                        userRecipes.add(recipe);
                    }
                }
                return ResponseEntity.ok(userRecipes);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
    }

}
