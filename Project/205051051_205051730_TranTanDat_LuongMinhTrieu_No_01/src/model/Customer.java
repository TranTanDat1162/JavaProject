package model;

import java.util.List;

public class Customer {
	private int customerId;
	private String name;
	private int tel;
	Object cart = new Cart();
	
	public Customer(int customerID, String name, int tel) {
		this.customerId = customerID;
		this.name = name;
		this.tel = tel;
	}
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tel
	 */
	public int getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(int tel) {
		this.tel = tel;
	}

//	public Customer get(int rowIndex) {
//		Customer temp = null;
//		temp.customerId = this.customerId;
//		temp.name = this.name;
//		temp.tel = this.tel;
//		return temp;
//	}
	
}
