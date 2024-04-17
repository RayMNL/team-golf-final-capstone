import axios from "axios";

const baseUrl = "https://api.spoonacular.com/recipes/";
const apiKey= "&apiKey=a65719d6498e4c6ebe72b227c62546a8";



const newAxiosInstance = axios.create();


export default {

    getRandomRecipes() {
        return newAxiosInstance.get(baseUrl + 'random?number=100' + apiKey)
    },

    getListOfRecipes(recipeIds) {
        console.log('In get list of recipes in spoon service: ', recipeIds)
        return newAxiosInstance.get(baseUrl + 'informationBulk?ids=' + recipeIds + apiKey)
    }
}
