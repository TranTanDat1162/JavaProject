package dao;

import model.Cart;
import model.Customer;

public interface SalesDAO {
	public void Search(String name);
	public void Add(String name, int tel);
	public void Save(Customer customer, Cart cart);
}
