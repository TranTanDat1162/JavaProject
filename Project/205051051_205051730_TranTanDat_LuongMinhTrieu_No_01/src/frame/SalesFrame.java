package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.*;
import org.jdatepicker.impl.*;

import _class.DateLabelFormatter;
import model.Cart;
import model.Customer;

import java.awt.GridLayout;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import _class.DateLabelFormatter;
import dao.SalesDAO;
import dao.SalesDAOImpl;
import model.Customer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SalesFrame extends JFrame {

	/**
	 *
	 *
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private LoginFrame loginFrame;
	private JFrame frame;
	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	private DefaultTableModel tableModel = new DefaultTableModel();
	public static List<Customer> customer = new ArrayList<Customer>();
	Calendar calendar = Calendar.getInstance();
	public static JTable table;

	public JTextField txtfItemName;
	public JTextField txtfFee;
	public JTextField txtfQuant;
	public JTextField txtfSalesPerson;
	public UtilDateModel model;

	SalesDAO salesDAO;
	Object[][] data;

	
	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesFrame frame = new SalesFrame();
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }
	
	public static JTable getTable() {
	    return table;
	}
	
	public void setTable() {
		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		tableModel = SalesDAOImpl.ModelPrep();
		table.setModel(tableModel);
	}
	
	public static void updTable(JTable T) {
		table = T;
	}
	
	public int[] date(int i) {
		String dateArr[] = customer.get(i).getCart().getSalesdate().split("-");
		int day = Integer.parseInt(dateArr[0]) ;
		int month = Integer.parseInt(dateArr[1]);
		int year = Integer.parseInt(dateArr[2]);
		int[] temp = {day,month,year};
		return temp;
	}
	/**	
	 * Create the frame.
	 * @throws IOException 
	 */
	public SalesFrame() {


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SaleList");

		frame = new JFrame();
		salesDAO = new SalesDAOImpl();
		setType(Type.UTILITY);

		setBounds(100, 100, 800, 550);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{791, 0};
		gridBagLayout.rowHeights = new int[]{50, 229, 150, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1, 3, 0, 0));
		
		JButton btnSearch = new JButton("Search customer");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnSearch);

		btnSearch.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchName = "";
		        boolean found = false;

		        while (!found && searchName != null) {
		            searchName = JOptionPane.showInputDialog("Enter customer name:");

		            if (searchName != null) {
		                DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		                int rowCount = tableModel.getRowCount();
		                found = false;

		                for (int i = 0; i < rowCount; i++) {
		                    String customerName = (String) table.getValueAt(i, 0);
		                    System.out.println();
		                    if (customerName.equalsIgnoreCase(searchName)) {
		                        table.getSelectionModel().setSelectionInterval(i, i);
		                        table.scrollRectToVisible(table.getCellRect(i, 0, true));
		                        found = true;
		                        break;
		                    }
		                }

		                if (!found) {
		                    JOptionPane.showMessageDialog(SalesFrame.this, "Customer not found.");
		                }
		            }
		        }
		    }
		});

		JButton btnNewCust = new JButton("Add new customer");
		btnNewCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCustFrame newcustframe = new AddCustFrame(table);
				newcustframe.setVisible(true);
				
			}
		});
		
		btnNewCust.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnNewCust);

		// Tạo nút "Sort customer"
		AtomicBoolean isSorted = new AtomicBoolean(false); // Khởi tạo AtomicBoolean với giá trị ban đầu là false
		JButton btnSort = new JButton("Sort customer");
		btnSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnSort);

		// Thêm xử lý sự kiện cho nút "Sort customer"
		btnSort.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Sắp xếp danh sách tên
		        DefaultTableModel tableModel = (DefaultTableModel) SalesFrame.getTable().getModel();
		        SalesDAOImpl salesDAO = new SalesDAOImpl();
		        salesDAO.sortCustomerList(tableModel, isSorted);
		     
		        // Đảo ngược trạng thái cờ
		        isSorted.set(!isSorted.get()); // Thay đổi giá trị của AtomicBoolean
		        table.setRowSelectionInterval(0, 0);
		    }
		});	

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		getContentPane().add(panel_3, gbc_panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane);
		
		//Setting up the JTable's model
		setTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{111, 370, 0};
		gbl_panel_1.rowHeights = new int[]{29, 29, 29, 29, 29, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblItemName = new JLabel("Item name:");
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.anchor = GridBagConstraints.WEST;
		gbc_lblItemName.fill = GridBagConstraints.VERTICAL;
		gbc_lblItemName.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 0;
		panel_1.add(lblItemName, gbc_lblItemName);
		
		txtfItemName = new JTextField();
		txtfItemName.setText(customer.get(0).getCart().getItemname());
		GridBagConstraints gbc_txtfItemName = new GridBagConstraints();
		gbc_txtfItemName.insets = new Insets(0, 0, 5, 0);
		gbc_txtfItemName.fill = GridBagConstraints.BOTH;
		gbc_txtfItemName.gridx = 1;
		gbc_txtfItemName.gridy = 0;
		
		panel_1.add(txtfItemName, gbc_txtfItemName);
		txtfItemName.setColumns(10);
		
		JLabel lblItemName_1 = new JLabel("Sales date:");
		lblItemName_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName_1 = new GridBagConstraints();
		
		gbc_lblItemName_1.fill = GridBagConstraints.BOTH;
		gbc_lblItemName_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName_1.gridx = 0;
		gbc_lblItemName_1.gridy = 1;
		panel_1.add(lblItemName_1, gbc_lblItemName_1);
		
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		model = new UtilDateModel();
		int defaultdate[] = date(0);
		model.setDate(defaultdate[0], defaultdate[1], defaultdate[2]);
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());

		GridBagConstraints gbc_DatePanel = new GridBagConstraints();
		gbc_DatePanel.insets = new Insets(0, 0, 5, 0);
		gbc_DatePanel.fill = GridBagConstraints.BOTH;
		gbc_DatePanel.gridx = 1;
		gbc_DatePanel.gridy = 1;
		panel_1.add(datePicker, gbc_DatePanel);
	
		JLabel lblItemName_2 = new JLabel("Sales person:");

		lblItemName_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName_2 = new GridBagConstraints();
		gbc_lblItemName_2.anchor = GridBagConstraints.WEST;
		gbc_lblItemName_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblItemName_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName_2.gridx = 0;
		gbc_lblItemName_2.gridy = 2;
		panel_1.add(lblItemName_2, gbc_lblItemName_2);
		
		txtfSalesPerson = new JTextField();
		txtfSalesPerson.setColumns(10);
		txtfSalesPerson.setText(customer.get(0).getCart().getSeller());
		GridBagConstraints gbc_txtfSalesPerson = new GridBagConstraints();
		gbc_txtfSalesPerson.insets = new Insets(0, 0, 5, 0);
		gbc_txtfSalesPerson.fill = GridBagConstraints.BOTH;
		gbc_txtfSalesPerson.gridx = 1;
		gbc_txtfSalesPerson.gridy = 2;
		panel_1.add(txtfSalesPerson, gbc_txtfSalesPerson);
		
		JLabel lblItemName_3 = new JLabel("Fee (đ):");
		lblItemName_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName_3 = new GridBagConstraints();
		gbc_lblItemName_3.anchor = GridBagConstraints.WEST;
		gbc_lblItemName_3.fill = GridBagConstraints.VERTICAL;
		gbc_lblItemName_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName_3.gridx = 0;
		gbc_lblItemName_3.gridy = 3;
		panel_1.add(lblItemName_3, gbc_lblItemName_3);
		
		txtfFee = new JTextField();
		txtfFee.setColumns(10);

		txtfFee.setText(Integer.toString(customer.get(0).getCart().getFee()));
		GridBagConstraints gbc_txtfFee = new GridBagConstraints();
		gbc_txtfFee.insets = new Insets(0, 0, 5, 0);
		gbc_txtfFee.fill = GridBagConstraints.BOTH;
		gbc_txtfFee.gridx = 1;
		gbc_txtfFee.gridy = 3;
		panel_1.add(txtfFee, gbc_txtfFee);
		
		JLabel lblItemName_4 = new JLabel("Quantity:");
		lblItemName_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName_4 = new GridBagConstraints();
		gbc_lblItemName_4.anchor = GridBagConstraints.WEST;
		gbc_lblItemName_4.fill = GridBagConstraints.VERTICAL;
		gbc_lblItemName_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblItemName_4.gridx = 0;
		gbc_lblItemName_4.gridy = 4;
		panel_1.add(lblItemName_4, gbc_lblItemName_4);
		
		txtfQuant = new JTextField();
		txtfQuant.setColumns(10);
		txtfQuant.setText(Integer.toString(customer.get(0).getCart().getQuantity()));
		GridBagConstraints gbc_txtfQuant = new GridBagConstraints();
		gbc_txtfQuant.fill = GridBagConstraints.BOTH;
		gbc_txtfQuant.gridx = 1;
		gbc_txtfQuant.gridy = 4;
		panel_1.add(txtfQuant, gbc_txtfQuant);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{41, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
//		table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener(){
//	        public void valueChanged(ListSelectionEvent event) {
//	        	Cart temp = customer.get(table.getSelectedRow()).getCart();
////	        	calendar.setTime(temp.getSalesdate());
//	        	int[]tdate = date(table.getSelectedRow());
//	        	txtfSalesPerson.setText(temp.getSeller());
//	    		txtfItemName.setText(temp.getItemname());
////	    		datePicker.getModel().setDate(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
//	    		
//	    		datePicker.getModel().setDate(tdate[0],tdate[1]-1,tdate[2]);
//	    		datePicker.getModel().setSelected(true);
//	    		txtfFee.setText(Integer.toString(temp.getFee()));
//	    		txtfQuant.setText(Integer.toString(temp.getQuantity()));
//	        }
//	    });
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable source = (JTable)e.getSource();
	            int row = source.rowAtPoint(e.getPoint());
	            System.out.println(row);
	            Cart temp = customer.get(row).getCart();
	            String s=source.getValueAt(row, 0)+"";
	            System.out.println(s);

	            for (Customer i : customer) {
					if(i.getName().equals(s)) {
						System.out.println(i.getName());
			            temp = i.getCart();
					}
				}
	            
//	            Cart temp = customer.get(row).getCart();
//	        	calendar.setTime(temp.getSalesdate());
	        	int[]tdate = date(row);
	        	try {
	        		txtfSalesPerson.setText(temp.getSeller());
		    		txtfItemName.setText(temp.getItemname());
//		    		datePicker.getModel().setDate(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
		    		datePicker.getModel().setDate(tdate[0],tdate[1]-1,tdate[2]);
		    		datePicker.getModel().setSelected(true);
		    		txtfFee.setText(Integer.toString(temp.getFee()));
		    		txtfQuant.setText(Integer.toString(temp.getQuantity()));
				} catch (Exception e2) {
					
				}	            
			}
		});
		
		JButton btnSave = new JButton("Save Order");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cart temp = customer.get(table.getSelectedRow()).getCart();
				temp.setItemname(txtfItemName.getText());
				temp.setSalesdate(datePicker.getJFormattedTextField().getText());
				temp.setSeller(txtfSalesPerson.getText());
				temp.setFee(Integer.parseInt(txtfFee.getText()));
				temp.setQuantity(Integer.parseInt(txtfQuant.getText()));
				SalesDAOImpl.UpdateSQL(table,true);
			}
		});
		
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 0;
		panel_2.add(btnSave, gbc_btnSave);	
		
		JButton btnNewButton_3 = new JButton("Log Out");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openLoginModule();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 0;
		panel_2.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnSave_1 = new JButton("Delete Order");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesDAOImpl.UpdateSQL(table,false);
				setTable();
				scrollPane.setViewportView(table);
			}
		});
		btnSave_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnSave_1 = new GridBagConstraints();
		gbc_btnSave_1.gridx = 2;
		gbc_btnSave_1.gridy = 0;
		panel_2.add(btnSave_1, gbc_btnSave_1);
	}
	
	private void openLoginModule() {
        this.setVisible(false);
        tableModel.setRowCount(0);
        table.removeAll();
        LoginFrame loginFrame = new LoginFrame(); 
        loginFrame.setVisible(false);
    }
}
