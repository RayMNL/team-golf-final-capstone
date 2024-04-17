import axios from "axios";




export default {
    addCustomRecipe(newRecipe) {
        return axios.post(`/users/add-custom-recipe/`,newRecipe);
    },

    getCustomRecipe() {
        return axios.get(`/users/list-of-custom-recipes`);
    },

    getRecipeById(recipeId) {
        return axios.get(`/custom-recipes/${recipeId}`);
    },

    updateRecipe(editedRecipe) {
        return axios.put('/users/update-custom-recipe', editedRecipe);
    },

    deleteRecipeById(recipeId) {
        return axios.delete(`/delete/${recipeId}`);
    }
};