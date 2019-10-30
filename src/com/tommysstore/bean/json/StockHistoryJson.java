package com.tommysstore.bean.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tommysstore.domain.StockHistory;
import com.tommysstore.domain.User;

public class StockHistoryJson {
	
	private String date;
	private String time;
	private String addedBy;
	private int stockAdded;
	
	public StockHistoryJson() {
		
	}
	
	public StockHistoryJson(StockHistory stockHistory) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy - E");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
		Date dateAdded = stockHistory.getDateAdded();
		User user = stockHistory.getUser();
		
		this.date = dateFormat.format(dateAdded);
		this.time = timeFormat.format(dateAdded);
		this.addedBy = user.getFirstname() + " " + user.getLastname();
		this.stockAdded = stockHistory.getStockAdded();
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

	public String getAddedBy() {
		
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		
		this.addedBy = addedBy;
	}

	public int getStockAdded() {
		
		return stockAdded;
	}

	public void setStockAdded(int stockAdded) {
		
		this.stockAdded = stockAdded;
	}
}
