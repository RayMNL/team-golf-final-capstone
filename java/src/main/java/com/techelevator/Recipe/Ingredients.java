package com.techelevator.Recipe;

import java.util.Objects;

public class Ingredients {

    private int ingredientId;
    private String name;


    public Ingredients(int ingredientId, String name) {
        this.ingredientId = ingredientId;
        this.name = name;
    }

    public Ingredients() {
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return ingredientId == that.ingredientId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, name);
    }
}
