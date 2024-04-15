<template>
    <div class="container">
        <h2>Favorited Recipes</h2>

        <div v-if="selectedRecipe">
            <button class="button" @click="selectedRecipe = null" id="back-to-recipes-button">Back to Recipes</button>
            <div id="success-message" v-if="showMessage" class="success-banner">
                {{ message }}
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
                <div v-for="recipe in newRecipe" :key="recipe.recipeId" class="recipe-card" @click="showCustomRecipe(recipe)">
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
        showCustomRecipe(recipe) {
        recipe.showDetails = !recipe.showDetails;
        }
    }
}
</script>

<style scoped>.container {
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
    width: calc(25% - 39px);
    margin-bottom: 20px;
    border: 1px solid rgb(204, 204, 204);
    border-radius: 5px;
    overflow: hidden;
    padding: 10px;
    cursor: pointer;
    transition: all 0.3s; /* Add transition for smooth effect */
    background-color: #40E0D0; /* Blue background color */
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
    min-width: 200px;
    min-height: 150px;
    max-width: 200px;
    max-height: 200px;
}

.recipe-details,
.custom-recipe-details {
    padding: 10px;
}

.recipe-title,
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
    background-color: #DDDDDD;
    border-radius: 15px;
    font-size: 12px;
    cursor: pointer;
}

.tag.selected {
    background-color: #007BFF;
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
    color: #555555;
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
</style>