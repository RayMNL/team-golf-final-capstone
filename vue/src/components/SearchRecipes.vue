<template>
    <div>
        <h1>Recipes</h1>
        <label for="Search">Search</label>
        <input type="text" id="search" v-model.trim="ingredient">
    </div>
</template>

<script>

export default {
    data() {
        return {
            Search: {},   
            
        }
    }
}

</script>


<style scoped>


</style>