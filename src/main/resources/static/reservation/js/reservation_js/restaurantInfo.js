document.addEventListener("DOMContentLoaded", function() {
    fetch("../restaurantName") // 替换为后端的URL路径
      .then(response => response.text())
      .then(data => {
        const h1Element = document.querySelector("h1");
        h1Element.textContent = data;
      })
      .catch(error => {
        console.log("Error:", error);
      });
  });
  