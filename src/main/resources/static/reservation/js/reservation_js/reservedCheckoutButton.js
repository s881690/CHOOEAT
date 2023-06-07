// 取得按鈕元素
var button = document.querySelector('.pay_check_btn');

// 綁定點擊事件處理函式
button.addEventListener('click', function() {
  // 發送請求給後端
  fetch('/reservation')  // 將'/your-endpoint'替換為實際的後端端點
    .then(response => response.json())
    .then(data => {
      // 檢查回傳的物件中的status屬性
      if (data.status === 'success') {
        // 跳轉至下一個頁面
        window.location.href = "reservationSucess.html";  
        // 將'/next-page'替換為實際的下一個頁面URL
      } else {
        // 處理其他情況
        // ...
      }
    })
    .catch(error => {
      // 處理錯誤
      // ...
    });
});


