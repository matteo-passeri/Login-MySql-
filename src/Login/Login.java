//package Login;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.swing.JOptionPane;
//
//public class Login extends LoginFrame {
//	
//	private String url, user, password, selectQuery, insertQuery;
//
//	private static final long serialVersionUID = 1L;
//
////	static String url = "jdbc:mysql://localhost:3306/testDb?useSSL=false";
////	static String user = "root";
////	static String password = "";
////	static String selectQuery = "Select * from userLogin where user=? and password=?";
//
//	public static void Login(String url, String user, String password, String selectQuery, String insertQuery) {
////		super(url, user, password, selectQuery, insertQuery);
//		this.url = url;
//		this.user = user;
//		this.password = password;
//		this.selectQuery = selectQuery;
//		this.insertQuery = insertQuery;
//		
////		// reset errors
////		clearErrors();
//
//		// login
////		else {
//
//		try (Connection connection = DriverManager.getConnection(url, user, password)) {
//
//			if (connection != null) {
//				// connection TEST
//				System.out.println("Connected to the database");
//				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
//				preparedStatement.setString(1, usernameField.getText());
//				preparedStatement.setString(2, passwordField.getText());
//				ResultSet result = preparedStatement.executeQuery();
//				if (result.next()) {
//					JOptionPane.showMessageDialog(null, "Username and Password matched");
//					AccessedMenu menu = new AccessedMenu();
//					menu.setVisible(true);
////						setVisible(false);
//				} else {
//					errorLabel.setVisible(true);
//					JOptionPane.showMessageDialog(null, "Username and Password DO NOT matched");
//					usernameField.setText("");
//					passwordField.setText("");
//				}
//			}
//		} catch (SQLException ex) {
//			JOptionPane.showMessageDialog(null, "Connection to the server failed.");
//
//			System.out.println("An error occurred. Maybe user/password is invalid");
//			System.out.println(ex);
//			ex.printStackTrace();
//		}
//	}
//
//}
//
////}
