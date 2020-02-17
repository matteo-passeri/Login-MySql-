package Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RegisterForm extends LoginFrame {
	
	private static final long serialVersionUID = 1L;

	public JTextField firstNameField;
	public JTextField lastNameField;
	public JTextField emailField;
	public JTextField usernameField;
	public JPasswordField passwordField;
	public JPasswordField password2Field;
	public JPanel registerContentPane = new JPanel();

	public void clearAll() {
		firstNameField.setText("");
		lastNameField.setText("");
		emailField.setText("");
		usernameField.setText("");
		passwordField.setText("");
		password2Field.setText("");
	}
	
	public void register(String url, String user, String password, String selectQuery, String insertQuery) {
		try (Connection connection = DriverManager.getConnection(url, user, password)) {
			if (connection != null) {
				System.out.println("Connection successfull!");
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
				preparedStatement.setString(1, firstNameField.getText());
				preparedStatement.setString(2, lastNameField.getText());
				preparedStatement.setString(3, emailField.getText());
				preparedStatement.setString(4, usernameField.getText());
				preparedStatement.setString(5, passwordField.getText());
				preparedStatement.executeUpdate();
				JOptionPane.showMessageDialog(null, "REGISTRATION COMPLETE");
				LoginFrame.init(url, user, password, selectQuery, insertQuery);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);

		}
	}

//	public void loadLoginForm(){
//		LoginFrame loginFrame = new LoginFrame();
//		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		loginFrame.setResizable(false);
//		loginFrame.setLocationRelativeTo(null);
//		loginFrame.setVisible(true);
//		setVisible(false);
//	}

	/**
	 * Create the frame.
	 */
	public RegisterForm(String url, String user, String password, String selectQuery, String insertQuery) {
		super(url, user, password, selectQuery, insertQuery);
//		this.register = new Register(url, user, password, selectQuery, insertQuery);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 501);
		registerContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		registerContentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(registerContentPane);

		JPanel panel = new JPanel();
		registerContentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel registerLabel = new JLabel("Registration Form");
		registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerLabel.setFont(new Font("Dialog", Font.BOLD, 28));
		registerLabel.setBounds(12, 12, 549, 48);
		panel.add(registerLabel);

		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		firstNameLabel.setBounds(136, 72, 114, 15);
		panel.add(firstNameLabel);

		firstNameField = new JTextField();
		firstNameField.setBounds(268, 70, 124, 19);
		panel.add(firstNameField);
		firstNameField.setColumns(10);

		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lastNameLabel.setBounds(136, 101, 114, 15);
		panel.add(lastNameLabel);

		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		lastNameField.setBounds(268, 99, 124, 19);
		panel.add(lastNameField);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setBounds(136, 130, 114, 15);
		panel.add(emailLabel);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(268, 128, 124, 19);
		panel.add(emailField);

		JLabel userLabel = new JLabel("Username");
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userLabel.setBounds(136, 159, 114, 15);
		panel.add(userLabel);

		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(268, 157, 124, 19);
		panel.add(usernameField);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setBounds(136, 188, 114, 15);
		panel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(268, 186, 124, 19);
		panel.add(passwordField);

		JLabel password2Label = new JLabel("Retype");
		password2Label.setHorizontalAlignment(SwingConstants.RIGHT);
		password2Label.setBounds(136, 215, 114, 29);
		panel.add(password2Label);

		password2Field = new JPasswordField();
		password2Field.setBounds(268, 233, 124, 19);
		panel.add(password2Field);

		JLabel retypePassword2Label = new JLabel("Password");
		retypePassword2Label.setHorizontalAlignment(SwingConstants.RIGHT);
		retypePassword2Label.setBounds(146, 235, 104, 15);
		panel.add(retypePassword2Label);

		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				register(url, user, password, selectQuery, insertQuery);

			}
		});
		registerButton.setBackground(Color.BLUE);
		registerButton.setForeground(Color.WHITE);
		registerButton.setBounds(214, 285, 114, 25);
		panel.add(registerButton);

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAll();
			}
		});
		resetButton.setForeground(Color.RED);
		resetButton.setBounds(447, 424, 114, 25);
		panel.add(resetButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame.init(url, user, password, selectQuery, insertQuery);
				setVisible(false); // TO CHANGE
			}
		});
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.RED);
		cancelButton.setBounds(12, 424, 114, 25);
		panel.add(cancelButton);
	}
}
