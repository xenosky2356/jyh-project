package vo;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class SearchVO {
	private String StartDate;
	private String EndDate;
	private String SearchDate;
	private Date TypeDate;
	private String CustName;
	private int OrderTotal;
	private int Quantity;
	private String itemName;
	
	
	public SearchVO(Date typeDate, String custName, int orderTotal) {
		super();
		TypeDate = typeDate;
		CustName = custName;
		OrderTotal = orderTotal;
	}


	public String getStartDate() {
		return StartDate;
	}


	public void setStartDate(String startDate) {
		StartDate = startDate;
	}


	public String getEndDate() {
		return EndDate;
	}


	public void setEndDate(String endDate) {
		EndDate = endDate;
	}


	public String getSearchDate() {
		return SearchDate;
	}


	public void setSearchDate(String searchDate) {
		SearchDate = searchDate;
	}


	public Date getTypeDate() {
		return TypeDate;
	}


	public void setTypeDate(Date typeDate) {
		TypeDate = typeDate;
	}


	public String getCustName() {
		return CustName;
	}


	public void setCustName(String custName) {
		CustName = custName;
	}


	public int getOrderTotal() {
		return OrderTotal;
	}


	public void setOrderTotal(int orderTotal) {
		OrderTotal = orderTotal;
	}


	public int getQuantity() {
		return Quantity;
	}


	public void setQuantity(int quantity) {
		Quantity = quantity;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	
	
	
}
