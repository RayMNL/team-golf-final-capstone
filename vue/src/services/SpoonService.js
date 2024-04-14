import axios from "axios";

const baseUrl = "https://api.spoonacular.com/recipes/";
<<<<<<< HEAD
const apiKey= "&apiKey=cd2c9521530b4c31bd45126b77f2cdcf";
=======
const apiKey= "&apiKey=250b663cb5c4495fb447c0c6ab6f005e";
>>>>>>> 910e19a8d936356b2ed431ec9b6c7c42de11a1a0


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
