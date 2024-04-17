<template>
    <div class="container">
        <h2>Favorited Recipes</h2>
        <div v-if="selectedRecipe">
            <div class="button-container">
            <div class="button-group">
                <button type="button" class="btn btn-outline-primary smaller-button transparent-button"
                    @click="selectedRecipe = null" id="back-to-recipes-button">Back to Recipes</button>
                <button class="button smaller-button transparent-button" @click="printRecipe">Print Recipe</button>
            </div>
        </div>
            <div id="success-message" v-if="showMessage" class="success-banner">
                {{ message }}
            </div>
            <div id="success-message" v-if="showDeleteMessage" class="success-banner">
                {{ deleteMessage }}
            </div>
            <div class="full-recipe">
                <img :src="selectedRecipe.image" :alt="selectedRecipe.title" class="recipe-image">
                <div class="recipe-details">
                    <h2 class="recipe-title">{{ selectedRecipe.title }}</h2>
                    <div class="recipe-tags">
                        <span v-if="selectedRecipe.vegetarian" class="tag">Vegetarian</span>
                        <span v-if="selectedRecipe.vegan" class="tag">Vegan</span>
                        <span v-if="selectedRecipe.glutenFree" class="tag">Gluten Free</span>
                        <span v-if="selectedRecipe.dairyFree" class="tag">Dairy Free</span>
                    </div>
                    <h3>Ingredients:</h3>
                    <ul>
                        <li v-for="ingredient in selectedRecipe.extendedIngredients" :key="ingredient.id">
                            {{ ingredient.original }}
                        </li>
                    </ul>
                    <h3>Instructions:</h3>
                    <ol>
                        <li v-for="(step, index) in selectedRecipe.analyzedInstructions[0].steps" :key="index">
                            {{ step.step }}
                        </li>
                    </ol>
                </div>
            </div>
        </div>
        <div v-else>
            <div class="recipe-container">
                <div v-if="filteredRecipes.length > 0" v-for="recipe in filteredRecipes" :key="recipe.id"
                    class="recipe-card" @click="showRecipe(recipe)">
                    <img v-if="recipe.image" :src="recipe.image" :alt="recipe.title" class="recipe-image">
                    <div class="recipe-details">
                        <div class="delete-custom-button" @click="deleteFromFavorite(recipe)"><span
                                class="button-text">x</span></div>
                        <h2 class="recipe-title">{{ recipe.title }}</h2>
                        <div class="recipe-tags">
                            <span v-for="diet in recipe.diets" :key="diet" class="tag">{{ diet }}</span>
                        </div>
                    </div>
                </div>
                <div v-else>
                    <p>No recipes with images found.</p>
                </div>
            </div>
        </div>
        <h2>Custom Recipes</h2>
        <div class="custom-new-recipes">
            <div v-if="newRecipe && newRecipe.length > 0" class="custom-new-recipes">
                <div v-for="recipe in newRecipe" :key="recipe.recipeId" class="recipe-card"
                    @click="toggleCustomRecipeExpansion(recipe)"
                    :class="{ 'expanded': recipe.showDetails }">
                    <img :src="recipe.img" alt="No Image Available" class="recipe-image">
                    <div class="recipe-details">
                        <h2 class="recipe-title"> {{ recipe.name }}</h2>
                        <div v-if="recipe.showDetails">
                            <h3>Ingredients:</h3>
                            <ul>
                                <li v-for="(ingredient, index) in recipe.ingredients.split(',')" :key="index">{{ ingredient }}</li>
                            </ul>
                            <h3>Instructions:</h3>
                            <ol>
                                <li v-for="(instruction, index) in recipe.instructions.split('.')" :key="index">{{ instruction }}</li>
                            </ol>
                        
                        </div>
                        <button class="button smaller-button transparent-button" @click="getRecipeInfoById(recipe)">Edit Recipe</button>
                        <div class="delete-custom-button" @click="deleteFromLibrary(recipe)"><span
                                class="button-text">x</span></div>
                        <!-- <router-link :to="{ name: 'editRecipe', params: { recipeId: recipe.recipeId }}" class="button" @click="getRecipeInfoById(recipe.recipeId)">Edit Recipe</router-link> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SpoonService from '@/services/SpoonService';
import LocalApiService from '../services/LocalApiService';
import CustomService from '../services/CustomService';
export default {
    data() {
        return {
            recipes: [],
            searchQuery: '',
            selectedTags: [],
            allTags: [],
            selectedRecipe: null,
            isRecipeSelected: false,
            recipeIdString: '',
            newRecipe: {
                recipeId: '',
                name: '',
                ingredients: '',
                instructions: '',
                img: ''
            },
            showMessage: false,
            message: "",
            showDeleteMessage: false,
            deleteMessage: ""
        }
    },
    computed: {
        filteredRecipes() {
            return this.recipes.filter(recipe => {
                const titleMatches = recipe.title.toLowerCase().includes(this.searchQuery.toLowerCase());
                const allTagsMatch = this.selectedTags.every(tag => recipe.diets.includes(tag));
                return titleMatches && allTagsMatch;
            });
        }
    },
    created() {
        this.getDataFromLocal();
    },
    methods: {
        getCustomData() {
            CustomService.getCustomRecipe()
                .then(response => {
                    this.newRecipe = response.data;
                    console.log('This is the newRecipe object: ', this.newRecipe);
                }).catch(err => console.error(err));
        },
        getDataFromLocal() {
            LocalApiService.getLibrary()
                .then(response => {
                    const recipeIDs = response.data;
                    const recipeIDsString = recipeIDs.join(',');
                    this.recipeIdString = recipeIDsString
                    console.log('This is the id string:', recipeIDsString)
                    this.getDataFromSpoon();
                }).catch(err => console.error(err));
        },
        getDataFromSpoon() {
            SpoonService.getListOfRecipes(this.recipeIdString).then(response => {
                this.recipes = response.data;
                this.getAllTags();
                this.getCustomData();
            }).catch(err => console.error(err));
        },
        toggleTag(tag) {
            if (this.isSelected(tag)) {
                this.selectedTags = this.selectedTags.filter(selectedTag => selectedTag !== tag);
            } else {
                this.selectedTags.push(tag);
            }
        },
        isSelected(tag) {
            return this.selectedTags.includes(tag);
        },
        getAllTags() {
            const tagsSet = new Set();
            this.recipes.forEach(recipe => {
                recipe.diets.forEach(diet => tagsSet.add(diet));
            });
            this.allTags = Array.from(tagsSet);
        },
        showRecipe(recipe) {
            this.selectedRecipe = recipe;
            this.isRecipeSelected = true;
        },
        toggleCustomRecipeExpansion(recipe) {
            recipe.showDetails = !recipe.showDetails;
        },
        getRecipeInfoById(recipe){
          const recipeId = this.$route.params.recipeId;
          CustomService.getRecipeById(recipe.recipeId)
                .then(response => {
                    this.newRecipe = response.data;
                    this.$store.state.selectedRecipe=response.data;
                    this.$router.push({ name: 'editRecipe', params: { recipeId: recipe.recipeId }})
                    console.log('This is the newRecipe object we are passing : ', this.newRecipe);
                }).catch(err => console.error(err));
        },
        printRecipe() {
            window.print(); // This will trigger the browser's print dialog for the detailed recipe card
        },

deleteFromLibrary(recipe) {
    console.log("delete from library of list recipes view: ", recipe.recipeId)
    CustomService.deleteRecipeById(recipe.recipeId)
        .then(response => {
            console.log(response.data);
            window.location.reload();
        })
        .catch(error => {
            console.error('Error deleting recipe from library:', error);
        });
    this.deleteMessage = "Recipe Deleted From Library";
    this.showDeleteMessage = true;

},
deleteFromFavorite(selectedRecipe) {
    const favoriteId = selectedRecipe.id;
    console.log("delete from library of list recipes view: ", favoriteId)
    console.log("recipe object: ",selectedRecipe);
    LocalApiService.deleteFavoriteRecipe(favoriteId)
        .then(response => {
            console.log(response.data);
            window.location.reload();
        })
        .catch(error => {
            console.error('Error deleting recipe from library:', error);
        });
    this.deleteMessage = "Recipe Deleted From Library";
    this.showDeleteMessage = true;

}

    }
}
</script>

<style scoped>
.delete-custom-button {
    position: absolute;
    top: 0;
    right: 0;
    width: 20px;
    height: 20px;
    line-height: 20px;
    text-align: center;
    border-radius: 0;
    background-color: #f00;
    color: #fff;
    cursor: pointer;
    opacity: 0;
    transition: opacity 0.3s ease-in-out;
}
 .delete-custom-button:hover {
    opacity: 1;
}
.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

.recipe-container,
.custom-new-recipes {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
    gap: 20px;
    margin-right: auto;
}

.recipe-card,
.custom-recipe-card {
    position: relative; /* Add relative positioning */
    width: calc(25% - 75px); /* Set initial width */
    margin-bottom: 20px;
    border: 1px solid rgb(204, 204, 204);
    border-radius: 5px;
    overflow: hidden;
    padding: 10px;
    cursor: pointer;
    transition: all 0.3s; /* Add transition for smooth effect */
    background-color: #40E0D0; /* Blue background color */
}

.custom-recipe-card.expanded {
    position: absolute; /* Change to absolute positioning */
    width: 100%; /* Expand to full width */
    z-index: 1; /* Ensure the expanded card is above other elements */
}

.recipe-image,
.custom-recipe-image {
    width: 100%;
    height: 200px;
    object-fit: cover;
    max-width: 100%;
    display: block;
    border: 5px rgb(204, 204, 204);
    border-radius: 5px;
    margin-bottom: 10px;
    /* min-width: 200px; */
    min-height: 150px;
    /* max-width: 200px; */
    max-height: 200px;
}

.recipe-details,
.custom-recipe-details {
    padding: 10px;
}

.recipe-title{
    font-size: 25px;
}

h2 {
    font-family: 'Clarkson Script', cursive;
}

h3{
    font-size: 18px;
}
.custom-recipe-title {
    margin: 0;
}

.recipe-tags,
.custom-recipe-tags {
    margin-top: 10px;
}

.tag {
    display: inline-block;
    margin-right: 5px;
    padding: 3px 8px;
    background-color: #4682B4;
    border-radius: 15px;
    font-size: 12px;
    color: #DDDDDD;
    cursor: pointer;
    font-family: 'Clarkson Script', cursive;
}

.tag.selected {
    background-color: #4682B4;
    color: #fff;
}

.recipe-card:hover,
.custom-recipe-card:hover {
    box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.2); /* Add box-shadow on hover */
    transform: translate(2px, 2px); /* Move the card 2px down and 2px right */
}

.full-recipe,
.custom-full-recipe {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border-radius: 5px;
    background-color: #40E0D0;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.full-recipe:hover,
.custom-full-recipe:hover {
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.full-recipe .recipe-title,
.custom-full-recipe .custom-recipe-title {
    font-size: 24px;
    margin-bottom: 10px;
}

.full-recipe .recipe-image,
.custom-full-recipe .custom-recipe-image {
    width: 100%;
    max-height: 200px;
    object-fit: cover;
    border-radius: 5px;
    margin-bottom: 10px;
}

.full-recipe .recipe-details,
.custom-full-recipe .custom-recipe-details {
    font-size: 16px;
    line-height: 1.5;
}

.button {
    display: inline-block;
    border-radius: 4px;
    background-color: #F0F0F0;
    border: none;
    color: #4682B4;
    text-align: center;
    font-size: 14px;
    padding: 8px 12px;
    transition: all 0.3s;
    cursor: pointer;
    margin: 5px;
    text-decoration: none;
}

.button:hover {
    background-color: #DDDDDD;
}

.button-container {
    max-width: 610px; /* Adjust the max-width as needed */
    margin:  0 auto; /* Center the container horizontally */
}

.button-group {
    display: flex;
    align-items: center;
    justify-content: center; /* Add this line */
}

.smaller-button.transparent-button {
    flex: 1;
    padding: 8px 16px;
    font-size: 14px;
    background-color: transparent4;
    border: 1px solid #4682B4;
    color: #000;
    height: 38px;
    margin: 5px;
    font-family: 'Clarkson Script', cursive;
    
}

.smaller-button.transparent-button:hover {
    background-color: #4682B4;
    color: #fff;
}

.delete-button {
    background-color: #dc3545; /* Red background color */
    color: #fff; /* White text color */
    border-color: #dc3545; /* Red border color */
}

.delete-button:hover {
    background-color: #c82333; /* Darker red on hover */
    border-color: #bd2130; /* Darker red border on hover */
}
</style>