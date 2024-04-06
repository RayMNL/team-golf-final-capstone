<template>
    <div class="container">
        <h1>Recipes</h1>
        <input type="text" v-model="searchQuery" placeholder="Search recipes..." class="search-bar">
        <div class="recipe-container">
            <div v-if="recipesWithImages.length > 0" v-for="recipe in recipesWithImages" :key="recipe.id"
                class="recipe-card">
                <img v-if="recipe.image" :src="recipe.image" :alt="recipe.title" class="recipe-image">
                <div class="recipe-details">
                    <h2 class="recipe-title">{{ recipe.title }}</h2>
                    <div class="recipe-tags">
                        <span v-if="recipe.vegetarian" class="tag">Vegetarian</span>
                        <span v-if="recipe.vegan" class="tag">Vegan</span>
                        <span v-if="recipe.glutenFree" class="tag">Gluten Free</span>
                        <span v-if="recipe.dairyFree" class="tag">Dairy Free</span>
                    </div>
                </div>
            </div>
            <div v-else>
                <p>No recipes with images found.</p>
            </div>
        </div>
    </div>
</template>
<script>
import SpoonService from '@/services/SpoonService'
export default {
    data() {
        return {
            recipes: []
        }
    },
    computed: {
        recipesWithImages() {
            return this.recipes.filter(recipe => recipe.image);
        }
    },
    created() {
        this.getDataFromSpoon();
    },
    methods: {
        getDataFromSpoon() {
            SpoonService.getRandomRecipes().then(response => {
                this.recipes = response.data.recipes;
            }).catch(err => console.error(err));
        }
    }
}
</script>
<style>
.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

.recipe-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.recipe-card {
    width: calc(25% - 20px);
    margin-bottom: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
    overflow: hidden;
}

.recipe-image {
    width: 100%;
    height: 200px;
    object-fit: cover;
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
}

.last-row-card {
    width: 100%;

}
.search-bar {
  width: 98%;
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

</style>