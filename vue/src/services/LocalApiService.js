import axios from "axios";
export default {
    addToLibrary(recipeId) {
        return axios.post(`/recipes/${recipeId}/add-to-list`);
    },

    getLibrary() {
        return axios.get('/users/recipes');
    }
};