<template>
  <div id="capstone-app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'spoon'}">Recipes </router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>
a:-webkit-any-link {

  background-color: lightgreen;
  color: #112A46;
  

}

#capstone-app {
  background-color: lightsalmon;
  
}

</style>
