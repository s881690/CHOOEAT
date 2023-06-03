package chooeat.prod.model.vo;

import java.io.Serializable;

import org.springframework.stereotype.Service;

public class CartItem implements Serializable {

	private String productId;
	private String productName;
	private double price;
	private Integer qty;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", productName=" + productName + ", price=" + price + ", qty=" + qty
				+ "]";
	}
	public CartItem(String productId, String productName, double price, Integer qty) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.qty = qty;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}


}
