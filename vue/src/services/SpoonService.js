import axios from "axios";

const baseUrl = "https://api.spoonacular.com/recipes/";
const apiKey= "&apiKey=cd2c9521530b4c31bd45126b77f2cdcf";


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
