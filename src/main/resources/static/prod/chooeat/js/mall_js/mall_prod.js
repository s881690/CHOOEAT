
//===============================================================================
var productName;
var price;
var resName;
var btn_add_cart_el;
var productId;
var prodPic;
let account;
const overlay = document.querySelector(".confirmation-overlay");
const confirmationBox = document.querySelector(".confirmation-box");
//const confirmBtn = document.querySelector(".confirm-btn");
const cancelBtn = document.querySelector(".cancel-btn");
var star;
//===========
account = JSON.parse(sessionStorage.getItem("loginReq"));
if (sessionStorage.getItem("loginReq") != null) {
	document.getElementById("sname").innerHTML = account.acc_name;

}
//=================================================================
// 拿到會員icon
let accountIcon = $("a.accountIcon");
// console.log(accountIcon);
// 會員中心的判斷
if (account != null) {
	accountIcon.attr("href", "../account/usercenter.html");
} else {
	accountIcon.attr("href", "../account/login.html");
}
// ========================從URL獲取商品ID並獲取詳細資料====================================
document.addEventListener("DOMContentLoaded", function() {
	productId = getProductIdFromURL();
	getProductDetails(productId);
});
//======================================== 時間轉換 ========================================
function formatTimestamp(timestampString) {
	var timestamp = new Date(timestampString);
	var year = timestamp.getFullYear();
	var month = timestamp.getMonth() + 1;
	var day = timestamp.getDate();
	var hours = timestamp.getHours();
	var minutes = timestamp.getMinutes();
	if (minutes < 10) {
		minutes = "0" + minutes;
	}

	var formattedTimestamp = year + "年" + month + "月" + day + "日 " + hours + ":" + minutes;

	return formattedTimestamp;
}
//===================================送到餐券詳細頁面=================================
function initMap() {
	const uluru = { lat: 25.105, lng: 121.597 };
	const map = new google.maps.Map(document.getElementById("map"), {
		zoom: 10,
		center: uluru,
	});
	const marker = new google.maps.Marker({
		position: uluru,
		map: map,
	});
}
//============使用fetch函數從後端獲取商品詳細資料並動態生成商品詳細頁面=============================
function getProductDetails(productId) {
	fetchController = new AbortController(); // 創建請求的控制器
	var fetchSignal = fetchController.signal;
	const url = "prod/details?id=" + productId;
	fetch(url, { signal: fetchSignal })
		.then(response => response.json())
		.then(data => {
			//			console.log(data);
			const prod = data.prod;
			const orderDetails = data.orderDetails;
			const address = prod.resAdd;
			resName = prod.resName;
			productName = prod.resName + "｜" + prod.prodName;
			price = prod.prodPrice.toLocaleString();
			star = Math.floor(prod.prodCommentScore);
			prodPic = prod.prodPic;
			const uint8Array = new Uint8Array(prod.prodPic);
			let blob = new Blob([uint8Array], { type: "image/*" });
			let imageUrl = URL.createObjectURL(blob);

			document.getElementById("prodpic").src = `${imageUrl}`;

			document.getElementById("breadcrumb-page").innerHTML = `
			${prod.resName} | ${prod.prodName}
			`;
			document.getElementById("prodInfor").innerHTML = `
			<span id="productName" class="prodTitle">${prod.resName} | ${prod.prodName}</span>
                <br />
              <div class="type">${prod.resType}</div>
              <div class="add">${prod.resAdd}</div>`;
			document.getElementById("prodText").innerHTML = `<span class="prodTitle">餐券詳情</span>
                <br />
                <ul>
                  <li>${prod.prodText}</li>
                </ul>`;
			document.getElementById("prodUserguide").innerHTML = `
			<span class="prodTitle">如何使用</span>
			                <br />
			                <ul>
			                 <li>${prod.prodUserguide}</li>
			                </ul>`;
			document.getElementById("star_score").innerHTML = `
			${prod.prodCommentScore} <span style="font-size: 18px;">/ 5</span>
			`;

			if (orderDetails.length === 0) {
				document.getElementById("comment").innerHTML = `<h2 style="margin-top:30px; text-align: center;">尚未有任何評論。</h2>`;
			} else {
				for (let orderDetail of orderDetails) {
					console.log(orderDetail);
					const star = Math.floor(orderDetail.prodCommentScore);
					let starHtml = '';
					for (let i = 1; i <= 5; i++) {
						if (i <= star) {
							starHtml += `<span class="star -on" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`;
						} else {
							starHtml += `<span class="star" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`;
						}
					}
					if (orderDetail.prodCommentScore !== 0) {
						document.getElementById("comment").innerHTML += `
        <div class="acc_profiles">
          <div class="acc_photo" style="background-image: url('./chooeat/images/header/logo2.png');"></div>
          <div class="nameandStar">
            <div class="acc_name">${orderDetail.accName}</div>
            <div class="dot3">
              <input type="submit" id="more_button" class="more" value="" data-order-detail-id="${orderDetail.orderDetailId}"
                style="background-image: url(./chooeat/images/mall_image/more.png);">
            </div>
            <br />
            <div class="commemt_star_block">
              ${starHtml}
            </div>
          </div>
        </div>
        <div class="comment_area">
          <div>${formatTimestamp(orderDetail.prodCommentTimestamp)} 單人｜平日晚餐</div>
          <div class="comment_text">
            ${orderDetail.prodCommentText}
          </div>
        </div>
        <hr class="comment_hr" style="border: 1.3px solid; margin-bottom:15px;" />
      `;
					}
				}
			}


			document.getElementById("price").innerHTML = ` NT $${price}`;
			//              <div class="prod">
			//                <span class="prodTitle">餐券詳情</span>
			//                <br />
			//                <ul>
			//                  <li>法式手工麵包佐松露奶油</li>
			//                  <li>前菜或沙拉（任選一）：香煎北海道干貝佐松露高湯／焗烤青醬田螺盅／酥炸蟹肉餅／香煎厚片煙燻鮭魚／義式生牛肉冷盤／煙燻鮭魚蘿蔓凱薩沙拉</li>
			//                  <li>湯品（任選一）：松露蘑菇濃湯／義式蕃茄蔬菜牛肉湯／酥皮燻鮭魚南瓜湯</li>
			//                  <li>西班牙手工雪酪Sorbet</li>
			//                  <li>主菜（任選一）：PS紐西蘭一級菲力牛排(7oz)／香煎舒肥宜蘭櫻桃鴨胸／乳酪蘑菇釀雞胸／爐烤戰斧豬排／碳烤國王鮭魚配根島蝦</li>
			//                  <li>甜點（任選一）：提拉米蘇千層蛋糕／草莓乳酪蛋糕／法式焦糖布蕾／主廚特製甜點</li>
			//                  <li>飲品（任選一）：美式咖啡／拿鐵／紅茶／奶茶／季節果汁</li>
			//                  <li>服務費</li>
			//                </ul>
			//              </div>
			//               <div class="prod">
			//                <span class="prodTitle">如何使用</span>
			//                <br />
			//                <ul>
			//                  <li>開放時間內皆可兌換 限內用 每筆交易中無使用此優惠券的數量限制</li>
			//                  <li>本券平假日午餐時段11:30至15:00（最後點餐及收客時間為14:00）適用、晚餐時段17:30至21:30（最後點餐及收客時間為21:00）適用</li>
			//                  <li>國定假日及特殊節日（除夕、初一、初二、西洋情人節、母親節、父親節、七夕情人節、聖誕夜、聖誕節、跨年夜）不適用，不適用之日期以店內現場公告為準</li>
			//                  <li>未持券者每人最低消費一個套餐，6歲以下兒童免低消</li>
			//                  <li>本券使用請提前1天前完成預約。訂位時請告知使用本餐券及內容，以利現場服務人員訂位點餐，用餐前請先出示餐券。本券銷售時已開立統一發票，使用時不再重複開立。宵夜時段21:30至04:00恕不適用本餐券</li>
			//                  <li>若您對某些食物過敏，請務必告知服務人員</li>
			//                  <li>每人最低消300元，六歲以上兒童或大人均需點餐，六歲以下兒童免低消</li>
			//                  <li>以上餐點內容因季節食材不同而做調整，依現場供應為主</li>
			//                  <li>本券恕不與其他任何優惠及折扣合併使用，例：龍蝦優惠活動</li>
			//                </ul>
			//              </div>
			googleMap(address);
			bindEventsToElements();
			prodStar();
		})
		.catch(error => {
			console.error("無法獲取商品詳細資料:", error);
		});
}
//onclick="addToCart()"
//                <li>雙人｜平日晚餐｜ NT $2,280</li>
//                <li>單人｜假日晚餐｜ NT $1,580</li>
//                <li>雙人｜假日晚餐｜ NT $2,980</li>

// =====================從URL中獲取商品ID的函數=====================
function getProductIdFromURL() {
	var urlParams = new URLSearchParams(window.location.search);
	return urlParams.get("id");
}
//=========================================================
function googleMap(address) {
	const geocoder = new google.maps.Geocoder();
	geocoder.geocode({ address: address }, (results, status) => {
		if (status === 'OK' && results[0]) {
			const map = new google.maps.Map(document.getElementById('map'), {
				center: results[0].geometry.location,
				zoom: 14
			});

			new google.maps.Marker({
				map: map,
				position: results[0].geometry.location
			});
		} else {
			console.error('無法解析地址:', status);
		}
	});
}
// ================================================================
function bindEventsToElements() {
	// === shopping cart ===
	btn_add_cart_el = document.getElementById("add_cart");
	if (btn_add_cart_el) {
		btn_add_cart_el.addEventListener("click", function() {
			var productId = new URLSearchParams(window.location.search).get("id");
			var cart_data = {};

			if (sessionStorage.getItem("loginReq")) {
				var account = JSON.parse(sessionStorage.getItem("loginReq"));
				cart_data.accId = account.acc_id;
			} else {
				cart_data.accId = "";
			}

			cart_data.productId = productId;
			cart_data.resName = resName;
			cart_data.accId = account.acc_id;
			cart_data.productName = productName;
			cart_data.price = price;
			cart_data.qty = 1;
			cart_data.prodPic = prodPic;
			console.log(cart_data);

			sessionStorage.setItem("form_data", JSON.stringify(cart_data));
			fetchController.abort();
			location.href = "mall_add_cart.html";
		});
	}
	// ==========
	const isDirectBuy = true;
	var selectedProducts = new Array();
	selectedProducts.push(productId);
	var btn_pay_el = document.getElementById("pay_immediately");
	btn_pay_el.addEventListener("click", function() {


		account = JSON.parse(sessionStorage.getItem("loginReq"));
		//		console.log(account);
		//		console.log(account.acc_id);
		if (sessionStorage.getItem("loginReq") == null) {
			alert("請先進行登入");
			return;
		} else {

			console.log("T");
			console.log(selectedProducts);
			fetch('checkout', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ selectedProducts: selectedProducts, isDirectBuy: isDirectBuy })
			})
				.then(data => {
					console.log("d");
					window.location.href = 'mall_checkout.html';
				})
				.catch(error => {
					console.error('Error:', error);
				});
		}
	});
	// === more_btn ===
	const confirmationBox = $(".confirmation-box");
	const overlay = $(".confirmation-overlay");
	$(".more").on("click", function() {
		const moreBtn = $(this);
		const orderDetailId = moreBtn.attr("data-order-detail-id");
		console.log(orderDetailId);
		if (moreBtn.hasClass("reported")) {
			overlay.css("display", "flex");
			confirmationBox.css({
				width: "300px",
				height: "180px"
			});
			confirmationBox.html("<h4 class='centered-text'>您已檢舉過這則評論。</h4>");
			return;
		}
		console.log("檢舉按鈕");
		confirmationBox.css({
			height: "350px",
			width: "350px"
		});

		confirmationBox.html(
			`<h5 style="font-weight: bold;">檢舉評論</h5>
				<div style="margin:5px;">檢舉這則評論的原因？</div>
				<textarea placeholder="請輸入檢舉原因..."></textarea>
				<div>
					<button class="confirm-btn">完成</button>
					<button class="cancel-btn">取消</button>
				</div>
				`);
		overlay.css("display", "flex");

		const confirmBtn = confirmationBox.find(".confirm-btn");
		confirmBtn.on("click", function() {
			const reason = confirmationBox.find("textarea").val();
			if (reason.trim() === "") {
				alert("請輸入檢舉原因");
				return;
			}
			console.log(reason);
			confirmationBox.css({
				width: "300px",
				height: "180px"
			});
			confirmationBox.html("<h4 class='centered-text'>檢舉成功！</h4>");
			moreBtn.addClass("reported");
			const url = "prod/details";
			fetch(url, {
				method: "POST",
				body: JSON.stringify({ reason: reason, orderDetailId: orderDetailId })
			})
				.then(function(response) { return response.json(); })
				.then((data) => {
					console.log(data);
				})
				.catch((error) => {
					console.log("哀哀哀：" + error);
				});

		})
		const cancelBtn = confirmationBox.find(".cancel-btn");
		cancelBtn.on("click", function() {
			overlay.css("display", "none");
		});
	});

	$(".confirmation-overlay").on("click", function(event) {
		if ($(event.target).is(".confirmation-overlay")) {
			$(this).css("display", "none");
		}
	});

	$(".confirmationBox").on("click", function(event) {
		event.stopPropagation();
	});
	//=====================搜尋欄位可以按enter=========================
	$("#search_text").on("keyup", function(e) {
		if (e.which == 13) {
			$("#search_button").click();
		}
	});
	//=====================搜尋按鈕點擊事件============================
	document.getElementById('search_button').addEventListener('click', function(event) {
		const url = "Prod/getall?search=" + search_text + "&action=getByCompositeQuery";
		fetch(url)
			.then(function(response) {
				return response.text();
			})
			.then(function(data) {
				window.location.href = "mall.html";
			})
			.catch(function(error) {
			});
	})
};
// ==========================================
function prodStar() {
	$("span.star_block").children("span.star").each(function(index, item) {
		if (parseInt($(item).attr("data-star")) <= star) {
			$(this).addClass("-on");
		} else {
			$(this).removeClass("-on");
		}
	});
}
// ==========================================
// 獲取所有按鈕元素
var buttons = document.getElementsByClassName("star_filter_all");
// 綁定按鈕點擊事件
for (var i = 0; i < buttons.length; i++) {
	buttons[i].addEventListener("click", filterComments(rating));
}
// 評論篩選函式
function filterComments(rating) {
	var starRating = rating; // 獲取按鈕的值
	console.log(starRating);
	const url = "prod/commentSort?sort=" + starRating + "&id=" + productId;
	const requestOptions = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({})
	};
	fetch(url, requestOptions)
		.then(function(response) {
			return response.json();
		})
		.then(orderDetails => {
			if (orderDetails.length === 0) {
				document.getElementById("comment").innerHTML = `<h2 style="margin-top:30px; text-align: center;">尚未有任何評論。</h2>`;
			} else {
				document.getElementById("comment").innerHTML = "";
				for (let orderDetail of orderDetails) {
					console.log(orderDetail);

					const star = Math.floor(orderDetail.prodCommentScore);
					let starHtml = '';
					for (let i = 1; i <= 5; i++) {
						if (i <= star) {
							starHtml += `<span class="star -on" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`;
						} else {
							starHtml += `<span class="star" data-star="${i}" style="padding-right:3px;"><i class="fas fa-star"></i></span>`;
						}
					}
					if (orderDetail.prodCommentScore !== 0) {
						document.getElementById("comment").innerHTML += `
        <div class="acc_profiles">
          <div class="acc_photo" style="background-image: url('./chooeat/images/header/logo2.png');"></div>
          <div class="nameandStar">
            <div class="acc_name">${orderDetail.accName}</div>
            <div class="dot3">
              <input type="submit" id="more_button" class="more" value="" data-order-detail-id="${orderDetail.orderDetailId}"
                style="background-image: url(./chooeat/images/mall_image/more.png);">
            </div>
            <br />
            <div class="commemt_star_block">
              ${starHtml}
            </div>
          </div>
        </div>
        <div class="comment_area">
          <div>${formatTimestamp(orderDetail.prodCommentTimestamp)} 單人｜平日晚餐</div>
          <div class="comment_text">
            ${orderDetail.prodCommentText}
          </div>
        </div>
        <hr class="comment_hr" style="border: 1.3px solid; margin-bottom:15px;" />
      `;
					}
				}
			}
		})
		.catch(error => {
			console.error('發生錯誤:', error);
		});

	console.log("篩選評論：" + starRating);
}