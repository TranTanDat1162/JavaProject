package dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Customer;

public interface SalesDAO {
	public void sortCustomerList(DefaultTableModel tableModel, AtomicBoolean isSorted);
	public List<Customer> search(String searchName);
	public List<Customer> getAllCustomers();
	public List<Customer> updateCartDAO();
	public DefaultTableModel ModelPrep();
	public void UpdateSQL(JTable table);
}
