<template>
  <nav class="navbar">
    <div class="navbar-left">
      <div class="logo">
        <img src="../assets/logo.png"
          alt="Plate Logo" class="plate-logo">
        <router-link class="brand" v-bind:to="{ name: 'home' }">Kitchen Chronicles</router-link>
        <span class="user" v-if="$store.state.token !== ''">Welcome, {{ currentUser }}!</span>
      </div>
    </div>
    <div class="navbar-right">
      <router-link v-bind:to="{ name: 'spoon' }" class="nav-link">Recipes</router-link>
      <span class="separator">|</span>
      <router-link v-bind:to="{ name: 'personal' }" v-if="$store.state.token !== ''" class="nav-link">My Recipes</router-link>
      <span class="separator" v-if="$store.state.token !== ''">|</span>
      <router-link v-bind:to="{ name: 'custom' }" v-if="$store.state.token !== ''" class="nav-link">Add Recipes</router-link>
      <span class="separator" v-if="$store.state.token !== ''">|</span>
      <router-link v-bind:to="logoutOrLoginRoute" class="logout-link navbar-link">{{ logoutOrLoginText }}</router-link>
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
    },
    currentUser() {
      return this.$store.state.user.username;
    }
  },
  mounted() {
    // Add event listener to the dropdown toggle button
    const dropdownToggle = document.querySelector('.dropdown-toggle');
    dropdownToggle.addEventListener('click', function() {
      const dropdownMenu = this.nextElementSibling;
      dropdownMenu.classList.toggle('show');
    });
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
  width: 50px;
  margin-right: 10px;
  border: none;
  padding: 0;
}

.brand {
  color: white;
  font-weight: bold;
  font-size: 18px;
}
.user {
  color: white;
  font-weight: bold;
  font-size: 18px;
  margin-left: 75px;
}

.navbar a {
  color: #ffffff;
  text-decoration: none;
  padding: 10px 20px;
  border-radius: 5px;
}

.navbar .navbar-right .nav-link:hover {
  background-color: #4682B4;
}

.separator {
  color: #ffffff;
  margin: 0 10px;
}

.logout-link {
  margin-left: auto;
}

.navbar-link:hover {
  background-color: #4682B4;
}
</style>