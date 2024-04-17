import axios from "axios";

const baseUrl = "https://api.spoonacular.com/recipes/";
<<<<<<< HEAD
const apiKey= "&apiKey=70bbe58d8c5c49dabf69ce2f4de89e8f";
=======
const apiKey= "&apiKey=3b90dd4f867142c484b9f7f2d9f3c4ff";
>>>>>>> dd7500772eac4f5f29c0864e52a8e99f7fe63650


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
