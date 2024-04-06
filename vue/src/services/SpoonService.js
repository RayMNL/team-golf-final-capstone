import axios from "axios";

const baseUrl = "https://api.spoonacular.com/recipes/";
const apiKey= "&apiKey=f739f73488e14c5298d73ab26ddec367"

const newAxiosInstance = axios.create();

export default {

    getRandomRecipes() {
        return newAxiosInstance.get(baseUrl + 'random?number=36' + apiKey)
    }

    

}
