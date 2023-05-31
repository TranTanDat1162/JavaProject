package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;

import dao.UserDAO;
import dao.UserDAOImpl;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class LoginFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDAO userDAO;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginFrame() {
    	// Khởi tạo đối tượng UserDAOImpl
    	userDAO =  new UserDAOImpl();
        frame = new JFrame();
        frame.setBounds(100, 100, 399, 280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblUsername.setBounds(50, 61, 80, 14);
        frame.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblPassword.setBounds(50, 102, 80, 14);
        frame.getContentPane().add(lblPassword);
        
        JLabel lblForgotPassword;
        lblForgotPassword = new JLabel("Forgot Password?");
        lblForgotPassword.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblForgotPassword.setBounds(147, 193, 100, 14);
        lblForgotPassword.setForeground(Color.BLUE);
        lblForgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(lblForgotPassword);

        usernameField = new JTextField();
        usernameField.setText("trantandat");
        usernameField.setBounds(173, 58, 120, 20);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setText("205051051");
        passwordField.setBounds(173, 99, 120, 20);
        frame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 13));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if (userDAO.login(username, password)) {
                    // Đăng nhập thành công
                    JOptionPane.showMessageDialog(frame, "Login Successful");
                    // Mở module Bán Hàng
                    openSalesModule();
                } else {
                    // Đăng nhập không thành công
                    JOptionPane.showMessageDialog(frame, "Invalid username or password");
                }
            }
        });
        btnLogin.setBounds(65, 143, 90, 37);
        frame.getContentPane().add(btnLogin);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 13));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý hành động khi nhấn Cancel
                // Ví dụ: Đóng ứng dụng
                System.exit(0);
            }
        });
        btnCancel.setBounds(228, 143, 101, 37);
        frame.getContentPane().add(btnCancel);
        
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblNewLabel.setBounds(140, 11, 51, 25);
        frame.getContentPane().add(lblNewLabel);

		// Thêm chức năng Quên mật khẩu

        lblForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = JOptionPane.showInputDialog(frame, "Enter your username:");
                if (username != null) {
                    String password = userDAO.getPasswordByUsername(username);
                    if (password != null) {
                        JOptionPane.showMessageDialog(frame, "Your password is: " + password);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username.");
                    }
                }
            }
        });
        	frame.setVisible(true);
   		}

    private void openSalesModule() {
    	frame.dispose();
        SalesFrame salesFrame = new SalesFrame(); 
        salesFrame.setVisible(true); 
    }

}
