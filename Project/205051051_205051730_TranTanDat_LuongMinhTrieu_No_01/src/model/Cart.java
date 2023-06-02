package model;

import java.sql.Date;

public class Cart {
	private String customerID;
	private String itemname;
	private Date salesdate;
	private String Seller;
	private int fee;
	private int quantity;
	
	public void Cart(String customerID, String itemname, Date salesdate, String Seller, int fee, int quantity) {
		this.customerID = customerID;
		this.itemname = itemname;
		this.salesdate = salesdate;
		this.Seller = Seller;
		this.fee = fee;
		this.quantity = quantity;
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the itemname
	 */
	public String getItemname() {
		return itemname;
	}

	/**
	 * @param itemname the itemname to set
	 */
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	/**
	 * @return the salesdate
	 */
	public Date getSalesdate() {
		return salesdate;
	}

	/**
	 * @param salesdate the salesdate to set
	 */
	public void setSalesdate(Date salesdate) {
		this.salesdate = salesdate;
	}

	/**
	 * @return the seller
	 */
	public String getSeller() {
		return Seller;
	}

	/**
	 * @param seller the seller to set
	 */
	public void setSeller(String seller) {
		Seller = seller;
	}

	/**
	 * @return the fee
	 */
	public int getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(int fee) {
		this.fee = fee;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
