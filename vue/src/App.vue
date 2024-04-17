<template>
  <div id="capstone-app">
<<<<<<< HEAD
    <nav class="navbar">
      <div class="navbar-left">
        <div class="logo">
          <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkk7ktNLRvLeJW0kv-LaiR5EKd5POOjewEmQ&usqp=CAU" alt="Plate Logo" class="plate-logo">
          <span class="brand">Kitchen Chronicles</span>
        </div>
      </div>
      <div class="navbar-right">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <span class="separator">|</span>
        <router-link v-bind:to="{ name: 'spoon' }">Recipes</router-link>
        <span class="separator">|</span>
        <router-link v-bind:to="{ name: 'personal' }" v-if="$store.state.token != ''">My Recipes</router-link>
        <span class="separator">|</span>
        <router-link v-bind:to="{ name: 'custom' }">Custom Recipes</router-link>
        <span class="separator">|</span> 
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" class="logout-link">Logout</router-link>
      </div>
    </nav>
=======
    <Header />
>>>>>>> dd7500772eac4f5f29c0864e52a8e99f7fe63650
    <router-view />
    <Footer />
  </div>
</template>

<script>
import Header from './components/header.vue';
import Footer from './components/footer.vue';

export default {
  components: {
    Header,
    Footer
  }
}
</script>

<style>
body {
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  min-height: 100vh; 
}

#capstone-app {
  flex: 1; 
  background-color: #f8f9fa;
<<<<<<< HEAD
  margin: 0 !important;
  overflow-x: hidden;
}

.navbar {
  background-color: #40E0D0;
  padding: 10px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  font-family: 'Clarkson Script', cursive;
}

.navbar-left {
  display: flex;
  align-items: center;
}

.navbar-right {
  display: flex;
  align-items: center;
}

.logo {
  display: flex;
  align-items: center;
}

.plate-logo {
  width: 30px; 
  margin-right: 10px;
  border: none; 
  padding: 0; 
}

.brand {
  color: white;
  font-weight: bold;
  font-size: 18px;
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

.separator {
  color: #ffffff;
  margin: 0 10px;
}

.separator-line {
  border-left: 1px solid #ffffff;
  height: 20px;
  margin: 0 10px;
}

.logout-link {
  margin-left: auto;
=======
>>>>>>> dd7500772eac4f5f29c0864e52a8e99f7fe63650
}
</style>