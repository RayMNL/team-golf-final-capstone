<template>
    <div class="container">
        <h1 class="blue-heading">Add Custom Recipe</h1>
        <div class="card-container">
            <div class="card bg-light mb-3">
                <div class="card-header bg-blue text-white">Custom Recipe</div>
                <div class="card-body">
                    <div class="form-group">
                        <label for="recipeName">Recipe Name</label>
                        <input type="text" class="form-control" id="recipeName" v-model.trim="newRecipe.name">
                    </div>
                    <div class="form-group">
                        <label for="ingredients">Ingredients</label>
                        <textarea class="form-control" id="ingredients" rows="3" v-model="newRecipe.ingredients" placeholder="Add a comma after each ingredient..."></textarea>
                    </div>
                    <div class="form-group">
                        <label for="instructions">Instructions</label>
                        <textarea class="form-control" id="instructions" rows="3" v-model="newRecipe.instructions" placeholder="Add a period after each sentence..."></textarea>
                    </div>
                    <div class="form-group">
                        <label for="recipeImage">Recipe Image</label>
                        <input type="text" class="form-control" id="recipeImage" v-model.trim="newRecipe.img">
                    </div>
                </div>
                <div class="card-footer">
                    <button type="button" class="btn btn-outline-primary btn-block bg-blue" @click="addNewRecipe()">Save Recipe</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SpoonService from '@/services/SpoonService'
import LocalApiService from '../services/LocalApiService';
import CustomService from '../services/CustomService';

export default {
    data() {
        return {
            newRecipe: {
                name: '',
                ingredients: '',
                instructions: '',
                img: ''
            }
        }
    },
    methods: {
        addNewRecipe() {
            CustomService.addCustomRecipe(this.newRecipe)
                .then(response => {
                    // Redirect to a specific route
                    this.$router.push({ name: "personal" });
                })
                .catch(err => console.error(err));
        }
    }
}
</script>

<style>
.container {
    display: grid;
    place-items: center;
    height: 100vh;
    font-family: 'Clarkson Script', cursive; 
}
.card-container {
    width: 100%;
    display: flex;
    justify-content: center;
    font-family: 'Clarkson Script', cursive; 
}
.card {
    max-width: 800px;
    width: 100%;
    font-family: 'Clarkson Script', cursive; 
}
.bg-blue {
    background-color: #40E0D0;
}
.blue-heading {
    color: #000000;
    text-align: center;
}
.card-footer {
    width: 100%;
    display: flex;
    align-items: center; /* Center vertically */
    font-family: 'Clarkson Script', cursive; 
}
.card-footer .btn {
    flex: 1; /* Expand button to fill available space */
    background-color: #40E0D0; /* Set button background color */
    border-color: #40E0D0; /* Set button border color */
    color: #FFFFFF; /* Set button text color */
}
.card-footer .btn:hover {
    background-color: #20B2AA; /* Set button background color on hover */
    border-color: #20B2AA; /* Set button border color on hover */
}
</style>