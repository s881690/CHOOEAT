// 檢查頁面載入時的sessionStorage
window.addEventListener('DOMContentLoaded', function() {
  // 檢查sessionStorage中是否存在loginReq鍵
  if (!sessionStorage.getItem('loginReq')) {
    alert("請登入會員");
    window.location.href = "http://localhost:8080/account/login.html";
  }
});
