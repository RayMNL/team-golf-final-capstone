<template>
  <div class="big-image">
    <div class=" overlay">
      <h1>Your personalized recipe for success</h1>
    </div>
  </div>
</template>

<script>
export default {
};
</script>

<style>

.big-image {
    height: 100vh;
    width: 100vw;
    position: relative;
    background-size: cover;
    background-position: 50% 50%;
    background-image:
    url('https://images.pexels.com/photos/1640774/pexels-photo-1640774.jpeg');
}

.overlay {
    position: absolute;
    height: 100%;
    width: 100%;
    top: 0;
    left: 0;
    background: rgb(0,0,0,0);
    color:rgb(0, 0, 0);
    display: flex;
    align-items:center;
    justify-content: center;
    flex-direction: column;
}

</style>
