//package Login;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//import javax.swing.JOptionPane;
//
//public class Register extends RegisterForm {
//
//	private static final long serialVersionUID = 1L;
//	
//	public Register(String url, String user, String password, String selectQuery, String insertQuery) {
//		super(url, user, password, selectQuery, insertQuery);
//		try (Connection connection = DriverManager.getConnection(url, user, password)) {
//			if (connection != null) {
//				System.out.println("Connection successfull!");
//				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
//				preparedStatement.setString(1, firstNameField.getText());
//				preparedStatement.setString(2, lastNameField.getText());
//				preparedStatement.setString(3, emailField.getText());
//				preparedStatement.setString(4, usernameField.getText());
//				preparedStatement.setString(5, passwordField.getText());
//				preparedStatement.executeUpdate();
//				JOptionPane.showMessageDialog(null, "REGISTRATION COMPLETE");
//				LoginFrame.init(url, user, password, selectQuery, insertQuery);
//			}
//		} catch (Exception ex) {
//			JOptionPane.showMessageDialog(null, ex);
//
//		}
//	}
//
////	public void Register(String url, String user, String password, String selectQuery, String insertQuery) {
////		try (Connection connection = DriverManager.getConnection(url, user, password)) {
////			if (connection != null) {
////				System.out.println("Connection successfull!");
////				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
////				preparedStatement.setString(1, firstNameField.getText());
////				preparedStatement.setString(2, lastNameField.getText());
////				preparedStatement.setString(3, emailField.getText());
////				preparedStatement.setString(4, usernameField.getText());
////				preparedStatement.setString(5, passwordField.getText());
////				preparedStatement.executeUpdate();
////				JOptionPane.showMessageDialog(null, "REGISTRATION COMPLETE");
////				LoginFrame.init(url, user, password, selectQuery, insertQuery);
////			}
////		} catch (Exception ex) {
////			JOptionPane.showMessageDialog(null, ex);
////
////		}		
////	}
//
//}
