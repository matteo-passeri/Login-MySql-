package Login;

public class Launcher {
	static String url = "jdbc:mysql://localhost:3306/testDb?useSSL=false";
	static String user = "root";
	static String password = "";
	static String selectQuery = "Select * from userLogin where user=? and password=?";
	static String insertQuery = "INSERT INTO `userLogin`(`fname`, `lname`, `email`, `user`, `password`) VALUES (?,?,?,?,?)";


	public static void main(String[] args) {	
		LoginFrame.init(url, user, password, selectQuery, insertQuery);
	}

}
