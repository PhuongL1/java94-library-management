package vn.devpro.java94.model;

public class StatisticItem {
	private String name;
	private int quantity;

	public StatisticItem() {
	}

	public StatisticItem(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
}