const cartData = [];
// ================================== 後端 ===================================
// 獲取購物車內容並顯示在畫面上
function displayCart() {
	var tbodyContainer = document.getElementById("tbody");
	var tfootContainer = document.getElementById("tfoot");
	tbodyContainer.innerHTML = "";
	tfootContainer.innerHTML = "";
	const url = "checkout";
	fetch(url)
		.then(function(response) { return response.json(); })
		.then(data => {
			//			console.log(data);
			let total = 0;
			for (const key in data) {
				if (data.hasOwnProperty(key)) {
					total += data[key].price * data[key].qty;
				}
			}
			const totalPrice = total.toLocaleString();

			const productsMap = data;
			for (let [productId, value] of Object.entries(productsMap)) {
				const key = productId;
				const productName = value.productName;
				const productPrice = value.price.toLocaleString();
				const productqty = value.qty;
				const producttotalPrice = (value.price * productqty).toLocaleString();
				tbodyContainer.innerHTML += `
									<tr data-product-id="${key}" class="key">
										<td style="width: 120px;"><img src="./chooeat/images/mall_image/mall3.jpg"
											class="pord_img" /></td>
										<td><h5>${productName}</h5>
											<span style="color: gray;">單人｜平日晚餐</span></td>
										<td class="price">NT $ ${productPrice}</td>
										<td class="qty">${productqty}</td>
										<td>NT ${producttotalPrice}</td>
									</tr>
									<tr>
										<td colspan="5" style="padding: 0;"><hr></td>
									</tr>
						`;
				tfootContainer.innerHTML = `
						<tfoot>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td id="totalPrice">NT $${totalPrice}</td>
							</tr>
						</tfoot>
								`;

			};
		})
		.catch(function(error) {
			console.log(error);
		});
}

document.addEventListener("DOMContentLoaded", function() {
	displayCart();
});

function sendCartDataToBackend() {
	const url = "order";

	var cartData = {};
	//	var memberId = document.getElementById("memberId").innerText;
	var originalAmount = document.getElementById("totalPrice").innerText;
	var checkoutAmount = document.getElementById("totalPrice").innerText;

	var rows = document.querySelectorAll("#tbody tr");
	console.log(rows);
	for (var i = 0; i < rows.length; i += 2) {
		var row = rows[i];
		var productId = row.getAttribute("data-product-id");
		var qty = row.querySelector(".qty").innerText;
		var uniprice = row.querySelector(".price").innerText;
		cartData[productId] = {
			qty: qty,
			uniprice: uniprice
		};
		console.log(cartData);
	}

	var requestData = {
		cartData: cartData,
		memberId: 10,
		originalAmount: originalAmount,
		checkoutAmount: checkoutAmount
	};


	fetch(url, {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(requestData)
	})
		.then(response => {
			if (response.ok) {
				console.log("d");
				window.location.href = 'mall_pay_successfully.html';
			} else {

				console.log("哀");
			}
		})
		.catch(error => {
			console.log("哀哀哀：" + error);
		});
}

document.getElementById("pay_check_btn").addEventListener("click", function() {
	sendCartDataToBackend();
});