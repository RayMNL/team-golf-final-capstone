package com.techelevator.Recipe;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ApiRecipeDao implements RecipeDao{

    private static final String baseUrl = "https://api.spoonacular.com/recipes/";
    private static final String apiKey = "&apiKey=250b663cb5c4495fb447c0c6ab6f005e";

    private static final RestTemplate restTemplate = new RestTemplate();

    public Recipe[] listOfUsersRecipes(id[]) {
        String url = baseUrl + "informationBulk?ids=" ;
        Recipe[] usersRecipes = restTemplate.getForObject(url, Recipe[].class);
        return usersRecipes;

    }

    public String getIdStringForRecipes(Recipe[] recipes) {

        String recipeString = "";
        for(int i =0; i < recipes.length; i++) {
            recipeString += recipes[i] + ",";
        }
        return recipeString;
    }
}
