import axios from "axios";

const baseUrl = "https://api.spoonacular.com/recipes/";
const apiKey= "&apiKey=250b663cb5c4495fb447c0c6ab6f005e";
const searchUrl = "https://api.spoonacular.com/recipes/complexSearch?query=";

const newAxiosInstance = axios.create();

export default {

    getRandomRecipes() {
        return newAxiosInstance.get(baseUrl + 'random?number=100' + apiKey)
    },

    searchForWord(word) {
        return newAxiosInstance.get(searchUrl + word + apiKey)
    }

}
