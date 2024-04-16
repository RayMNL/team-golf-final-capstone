<template>
  <div class="login-container">
    <div class="blue-box">
      <form v-on:submit.prevent="register" class="login-form">
        <h1>Create Account</h1>
        <div role="alert" v-if="registrationErrors" class="alert">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password</label>
          <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
        </div>
        <button type="submit" class="btn btn-secondary btn-lg">Create Account</button>
        <p>
          <router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
  },
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
  width: 500px;
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