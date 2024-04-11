<template>
  <div id="capstone-app">
    <nav class="navbar">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'spoon' }">Recipes</router-link>
      <router-link v-bind:to="{ name: 'personal' }" v-if="$store.state.token != ''">My Recipes</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="logout-link">Logout</router-link>
    </nav>
    <router-view />
  </div>
</template>

<style scoped>
#capstone-app {
  background-color: #f8f9fa;
  margin: 0 !important;
  overflow-x: hidden;
}

.navbar {
  background-color: #40E0D0; 
  padding: 10px 0; 
  display: flex; 
  justify-content: space-between; 
  width: 100%; 
}

.navbar a {
  color: #ffffff; 
  text-decoration: none; 
  padding: 10px 20px; 
  border-radius: 5px; 
}

.navbar a:hover {
  background-color: #495057; 
}

.logout-link {
  margin-left: auto;
}
</style>
