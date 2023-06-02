package dao;

import model.Cart;
import model.Customer;
import _class.*;
import frame.SalesFrame;
import frame.SalesFrame.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.xdevapi.Table;



public class SalesDAOImpl implements SalesDAO {

	private static List<Customer> customer = new ArrayList<>();
	static String col[] = {"Name","Telephone"};
	
	
	@Override
	public void Search(String name) {
		// TODO Auto-generated method stub
		
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
