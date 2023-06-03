package chooeat.prod.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import chooeat.prod.model.vo.CartItem;
import redis.clients.jedis.Jedis;

@WebServlet("/prod/get-cart")
public class AddToCartContronller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Jedis jedis;

	@Override
	public void init() throws ServletException {
		jedis = new Jedis("localhost", 6379);
		jedis.select(1);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 設置接收格式
		req.setCharacterEncoding("utf-8");
		// 設置跨域
		res.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		res.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		res.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		res.setContentType("application/json; charset=utf-8");
		// 判斷請求中的操作類型
		String cartKey = "cart:user1"; // 先假設使用者ID為user1，實際要再多增加使用者這個參數
		String operation = req.getParameter("operation");
		System.out.println(operation);
		String productId = req.getParameter("productId");
		System.out.println(productId);
		String productName = req.getParameter("productName");
		System.out.println(productName);
		int price = Integer.parseInt(req.getParameter("price"));
		System.out.println(price);
		int qty = Integer.parseInt(req.getParameter("qty"));
		System.out.println(qty);

		switch (operation) {
		case "deleteSelected":
			// 刪除
			jedis.select(1);
			jedis.hdel(cartKey, "productId:"+productId);
			res.getWriter().println("{\"status\": \"success\"}");
			break;
		case "updateQuantity":
			// 更新數量
			String cartItemJson = "name:" + productName + ", price:" + price + ", qty:" + qty;
			System.out.println(cartItemJson);
			jedis.select(1);
			jedis.hset(cartKey, "productId:" + productId, cartItemJson);
			res.getWriter().println("{\"status\": \"success\"}");
			break;
		case "addcart":
			jedis.select(1);
			jedis.hset(cartKey, "productId:" + productId, "name:" + productName + ", price:" + price + ", qty:" + qty);
			res.getWriter().println("{\"status\": \"success\"}");
			break;
		default:
			System.out.println("又壞了哀");
			break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 設置接收格式
		req.setCharacterEncoding("utf-8");
		// 設置跨域
		res.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
		res.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
		res.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
		// 設置返回格式
		res.setContentType("application/json; charset=utf-8");

		String userId = "user1";
		String cartKey = "cart:" + userId;

		jedis.select(1);
		Map<String, String> cartData = jedis.hgetAll(cartKey);

		Map<String, CartItem> cartItems = new HashMap<>();

		for (Map.Entry<String, String> entry : cartData.entrySet()) {
			String productId = entry.getKey();
			String cartItemJson = entry.getValue();
			String redisValue = productId + ", " + cartItemJson;
//			System.out.println(redisValue);
			CartItem cartItem = convertToCartItem(redisValue);
			cartItems.put(extractProductId(productId), cartItem);
			String json = new Gson().toJson(cartItem);
		}

		String json = new Gson().toJson(cartItems);
		res.getWriter().write(json);
//		System.out.println(json);
	}

	public String convertToJsonString(CartItem cartItem) {
		Gson gson = new Gson();
		return gson.toJson(cartItem);
	}

	private static String extractProductId(String productId) {
		Pattern pattern = Pattern.compile("productId:(\\d+)");
		Matcher matcher = pattern.matcher(productId);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return null; // 無法找到productId，返回null或其他適當的預設值
		}
	}

	public static CartItem convertToCartItem(String keyValueString) {
		CartItem cartItem = new CartItem();
		String[] keyValuePairs = keyValueString.split(", ");
		for (String keyValuePair : keyValuePairs) {
			String[] keyValue = keyValuePair.split(":");
			String key = keyValue[0].trim();
			String value = keyValue[1].trim();
			if (key.equals("productId")) {
				cartItem.setProductId(value);
			} else if (key.equals("name")) {
				cartItem.setProductName(value);
			} else if (key.equals("price")) {
				cartItem.setPrice(Double.parseDouble(value));
			} else if (key.equals("qty")) {
				cartItem.setQty(Integer.parseInt(value));
			}
		}
		return cartItem;
	}


	public void destroy() {
		jedis.close();
	}
}
