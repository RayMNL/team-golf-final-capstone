<template>
  <div id="capstone-app">
    <Header />
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
}
</style>