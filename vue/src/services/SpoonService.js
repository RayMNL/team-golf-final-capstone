import axios from "axios";

const baseUrl = "https://api.spoonacular.com/recipes/";
const apiKey= "&apiKey=250b663cb5c4495fb447c0c6ab6f005e";


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
