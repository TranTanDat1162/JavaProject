package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	private static List<Customer> customerlist = new ArrayList<>();
	private Cart cart = null;
	static String col[] = {"Name","Telephone"};
	
	@Override
	public List<Customer> Search(String name, DefaultTableModel tableCustomers) {
	    List<Customer> searchResults = new ArrayList<>();
	    for (Customer customer : customerlist) {
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
	
//	public static List<Customer>  updateCustomerList(){
//		try(Connection connection = DatabaseConnector.getConnection()) {
//			PreparedStatement dm = connection.prepareStatement("SELECT * FROM customer");
//			ResultSet rs = dm.executeQuery();
//			customerlist.clear();
//			while(rs.next()){
//				Customer temp = new Customer(rs.getInt(1),rs.getString(2),rs.getInt(3));
//				customerlist.add(temp);
//			}
//		return customerlist;
//		} catch (SQLException e) {
//			throw new DatabaseActionException(e);
//		}
//	}	
//	
	public static List<Customer> updateCartDAO() {
		try(Connection connection = DatabaseConnector.getConnection()) {
			PreparedStatement dm = connection.prepareStatement("SELECT * FROM customer INNER JOIN cart on customer.customerid = cart.cartid;");
			ResultSet rs = dm.executeQuery();
			while(rs.next()){
				Cart cart = new Cart(rs.getString(5),rs.getString(6),rs.getDate(7).toString(),rs.getString(8),rs.getInt(9),rs.getInt(10));
				Customer temp = new Customer(rs.getInt(1),rs.getString(2),rs.getInt(3),cart);
				customerlist.add(temp);
			}
		return customerlist;
		} catch (SQLException e) {
			throw new DatabaseActionException(e);
		}
	}
	public static DefaultTableModel ModelPrep() {
		SalesFrame.customer = SalesDAOImpl.updateCartDAO();
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (Customer temp : customerlist) {
			Object[] t = {temp.getName(), temp.getTel()};
			tableModel.addRow(t);
		}
		return tableModel;
	}
	public static void UpdateSQL(JTable table) {
		int i = table.getSelectedRow();
		Customer cs = SalesFrame.customer.get(i);
		Cart c = SalesFrame.customer.get(i).getCart();
		if(cs.getCustomerId()==0)
			cs.setCustomerId(table.getRowCount());
		SalesFrame.customer.add(new Customer(table.getValueAt(i,0).toString(),Integer.parseInt(table.getValueAt(i,1).toString())));
		try(Connection connection = DatabaseConnector.getConnection()){
			String d = c.getSalesdate().toString();
			System.out.println(table.getRowCount());
			String query = "INSERT INTO `customer` VALUES (null,'"+cs.getName()+"',"+cs.getTel()+");";
			String query2 = "INSERT INTO `cart` VALUES (null,'"+cs.getCustomerId()+"','"+c.getItemname()+"','"+d+"','"+ c.getSeller()+"',"+c.getFee()+","+c.getQuantity() +");";
			
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate(query);
			stmt.executeUpdate(query2);
		} catch (SQLException e) {
			throw new DatabaseActionException(e);
		}
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
	
	public static void AddCustomer() {
		
	}	
}
