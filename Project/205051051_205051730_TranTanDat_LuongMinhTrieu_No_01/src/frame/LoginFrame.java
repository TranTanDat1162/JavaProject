package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;

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
        frame.setBounds(100, 100, 795, 396);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(376, 88, 90, 32);
        frame.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setBounds(376, 167, 90, 14);
        frame.getContentPane().add(lblPassword);
        
        JLabel lblForgotPassword;
        lblForgotPassword = new JLabel("Forgot Password?");
        lblForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblForgotPassword.setBounds(520, 307, 141, 32);
        lblForgotPassword.setForeground(Color.BLUE);
        lblForgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.getContentPane().add(lblForgotPassword);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        usernameField.setText("trantandat");
        usernameField.setBounds(483, 78, 290, 53);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setText("205051051");
        passwordField.setBounds(483, 151, 290, 52);
        frame.getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if (userDAO.login(username, password) != null) {
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
        btnLogin.setBounds(441, 243, 90, 37);
        frame.getContentPane().add(btnLogin);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý hành động khi nhấn Cancel
                // Ví dụ: Đóng ứng dụng
                System.exit(0);
            }
        });
        btnCancel.setBounds(650, 243, 101, 37);
        frame.getContentPane().add(btnCancel);
        
        JLabel lblNewLabel = new JLabel("Login Form");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(376, 11, 397, 56);
        frame.getContentPane().add(lblNewLabel);

		JLabel imageLabel = new JLabel();
		try {
		    InputStream inputStream = getClass().getResourceAsStream("/image/logouef.png");
		    Image image = ImageIO.read(inputStream);
		    Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		    imageLabel.setIcon(new ImageIcon(scaledImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		imageLabel.setBounds(28, 32, 300, 307);
		frame.getContentPane().add(imageLabel);

		// Thêm chức năng Quên mật khẩu

		lblForgotPassword.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String username = JOptionPane.showInputDialog(frame, "Enter your username:");
		        if (username != null) {
		            User user = userDAO.getPasswordByUsername(username);
		            if (user != null) {
		                String password = user.getPassword();
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
