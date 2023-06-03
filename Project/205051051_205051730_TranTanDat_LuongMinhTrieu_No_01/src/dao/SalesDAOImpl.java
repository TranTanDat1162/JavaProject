package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import _class.DatabaseActionException;
import _class.DatabaseConnector;
import frame.SalesFrame;
import model.Cart;
import model.Customer;

public class SalesDAOImpl implements SalesDAO {

	private static List<Customer> customer = new ArrayList<>();
	static String col[] = {"Name","Telephone"};
	
	
	@Override
	public List<Customer> Search(String name, DefaultTableModel tableCustomers) {
	    List<Customer> searchResults = new ArrayList<>();

	    for (Customer customer : customer) {
	        if (customer.getName().equalsIgnoreCase(name)) {
	            searchResults.add(customer);
	        }
	    }

	    updateCustomerTable(searchResults, tableCustomers);
	    return searchResults;
	}

	private void updateCustomerTable(List<Customer> searchResults, DefaultTableModel tableCustomers) {
	    tableCustomers.setRowCount(0); // Xóa tất cả các dòng hiện tại trong bảng

	    for (Customer customer : searchResults) {
	        Object[] rowData = { customer.getName(), customer.getTel() };
	        tableCustomers.addRow(rowData);
	    }
	}


	@Override
	public void Add(String name, int tel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Save(Customer customer, Cart cart) {
		// TODO Auto-generated method stub
		
	}
	
	public static List<Customer>  updateCustomerDAO(){
		try(Connection connection = DatabaseConnector.getConnection()) {
			PreparedStatement dm = connection.prepareStatement("SELECT * FROM customer");
			ResultSet rs = dm.executeQuery();
			customer.clear();
			while(rs.next()){
				Customer temp = new Customer(rs.getInt(1),rs.getString(2),rs.getInt(3));
				customer.add(temp);
			}
		return customer;
		} catch (SQLException e) {
			throw new DatabaseActionException(e);
		}
	}	
	
	public static DefaultTableModel ModelPrep() {
		SalesFrame.customer = SalesDAOImpl.updateCustomerDAO();
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (Customer temp : customer) {
			Object[] t = {temp.getName(), temp.getTel()};
			tableModel.addRow(t);
		}
		return tableModel;
	}
	
	public static void AddCustomer() {
		
	}
//	public static List<Customer>  updateCustomerList() {
//		int i = 0;
//		List<Customer> dataset = null;
//		for (Customer customertemp : customer) {
//			int id = customertemp.getCustomerId();
//			String name = customertemp.getName();
//			int tel = customertemp.getTel();
//			Customer data = new Customer(id,name,tel);
//			dataset = data;
//			i++;
//		}
//		return dataset;
//	}	
}
