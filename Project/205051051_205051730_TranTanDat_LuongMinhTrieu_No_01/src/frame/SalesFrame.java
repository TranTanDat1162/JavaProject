package frame;

import java.awt.EventQueue;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import _class.CustomerTableModel;
import dao.SalesDAOImpl;
import dao.SalesDAOImpl.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Customer;

import java.awt.GridLayout;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.lang.reflect.Array;
public class SalesFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private LoginFrame loginFrame;
//	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	public static List<Customer> customer = new ArrayList<Customer>();
//	public static List<String> customermodel = new ArrayList<String>();
	public static JTable table;
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
	public void setTable() {
		table = new JTable();
		tableModel = SalesDAOImpl.ModelPrep();
		table.setModel(tableModel);
	}
	/**	
	 * Create the frame.
	 */
	public SalesFrame() {
		
		setTitle("SaleList");
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
		
		JButton btnNewCust = new JButton("Add new customer");
		btnNewCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCustFrame newcustframe = new AddCustFrame();
				newcustframe.setVisible(true);
			}
		});
		btnNewCust.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnNewCust);
		
		JButton btnSort = new JButton("Sort customer");
		btnSort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(btnSort);
		
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
		gbc_panel_1.anchor = GridBagConstraints.WEST;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{135, 183, 370, 0};
		gbl_panel_1.rowHeights = new int[]{29, 29, 29, 29, 29, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblItemName = new JLabel("New label");
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.fill = GridBagConstraints.BOTH;
		gbc_lblItemName.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName.gridx = 1;
		gbc_lblItemName.gridy = 0;
		panel_1.add(lblItemName, gbc_lblItemName);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 0;
		panel_1.add(textArea, gbc_textArea);
		
		JLabel lblItemName_1 = new JLabel("New label");
		lblItemName_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName_1 = new GridBagConstraints();
		gbc_lblItemName_1.fill = GridBagConstraints.BOTH;
		gbc_lblItemName_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName_1.gridx = 1;
		gbc_lblItemName_1.gridy = 1;
		panel_1.add(lblItemName_1, gbc_lblItemName_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_1.gridx = 2;
		gbc_textArea_1.gridy = 1;
		panel_1.add(textArea_1, gbc_textArea_1);
		
		JLabel lblItemName_2 = new JLabel("New label");
		lblItemName_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName_2 = new GridBagConstraints();
		gbc_lblItemName_2.fill = GridBagConstraints.BOTH;
		gbc_lblItemName_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName_2.gridx = 1;
		gbc_lblItemName_2.gridy = 2;
		panel_1.add(lblItemName_2, gbc_lblItemName_2);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_textArea_2 = new GridBagConstraints();
		gbc_textArea_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea_2.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_2.gridx = 2;
		gbc_textArea_2.gridy = 2;
		panel_1.add(textArea_2, gbc_textArea_2);
		
		JLabel lblItemName_3 = new JLabel("New label");
		lblItemName_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName_3 = new GridBagConstraints();
		gbc_lblItemName_3.fill = GridBagConstraints.BOTH;
		gbc_lblItemName_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName_3.gridx = 1;
		gbc_lblItemName_3.gridy = 3;
		panel_1.add(lblItemName_3, gbc_lblItemName_3);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_textArea_3 = new GridBagConstraints();
		gbc_textArea_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea_3.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_3.gridx = 2;
		gbc_textArea_3.gridy = 3;
		panel_1.add(textArea_3, gbc_textArea_3);
		
		JLabel lblItemName_4 = new JLabel("New label");
		lblItemName_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblItemName_4 = new GridBagConstraints();
		gbc_lblItemName_4.fill = GridBagConstraints.BOTH;
		gbc_lblItemName_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblItemName_4.gridx = 1;
		gbc_lblItemName_4.gridy = 4;
		panel_1.add(lblItemName_4, gbc_lblItemName_4);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setFont(new Font("Arial", Font.PLAIN, 16));
		GridBagConstraints gbc_textArea_4 = new GridBagConstraints();
		gbc_textArea_4.fill = GridBagConstraints.BOTH;
		gbc_textArea_4.gridx = 2;
		gbc_textArea_4.gridy = 4;
		panel_1.add(textArea_4, gbc_textArea_4);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{41, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton btnNewButton_3_1 = new JButton("New button");
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton_3_1 = new GridBagConstraints();
		gbc_btnNewButton_3_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3_1.gridx = 0;
		gbc_btnNewButton_3_1.gridy = 0;
		panel_2.add(btnNewButton_3_1, gbc_btnNewButton_3_1);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 0;
		panel_2.add(btnNewButton_3, gbc_btnNewButton_3);
	}

}
