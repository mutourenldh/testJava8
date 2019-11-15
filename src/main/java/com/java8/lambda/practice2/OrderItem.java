package com.java8.lambda.practice2;

public class OrderItem {
	
	private Integer id;
	private String priceUnit;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", priceUnit=" + priceUnit + "]";
	}
	public OrderItem(Integer id, String priceUnit) {
		super();
		this.id = id;
		this.priceUnit = priceUnit;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
