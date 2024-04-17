<template>
    <footer class="footer">
      <div class="footer-links">
        <a href="#">About Us</a>
        <a href="#">Contact</a>
        <a href="#">Privacy Policy</a>
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
    justify-content: space-between;
    align-items: center;
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
  </style>