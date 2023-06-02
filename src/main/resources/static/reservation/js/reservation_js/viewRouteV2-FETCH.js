document.addEventListener("DOMContentLoaded", () => {
  const viewButton = document.getElementById("viewButton");

  viewButton.addEventListener("click", () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const userLocation = `${position.coords.latitude},${position.coords.longitude}`;
          fetch("../restaurantAddress")
            .then((response) => response.text())
            .then((address) => {
              const encodedStartAddress = encodeURIComponent(userLocation);
              const encodedEndAddress = encodeURIComponent(address);
              const mapUrl = `https://www.google.com/maps?saddr=${encodedStartAddress}&daddr=${encodedEndAddress}`;
              window.open(mapUrl, "_blank"); // 在新的選項卡或窗口中打開地圖
            })
            .catch((error) => {
              console.error("Failed to fetch restaurant address:", error);
              // 處理獲取餐廳地址失敗的情況
              // 或者在這裡添加其他錯誤處理邏輯
            });
        },
        (error) => {
          console.error("Failed to get user location:", error);
          // 處理獲取使用者位置失敗的情況
          // 或者在這裡添加其他錯誤處理邏輯
        }
      );
    } else {
      console.error("Geolocation is not supported by this browser.");
      // 處理瀏覽器不支援地理定位的情況
      // 或者在這裡添加其他錯誤處理邏輯
    }
  });
});
