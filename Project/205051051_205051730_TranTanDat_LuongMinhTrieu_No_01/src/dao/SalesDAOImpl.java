package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.SortOrder;


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
	public void sortCustomerList(DefaultTableModel tableModel, AtomicBoolean isSorted) {
	    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
	    JTable table = SalesFrame.getTable();
	    table.setRowSorter(sorter);

	    List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	    sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING)); // Sắp xếp theo cột tên (cột 0) theo thứ tự tăng dần

	    if (isSorted.get()) {
	        sortKeys.set(0, new RowSorter.SortKey(0, SortOrder.DESCENDING)); // Nếu đã sắp xếp rồi, đảo ngược thứ tự sắp xếp
	    }

	    sorter.setSortKeys(sortKeys);
	    sorter.sort();
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
}
