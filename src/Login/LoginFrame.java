package Login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	final String card1Text = "Card 1";
	final String card2Text = "Card 2";
	JPanel loginContentPane;
	static JTextField usernameField;
	static JPasswordField passwordField;

//	static String url = "jdbc:mysql://localhost:3306/testDb?useSSL=false";
//	static String user = "root";
//	static String password = "";
//	static String selectQuery = "Select * from userLogin where user=? and password=?";
	
	static JLabel errorLabel = new JLabel("Username and Password doesnt't matched");
	static JLabel usernameErrorLabel = new JLabel("Username is empty");
	static JLabel passwordErrorLabel = new JLabel("Password is empty");

	JButton resetButton = new JButton("Reset");
	JButton loginButton = new JButton("Login");
	JPanel loginPanel = new JPanel();
	JLabel logInLabel = new JLabel("Log In");
	JLabel passwordLabel = new JLabel("Password");
	JLabel iconPasswordLabel = new JLabel("");
	JLabel iconUsernameLabel = new JLabel("");
	JLabel usernameLabel = new JLabel("Username");
	JButton registerButton = new JButton("I don't have an account yet");

	/**
	 * Launch the application.
	 * @param insertQuery 
	 * @param selectQuery 
	 * @param password 
	 * @param user 
	 * @param url 
	 * 
	 * @return
	 */
	public static void init(String url, String user, String password, String selectQuery, String insertQuery) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame(url, user, password, selectQuery, insertQuery);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void clearErrors() {
		passwordErrorLabel.setVisible(false);
		usernameErrorLabel.setVisible(false);
		errorLabel.setVisible(false);
	}
	
	private void login(String url, String user, String password, String selectQuery, String insertQuery) {
		try (Connection connection = DriverManager.getConnection(url, user, password)) {

			if (connection != null) {
				// connection TEST
				System.out.println("Connected to the database");
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
				preparedStatement.setString(1, usernameField.getText());
				preparedStatement.setString(2, passwordField.getText());
				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					JOptionPane.showMessageDialog(null, "Username and Password matched");
					AccessedMenu menu = new AccessedMenu();
					menu.setVisible(true);
//						setVisible(false);
				} else {
					errorLabel.setVisible(true);
					JOptionPane.showMessageDialog(null, "Username and Password DO NOT matched");
					usernameField.setText("");
					passwordField.setText("");
				}
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Connection to the server failed.");

			System.out.println("An error occurred. Maybe user/password is invalid");
			System.out.println(ex);
			ex.printStackTrace();
		}
	}
	

	/**
	 * Create the frame.
	 */
	public LoginFrame(String url, String user, String password, String selectQuery, String insertQuery) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 390);
		loginContentPane = new JPanel();
		loginContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginContentPane);
		loginContentPane.setLayout(null);

		loginPanel.setBounds(12, 0, 366, 351);
		loginContentPane.add(loginPanel);
		loginPanel.setLayout(null);
		logInLabel.setForeground(Color.BLUE);

		logInLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		logInLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logInLabel.setBounds(0, 12, 366, 34);
		loginPanel.add(logInLabel);

		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(0, 63, 370, 15);
		errorLabel.setVisible(false);
		loginPanel.add(errorLabel);

		usernameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		usernameLabel.setBounds(68, 110, 109, 15);
		loginPanel.add(usernameLabel);

		iconUsernameLabel.setIcon(new ImageIcon("/home/sparrow/eclipse-workspace/Login with MySql/res/user.png"));
		iconUsernameLabel.setBounds(12, 90, 61, 48);
		loginPanel.add(iconUsernameLabel);

		passwordLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		passwordLabel.setBounds(74, 171, 109, 15);
		loginPanel.add(passwordLabel);

		iconPasswordLabel.setIcon(new ImageIcon("/home/sparrow/eclipse-workspace/Login with MySql/res/password.png"));
		iconPasswordLabel.setBounds(12, 150, 61, 48);
		loginPanel.add(iconPasswordLabel);

		usernameErrorLabel.setForeground(Color.RED);
		usernameErrorLabel.setBounds(223, 136, 136, 15);
		usernameErrorLabel.setVisible(false);
		loginPanel.add(usernameErrorLabel);

		passwordErrorLabel.setForeground(Color.RED);
		passwordErrorLabel.setBounds(223, 195, 136, 15);
		passwordErrorLabel.setVisible(false);
		loginPanel.add(passwordErrorLabel);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				passwordErrorLabel.setVisible(false);
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					// reset errors
					clearErrors();
					// before attempting login check if one of the field is empty
					if (passwordField.getText().isEmpty() || usernameField.getText().isEmpty()) {
						if (passwordField.getText().isEmpty()) {
							passwordErrorLabel.setVisible(true);

						}
						if (usernameField.getText().isEmpty()) {
							usernameErrorLabel.setVisible(true);
						}
						return;
					}
					// do login
					login(url, user, password, selectQuery, insertQuery);
				}
			}
		});
		passwordField.setBounds(199, 163, 162, 25);
		loginPanel.add(passwordField);

		usernameField = new JTextField();
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				usernameErrorLabel.setVisible(false);
			}
		});
		usernameField.setBounds(195, 104, 164, 27);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.BLUE);

		// LOGIN BUTTON
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Im going...");

				// reset errors
				clearErrors();
				// before attempting login check if one of the field is empty
				if (passwordField.getText().isEmpty() || usernameField.getText().isEmpty()) {
					if (passwordField.getText().isEmpty()) {
						passwordErrorLabel.setVisible(true);

					}
					if (usernameField.getText().isEmpty()) {
						usernameErrorLabel.setVisible(true);
					}
					return;
				}
				// do login
				login(url, user, password, selectQuery, insertQuery);

			}
		});
		loginButton.setFont(new Font("Dialog", Font.BOLD, 24));
		loginButton.setBounds(195, 231, 160, 42);
		loginPanel.add(loginButton);
		resetButton.setForeground(Color.WHITE);
		resetButton.setBackground(Color.RED);
		// ...LOGIN BUTTON

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearErrors();
				usernameField.setText("");
				passwordField.setText("");
			}
		});
		resetButton.setBounds(245, 285, 114, 25);
		loginPanel.add(resetButton);
		registerButton.setForeground(Color.BLUE);

		registerButton.setBounds(99, 320, 260, 19);
		loginPanel.add(registerButton);
		registerButton.setHorizontalAlignment(SwingConstants.RIGHT);
		registerButton.setBorderPainted(false);
		registerButton.setOpaque(false);
		registerButton.setBackground(Color.WHITE);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterForm registerForm = new RegisterForm(url, user, password, selectQuery, insertQuery);
				registerForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				registerForm.setResizable(false);
				registerForm.setLocationRelativeTo(null);
				registerForm.setVisible(true);
				setVisible(false);
			}
		});

		registerButton.setFont(new Font("Dialog", Font.BOLD, 12));

	}
}
