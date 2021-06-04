package vo;

import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class OrderVO {
	private int customerId;
	private int total;
	private String saleDate;
	private int userId;
	private DefaultTableModel model;
	
	public OrderVO(int customerId, int total, String saleDate, int userId, DefaultTableModel model) {
		this.customerId = customerId;
		this.total = total;
		this.saleDate = saleDate;
		this.userId = userId;
		this.model = model;
	}

	public int getCustomerId() {return customerId;}
	public void setCustomerId(int customerId) {	this.customerId = customerId;}

	public int getTotal() {return total;}
	public void setTotal(int total) {this.total = total;}

	public String getSaleDate() {return saleDate;}
	public void setSaleDate(String saleDate) {this.saleDate = saleDate;}

	public int getUserId() {return userId;}
	public void setUserId(int userId) {this.userId = userId;}

	public DefaultTableModel getModel() {return model;}
	public void setModel(DefaultTableModel model) {this.model = model;}
}
