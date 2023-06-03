// 取得活動照片區塊
let show_photo = document.querySelector("div.show_photo");
// 取得上傳照片按鈕
let photo_btn = document.querySelector("#activity_photo");
// 取得form表單
let form = document.querySelector("form.activity_establish_form");
// 取得彈窗內的「送出」
let check_submit = document.querySelector("button.check_submit");

// 取得 餐廳名稱
function getResName() {
  let restaurant_list = document.querySelector("select#activity_restaruant");
  fetch("restaurantList")
    .then((res) => {
      return res.json();
    })
    .then((resList) => {
      // console.log(resList);
      for (let reser of resList) {
        // console.log(reser.restaurantId);
        restaurant_list.innerHTML += `
        <option value="${reser.restaurantId}">${reser.resName}</option>
      `;
      }
    });
}

// 顯示圖片功能
function showImg() {
  photo_btn.addEventListener("change", function () {
    show_photo.innerHTML = ""; // 清空
    let reader = new FileReader();
    reader.readAsDataURL(this.files[0]);

    reader.addEventListener("load", function () {
      let img_str = `
          <img src = "${this.result}" class="preview"  >
      `;

      show_photo.innerHTML = img_str;
    });
  });
}

// 表單相關預設設定
function formDefault() {
  // 判斷申請開始日期，若被點擊，參加截止日期就開放填寫
  if ($("input#regesteration_starting_time").val() == "") {
    $("input#regesteration_endinging_time").attr("disabled", "disabled");
  } else {
    $("input#regesteration_endinging_time").removeAttr("disabled");
  }
  // console.log($("input#regesteration_starting_time").val() == "");
}

// 點擊圖片換圖
function clickImg() {
  // let show_photo = document.querySelector("div.show_photo");
  show_photo.addEventListener("click", () => {
    // 裡面觸發 photo_btn 的點擊
    photo_btn.click();
  });
}

//將表單數據轉為JSON，並且提交給後端
function form2JSON() {
  check_submit.addEventListener("click", (e) => {
    // 阻止表單送出
    e.preventDefault();

    // 取得form裡面的數據
    let activityName = $("input#activity_name").val();
    let restaurantId = $("select#activity_restaruant").val();
    let activityDate = $("input#activity_date").val();
    // 轉換日期格式，使其符合後端資料
    let regesterationStartingTime = `${$("input#regesteration_starting_time")
      .val()
      .substring(0, 10)} ${$("input#regesteration_starting_time")
      .val()
      .substring(11)}:00`;
    let regesterationEndingTime = `${$("input#regesteration_ending_time")
      .val()
      .substring(0, 10)} ${$("input#regesteration_ending_time")
      .val()
      .substring(11)}:00`;
    let activityStartingTime = $("input#activity_starting_time").val() + ":00";
    let activityEndingTime = $("input#activity_ending_time").val() + ":00";
    let minNumber = $("input#min_member").val();
    let maxNumber = $("input#max_member").val();
    let activityText = $("textarea#activity_text").val();
    let activityStatus = 0; // 0：報名中 1：成團 2：流團，預設為0
    // let accId = 1; //先假定會員編號為1
    let accId = sessionStorage.getItem("accId");
    let activityNumber = 1; //預設會有1人
    let activityPhotoBase64;

    //取得上傳的圖片
    // type="file"的input標籤會回傳filesList物件
    let file = document.querySelector("input#activity_photo").files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);

    // 讀取好後，嘗試將資料送往後端
    reader.addEventListener("load", () => {
      // 這是一個Base64型態的物件，並將其他不相關的字串去除
      activityPhotoBase64 = reader.result.split(",")[1];
      // console.log(reader.result.split(",")[1]);

      // 準備好空物件，拿來裝form資料的key-value
      let jsonObject = {
        activityName: activityName,
        activityNumber: activityNumber,
        minNumber: minNumber,
        maxNumber: maxNumber,
        activityDate: activityDate,
        accId: accId,
        restaurantId: restaurantId,
        regesterationStartingTime: regesterationStartingTime,
        regesterationEndingTime: regesterationEndingTime,
        activityStartingTime: activityStartingTime,
        activityEndingTime: activityEndingTime,
        activityText: activityText,
        activityStatus: activityStatus,
        // activityPhoto: activityPhoto,
        activityPhotoBase64: activityPhotoBase64,
      };
      console.log(jsonObject);

      fetch("establish", {
        method: "POST",
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
        },
        body: JSON.stringify(jsonObject), // 欲傳送的資料
      })
        .then((res) => {
          // console.log(JSON.stringify(jsonObject));
          console.log(res);
          return res.json();
        })
        .then((json) => {
          console.log(json);
          // 建立成功後直接導向該活動詳細頁
          let activityId = json.activityId;
          console.log(activityId);
          sessionStorage.setItem("activityId", activityId);
          document.location.href = `activity_detail.html`;
          // console.log("成功發送給後端");
        });
    });
  });
}

// 搜尋
function search() {
  const search_btn = document.querySelector("button.submit");
  search_btn.addEventListener("click", (e) => {
    e.preventDefault();
    // 取得搜尋的值
    search_value = $("input.search_value").val().trim();
    // 將值存在session中，傳給下個頁面
    sessionStorage.setItem("search_value", search_value);
    //進行重導向
    document.location.href = "activity_search.html";
  });
}

$(function () {
  //form表單相關設定
  formDefault();

  search();

  // 顯示圖片功能
  showImg();

  //點擊圖片也可以換圖
  clickImg();

  // 取得 餐廳名稱
  getResName();

  // //將表單數據轉為JSON，並且提交給後端
  form2JSON();
});
