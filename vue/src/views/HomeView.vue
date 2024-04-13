<template>
  <div class="big-image">
    <div class="overlay">
      <h1 class="text-big">Kitchen Chronicles</h1>
      <p class="text-small">Your personalized recipe for success</p>
      <button type="button" class="btn btn-secondary btn-lg" @click="navigateToListRecipes">View Recipes</button>
      <div class="line"></div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    navigateToListRecipes() {
      this.$router.push({ name: 'spoon' });
    }
  }
};
</script>

<style>
.big-image {
    height: 100vh;
    width: 100vw;
    position: relative;
    background-size: cover;
    background-position: 50% 50%;
    background-image: url('https://images.pexels.com/photos/1640774/pexels-photo-1640774.jpeg');
}

.overlay {
    position: absolute;
    height: 100%;
    width: 100%;
    top: 0;
    left: 0;
    background: rgb(0,0,0,0);
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
}

.line {
    position: absolute;
    top: 50%;
    left: 50%;
    width: 1000px; 
    height: 300px; 
    background-color: #40E0D0; 
    transform: translate(-50%, -50%);
    z-index: 2; 
}

.text-big {
    position: relative;
    z-index: 3; 
    color: white; 
    font-family: 'Clarkson Script', cursive; 
    font-size: 64px; 
}

.text-small {
    position: relative;
    z-index: 3;
    color: white; 
    font-family: 'Clarkson Script', cursive; 
    font-size: 24px; 
}

.btn {
    position: relative;
    z-index: 3; 
    margin-top: 20px; 
    font-size: 24px; 
    padding: 15px 30px; 
    text-decoration: none; 
    color: white; 
    cursor: pointer; 
}
</style>