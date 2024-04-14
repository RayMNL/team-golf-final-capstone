<template>
    <div class="container">
        <input type="text" v-model="searchQuery" placeholder="Search recipes..." class="search-bar" v-if="!selectedRecipe">
        <div class="tags-container" v-if="!selectedRecipe">
            <label for="filter">Filter: </label>
            <span class="tag" v-for="tag in allTags" :key="tag" @click="toggleTag(tag)"
                :class="{ 'selected': isSelected(tag) }">{{ tag }}</span>
        </div>
        <div v-if="selectedRecipe">
            <button type="button" class="btn btn-outline-primary" @click="selectedRecipe = null" id="back-to-recipes-button">Back to Recipes</button>
            <button class="button" @click="addToLibrary(selectedRecipe.id)" id="add-to-recipe-library-button">Add to Recipe Library</button>
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
    </div>
</template>

<script>
import SpoonService from '@/services/SpoonService'
import LocalApiService from '../services/LocalApiService';

export default {
    data() {
        return {
            recipes: [],
            searchQuery: '',
            selectedTags: [],
            allTags: [],
            selectedRecipe: null,
            isRecipeSelected: false,
            showMessage: false,
            message: ""
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
        this.getDataFromSpoon();
    },
    methods: {
        getDataFromSpoon() {
            SpoonService.getRandomRecipes().then(response => {
                this.recipes = response.data.recipes;
                this.getAllTags();
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
        addToLibrary(recipeId) {
            console.log("add to library of list recipes view: ", recipeId)
            LocalApiService.addToLibrary(recipeId)
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.error('Error adding recipe to library:', error);
                });
                this.message = "Added to Recipe Library";
                this.showMessage = true;

                
                setTimeout(() => {
              this.showMessage = false;
            }, 3000);
        }
    }
}
</script>



<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  
}

.recipe-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  gap: 20px;
  margin-right: auto;
}

.recipe-card {
    width: calc(25% - 39px);
    margin-bottom: 20px;
    border: 1px solid rgb(204, 204, 204);
    border-radius: 5px;
    overflow: hidden;
    padding: 10px;
}

.recipe-image {
    width: 100%;
    height: 200px;
    object-fit: cover;
    max-width: 100%;
    display: block;
    border: 5px rgb(204, 204, 204);
    border-radius: 5px;
    margin-bottom: 10px;
}

.recipe-details {
  padding: 10px;
}

.recipe-title {
  margin: 0;
}

.recipe-tags {
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
  background-color: #007bff;
  color: #fff;
}

.last-row-card {
  width: 100%;

}

.search-bar {
  width: 97%;
  margin-bottom: 20px;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
  align-self: center;
  display: flex;
  align-items: center;
  justify-content: center;
}

div.recipe-card,
div.recipe-details {
    background-color: #40E0D0;
}

div.tags-container {
    margin-bottom: 25px;
}

.full-recipe {
    max-width: 600px; 
    margin: 0 auto;
    padding: 20px;
    border-radius: 5px;
    background-color: #40E0D0; 
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* Hover effect */
.full-recipe:hover {
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2); 
}


.full-recipe .recipe-title {
    font-size: 24px;
    margin-bottom: 10px;
}


.full-recipe .recipe-image {
    width: 100%;
    max-height: 200px; 
    object-fit: cover;
    border-radius: 5px;
    margin-bottom: 10px;
}


.full-recipe .recipe-details {
    font-size: 16px;
    line-height: 1.5;
}


#back-to-recipes-button {
    margin-bottom: 15px;
    border-radius: 5px;
}

div.recipe-details {
    height: 75%;
    width: 75%;
}

.button {
  display: inline-block;
  border-radius: 4px;
  background-color: #f0f0f0; 
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
  background-color: #dddddd; 
}

.success-banner {
    position: fixed; 
    top: 20px; 
    left: 50%;
    transform: translateX(-50%); 
    background-color: #495057;
    color: white;
    padding: 15px 20px;
    border-radius: 5px;
    z-index: 9999;
    text-align: center;
}
</style>
