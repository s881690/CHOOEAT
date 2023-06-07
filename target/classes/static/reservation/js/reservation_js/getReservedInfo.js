window.addEventListener('DOMContentLoaded', function() {
  // 發送 GET 請求
  var xhr = new XMLHttpRequest();
  xhr.open('GET', 'http://localhost:8080/getReservationInfo', true);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      // 解析回傳的 JSON 字串為物件
      var result = JSON.parse(xhr.responseText);

      // 從後端回傳的 result 物件中取得相應的值
      var resStartTime = result.resStartTime;
      var reservedPeople = result.reservedPeople;
      var member = result.member;

      // 更新 HTML 元素的內容
      document.getElementById('reservedTime').textContent = resStartTime;
      document.getElementById('memberName').textContent = member;
      document.getElementById('reservedPeoeple').textContent = reservedPeople + '位';
    }
  };
  xhr.send();
});

 