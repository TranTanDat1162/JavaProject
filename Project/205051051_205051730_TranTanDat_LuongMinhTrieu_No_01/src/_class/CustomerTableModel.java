package _class;

import java.util.List;

import javax.swing.table.TableModel;

import frame.SalesFrame;
import model.Customer;

public abstract class CustomerTableModel implements TableModel{
	private List<Customer> customers = SalesFrame.customer;
	public Object getCartAt(int row) {
      return customers.get( row ).getCustomerId();
	}
}
