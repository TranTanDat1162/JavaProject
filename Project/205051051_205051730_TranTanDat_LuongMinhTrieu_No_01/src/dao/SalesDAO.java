package dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.table.DefaultTableModel;

import model.Cart;
import model.Customer;

public interface SalesDAO {
	public List<Customer> Search(String searchName, DefaultTableModel tableModel);
	public void Add(String name, int tel);
	public void Save(Customer customer, Cart cart);
	public void sortCustomerList(DefaultTableModel tableModel, AtomicBoolean isSorted);
}
