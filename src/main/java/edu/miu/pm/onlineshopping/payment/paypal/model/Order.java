package edu.miu.pm.onlineshopping.payment.paypal.model;

public class Order {
	private String productName;
	private double subtotal;
	private double shipping;
	private double tax;
	private double total;
	public Order(String productName, double subtotal, double shipping, double tax, double total) {
		this.productName = productName;
		this.subtotal = subtotal;
		this.shipping = shipping;
		this.tax = tax;
		this.total = total;
	}
	public Order() {

	}
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public double getShipping() {
		return shipping;
	}

	public void setShipping(float shipping) {
		this.shipping = shipping;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}