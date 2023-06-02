package _class;

import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import salesdao.SalesDAOImpl;

public class CustomerTableModel extends AbstractTableModel{
	// TableModel's column Names
	private List<Customer> customers;

	
	public CustomerTableModel(List<Customer> customer) {
		this.customers = new ArrayList<Customer>(customers);
	}
    // TableModel's data
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = "??";
        Customer customer = customers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = customer.getCustomerId();
                break;
            case 1:
                value = customer.getName();
                break;
            case 2:
                value = customer.getTel();
                break;
        }

        return value;
	}

}
