<template>
  <nav class="navbar">
    <div class="navbar-left">
      <div class="logo">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkk7ktNLRvLeJW0kv-LaiR5EKd5POOjewEmQ&usqp=CAU" alt="Plate Logo" class="plate-logo">
        <span class="brand">Kitchen Chronicles</span>
      </div>
    </div>
    <div class="navbar-right">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <span class="separator" v-if="$store.state.token !== ''"></span>
      <router-link v-bind:to="{ name: 'spoon' }">Recipes</router-link>
      <span class="separator" v-if="$store.state.token !== ''"></span>
      <router-link v-bind:to="{ name: 'personal' }" v-if="$store.state.token !== ''">My Recipes</router-link>
      <span class="separator" v-if="$store.state.token !== ''"></span>
      <router-link v-bind:to="{ name: 'custom' }" v-if="$store.state.token !== ''">Custom Recipes</router-link>
      <span class="separator" v-if="$store.state.token !== ''"></span>
      <router-link v-bind:to="logoutOrLoginRoute" class="logout-link">{{ logoutOrLoginText }}</router-link>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'Header',
  computed: {
    logoutOrLoginRoute() {
      return this.$store.state.token !== '' ? { name: 'logout' } : { name: 'login' };
    },
    logoutOrLoginText() {
      return this.$store.state.token !== '' ? 'Logout' : 'Login';
    }
  }
}
</script>
  
  <style scoped>
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
  
  .logout-link {
    margin-left: auto;
  }
  </style>