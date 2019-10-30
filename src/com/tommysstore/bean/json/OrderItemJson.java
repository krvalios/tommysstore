package com.tommysstore.bean.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tommysstore.domain.OrderItem;

public class OrderItemJson {
	
	private Integer id;
	private String date;
	private String time;
	private int quantity;

	public OrderItemJson() {
		
	}
	
	public OrderItemJson(OrderItem orderItem) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy - E");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		Date dateOrdered = orderItem.getOrder().getDateOrdered();
		
		this.id = orderItem.getId();
		this.date = dateFormat.format(dateOrdered);
		this.time = timeFormat.format(dateOrdered);
		this.quantity = orderItem.getQuantity();
	}

	public Integer getId() {
		
		return id;
	}

	public void setId(Integer id) {
		
		this.id = id;
	}

	public String getDate() {
		
		return date;
	}

	public void setDate(String date) {
		
		this.date = date;
	}

	public String getTime() {
		
		return time;
	}

	public void setTime(String time) {
		
		this.time = time;
	}

	public int getQuantity() {
		
		return quantity;
	}

	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
	}
}
