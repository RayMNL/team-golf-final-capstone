<template>
    <div class="container">
        <h1>Add Custom Recipe</h1>
        <div class="card-container">
            <div class="card bg-light mb-3" style="max-width: 100%;">
                <div class="card-header">Custom Recipe</div>
                <div class="card-body">
                    <div class="mb-3">
                        <label for="exampleFormControlInput1" class="form-label">Recipe Name</label>
                        <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="Recipe Name">
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label">Ingredients</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="exampleFormControlTextarea1" class="form-label">Instructions</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                    </div>
                </div>
                <button type="button" class="btn btn-outline-primary" @click="saveRecipe = null" id="save-recipe">Save Recipe</button>
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
        
    },
    methods: {
        SendLocalData() {
            LocalApiService.addCustomRecipe().then(response => {
                this.recipes = response.data.recipes;
                this.getAllTags();
            }).catch(err => console.error(err));
        },
        
        
    }
}
</script>
  
<style>
.container {
    display: grid;
    place-items: center;
    height: 100vh;
}


.card-container {
    width: 100%;
    display: flex;
    justify-content: center;
}

.card {
  max-width: 2000px;
}
</style>