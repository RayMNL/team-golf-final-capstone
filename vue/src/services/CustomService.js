import axios from "axios";




export default {
    addCustomRecipe() {
        return axios.post(`/recipes/${recipeId}/`);
    },
};