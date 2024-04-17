<template>
  <div class="login-container">
    <div class="blue-box">
      <form v-on:submit.prevent="login" class="login-form">
        <h1>Please Sign In</h1>
        <div role="alert" v-if="invalidCredentials" class="alert">
          Invalid username and password!
        </div>
        <div role="alert" v-if="this.$route.query.registration" class="alert">
          Thank you for registering, please sign in.
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <button type="submit" class="btn btn-secondary btn-lg">Sign in</button>
        <p>
          <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/personal");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: white; 
}

.blue-box {
  background-color: #40E0D0; 
  padding: 20px;
  border-radius: 10px; 
}

.login-form {
  width: 500x; 
}

.form-input-group {
  margin-bottom: 1rem;
}

label {
  margin-right: 0.5rem;
}

button {
  margin-top: 20px;
  font-size: 24px;
  padding: 15px 30px;
  text-decoration: none;
  color: white;
  cursor: pointer;
  font-family: 'Clarkson Script', cursive;
  background-color: #6c757d; 
  border: none;
  
}

button:hover {
  background-color: #808080; 
}

.alert {
  color: white;
  background-color: red;
  padding: 10px;
  border-radius: 5px;
}
</style>