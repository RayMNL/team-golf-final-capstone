package com.techelevator.Recipe;

import java.util.List;

public class RecipeApiResponse {
    private int id;
    private String title;
    private String image;
    private String imageType;
    private List<String> diets;
    private List<String> ingredients;
    private String instructions;
    private List<ExtendedIngredient> extendedIngredients;

    public RecipeApiResponse(int id, String title, String image, String imageType, List<String> diets,
                             List<String> ingredients, String instructions, List<ExtendedIngredient> extendedIngredients) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.imageType = imageType;
        this.diets = diets;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.extendedIngredients = extendedIngredients;
    }

    public RecipeApiResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public List<String> getDiets() {
        return diets;
    }

    public void setDiets(List<String> diets) {
        this.diets = diets;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }
}
