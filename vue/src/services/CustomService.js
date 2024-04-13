import axios from "axios";




export default {
    addCustomRecipe(newRecipe) {
        return axios.post(`/users/add-custom-recipe/`,newRecipe);
    },
};