package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import _class.DatabaseActionException;
import _class.DatabaseConnector;
import frame.SalesFrame;
import model.Cart;
import model.Customer;

public class SalesDAOImpl implements SalesDAO {

	private static List<Customer> customerlist = new ArrayList<>();
	static String col[] = {"Name","Telephone"};
	
	@Override
	public List<Customer> search(String searchName) {
	    List<Customer> searchResults = new ArrayList<>();
	    boolean customerFound = false;

	    for (Customer customer : customerlist) {
	        if (customer.getName().toLowerCase().contains(searchName.toLowerCase())) {
	            searchResults.add(customer);
	            customerFound = true;
	        }
	    }

	    if (!customerFound) {
	        JOptionPane.showMessageDialog(null, "Customer not found.", "Search Result", JOptionPane.ERROR_MESSAGE);
	    }

	    return searchResults;
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



	public List<Customer> getAllCustomers() {
	    List<Customer> customerList = new ArrayList<>();

	    String query = "SELECT * FROM customer";

	    try (Connection connection = DatabaseConnector.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {
	            String name = resultSet.getString("name");
	            int tel = resultSet.getInt("tel");

	            Customer customer = new Customer(name, tel);
	            customerList.add(customer);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return customerList;
	}


	
	public static List<Customer> updateCartDAO() {
		try(Connection connection = DatabaseConnector.getConnection()) {
			PreparedStatement dm = connection.prepareStatement("SELECT * FROM customer INNER JOIN cart on customer.customerid = cart.cartid;");
			ResultSet rs = dm.executeQuery();
			customerlist.clear();
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
		System.out.println(i);
		Customer cs = SalesFrame.customer.get(i);
		Cart c = SalesFrame.customer.get(i).getCart();
		if(cs.getCustomerId()==0)
			cs.setCustomerId(table.getRowCount());
		SalesFrame.customer.add(new Customer(table.getValueAt(i,0).toString(),Integer.parseInt(table.getValueAt(i,1).toString())));
		try(Connection connection = DatabaseConnector.getConnection()){
			String d = c.getSalesdate().toString();
			String query = "INSERT INTO `customer` VALUES (null,'"+cs.getName()+"',"+cs.getTel()+");";
			String query2 = "INSERT INTO `cart` VALUES (null,'"+cs.getCustomerId()+"','"+c.getItemname()+"','"+d+"','"+ c.getSeller()+"',"+c.getFee()+","+c.getQuantity() +");";
			
			Statement stmt = connection.createStatement();
			
			stmt.executeUpdate(query);
			stmt.executeUpdate(query2);
		} catch (SQLException e) {
			throw new DatabaseActionException(e);
		}
	}
}
