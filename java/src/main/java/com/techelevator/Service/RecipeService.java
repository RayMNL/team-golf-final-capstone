package com.techelevator.Service;

import com.techelevator.model.Recipe;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Component
public class RecipeService {
    private static final String API_BASE_URL = "https://api.spoonacular.com/recipes/";
    private static final String API_KEY = "&apiKey=250b663cb5c4495fb447c0c6ab6f005e";

    private RestTemplate restTemplate = new RestTemplate();

    public Recipe getRecipeById(int recipeId) {
        String url = API_BASE_URL + "informationBulk?ids=" + recipeId + API_KEY;
        Recipe recipe = restTemplate.getForObject(url, Recipe.class);
        return recipe;
    }
}
