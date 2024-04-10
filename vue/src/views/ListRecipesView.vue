<template>

    <div class="container">
        <h1>Recipes</h1>
        <input type="text" v-model="searchQuery" placeholder="Search recipes..." class="search-bar">
        <div class="tags-container">
            <span class="tag" v-for="tag in allTags" :key="tag" @click="toggleTag(tag)"
                :class="{ 'selected': isSelected(tag) }">{{ tag }}</span>
        </div>
        <div v-if="selectedRecipe">
            <button @click="selectedRecipe = null" id="back-to-recipes-button">Back to Recipes</button>
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

export default {
    data() {
        return {
            recipes: [],
            searchQuery: '',
            selectedTags: [],
            allTags: [],
            selectedRecipe: null
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
        },
        /* addToLibrary(recipeId) {
            SpoonService.getListOfRecipes(recipeId)
                .then(response => {
                    console.log(response.data);
                })
                .catch(error => {
                    console.error('Error adding recipe to library:', error);
                });
        } */
        // ADD THIS UNDER BACK TO RECIPES BUTTON: <button @click="addToLibrary(recipeId)" id="add-to-recipe-library-button">Add to Recipe Library</button>
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
  padding: 3px 6px;
  background-color: #F0F0F0;
  border-radius: 5px;
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
    background-color: rgb(253, 226, 149);
}

div.tags-container {
    margin-bottom: 25px;
}

.full-recipe .recipe-image {
    height: 75%;
    width: 75%;
    object-fit: contain;
}

#back-to-recipes-button {
    margin-bottom: 15px;
    border-radius: 5px;
}

div.recipe-details {
    height: 75%;
    width: 75%;
}
</style>
