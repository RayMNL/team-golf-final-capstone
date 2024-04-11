package com.techelevator.Recipe;

import java.util.List;
import java.util.Objects;

public class Recipe {

    private int recipeId;
    private String name;
    private List<Ingredients> ingredients;
    private List<String> instructions;
    private String img;
    private String dietTags;

    public Recipe(int recipeId, String name, List<Ingredients> ingredients, List<String> instructions, String img, String dietTags) {
        this.recipeId = recipeId;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.img = img;
        this.dietTags = dietTags;
    }

    public Recipe() {
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDietTags() {
        return dietTags;
    }

    public void setDietTags(String dietTags) {
        this.dietTags = dietTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return recipeId == recipe.recipeId && Objects.equals(name, recipe.name) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(instructions, recipe.instructions) && Objects.equals(img, recipe.img) && Objects.equals(dietTags, recipe.dietTags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, name, ingredients, instructions, img, dietTags);
    }
}
