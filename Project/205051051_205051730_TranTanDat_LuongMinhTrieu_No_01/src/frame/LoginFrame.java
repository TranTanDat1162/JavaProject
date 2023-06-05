package frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDAO userDAO;
    private ResourceBundle messages;

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

    public LoginFrame() {
        userDAO = new UserDAOImpl();
        messages = ResourceBundle.getBundle("messages");

        frame = new JFrame();
        frame.setResizable(false);
        frame.setType(Type.UTILITY);
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

        JLabel lblForgotPassword = new JLabel("Forgot Password?");
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
        btnLogin.setActionCommand("login"); // Thiết lập ActionCommand
        ActionListener loginActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                User user = userDAO.login(username);

                if (user == null) {
                    // Người dùng không tồn tại
                    showMessage(messages.getString("userNotExist"), JOptionPane.ERROR_MESSAGE);
                } else {
                    // Người dùng tồn tại
                    if (user.getPassword().equals(password)) {
                        // Đăng nhập thành công
                        showMessage(messages.getString("loginSuccessful"), JOptionPane.INFORMATION_MESSAGE);
                        try {
                            openSalesModule();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        // Sai mật khẩu
                        showMessage(messages.getString("incorrectPassword"), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };
        usernameField.addActionListener(loginActionListener);
        passwordField.addActionListener(loginActionListener);

        btnLogin.addActionListener(loginActionListener);
        btnLogin.setBounds(441, 243, 90, 37);
        frame.getContentPane().add(btnLogin);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
            InputStream inputStream = getClass().getResourceAsStream("/images/logouef.png");
            Image image = ImageIO.read(inputStream);
            Image scaledImage = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageLabel.setBounds(28, 32, 300, 307);
        frame.getContentPane().add(imageLabel);

        lblForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username;
                User user;

                do {
                    username = JOptionPane.showInputDialog(frame, messages.getString("enterUsername"));
                    if (username != null) {
                        user = userDAO.getPasswordByUsername(username);
                        if (user == null) {
                            showMessage(messages.getString("invalidUsername"), JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        return;
                    }
                } while (user == null);

                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(3, 2));

                JPasswordField newPasswordField = new JPasswordField();
                JPasswordField confirmPasswordField = new JPasswordField();

                panel.add(new JLabel(messages.getString("newPassword")));
                panel.add(newPasswordField);
                panel.add(new JLabel(messages.getString("confirmPassword")));
                panel.add(confirmPasswordField);

                int option;
                String newPassword;
                String confirmPassword;
                do {
                    option = JOptionPane.showOptionDialog(frame, panel, messages.getString("changePassword"),
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                    if (option == JOptionPane.OK_OPTION) {
                        newPassword = String.valueOf(newPasswordField.getPassword());
                        confirmPassword = String.valueOf(confirmPasswordField.getPassword());

                        if (!newPassword.equals(confirmPassword)) {
                            showMessage(messages.getString("passwordsDoNotMatch"), JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        return;
                    }
                } while (!newPassword.equals(confirmPassword));

                if (option == JOptionPane.OK_OPTION) {
                    user.setPassword(newPassword);
                    boolean updated = userDAO.updateUser(user);
                    if (updated) {
                        showMessage(messages.getString("passwordChanged"), JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        showMessage(messages.getString("failedToChangePassword"), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        frame.setVisible(true);
    }

    private void showMessage(String message, int messageType) {
        JOptionPane.showMessageDialog(frame, message, "Message", messageType);
    }

    private void openSalesModule() {
        frame.dispose();
        SalesFrame salesFrame = new SalesFrame();
        salesFrame.setVisible(true);
    }
}
