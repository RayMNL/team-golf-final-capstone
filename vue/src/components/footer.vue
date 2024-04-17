<template>
  <footer class="footer">
    <div class="footer-left">
      <div class="footer-links">
        <a href="#">About Us</a>
        <a href="#">Contact</a>
        <a href="#">Privacy Policy</a>
      </div>
    </div>
    <div class="footer-social">
      <a href="https://www.facebook.com"><img src="../assets/facebookLogo.png" alt="Facebook"></a>
      <a href="https://twitter.com"><img src="../assets/twitterIcon.png" alt="Twitter"></a>
      <a href="https://www.instagram.com"><img src="../assets/instaLogo.png" alt="Instagram"></a>
    </div>
    <div class="footer-text">
      &copy; 2024 Kitchen Chronicles. All rights reserved.
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-color: #40E0D0;
  color: white;
  padding: 20px;
  display: flex;
  justify-content: space-between; /* Distribute items evenly along the main axis */
  align-items: center; /* Center align items vertically */
}

.footer-links {
  display: flex;
}

.footer-links a {
  color: white;
  text-decoration: none;
  margin-right: 20px;
}

.footer-links a:hover {
  text-decoration: underline;
}

.footer-social {
  display: flex;
  justify-content: center; /* Center align items horizontally */
}

.footer-social a {
  margin: 0 10px; /* Add some space around the icons */
}

.footer-social img {
  width: 30px; /* Adjust the width of the icons as needed */
  height: 30px; /* Adjust the height of the icons as needed */
}
</style>