package sv.edu.udb.desafio1.model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConnection {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/desafio1_dwf?useSSL=false";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	public AppConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(AppConnection.class.getName()).log(Level.SEVERE,
			null, ex);
		}
	}

	public void connect() throws SQLException{
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		if (conn != null) {
			System.out.println("Conexión exitosa a la base de datos.");
		} else {
			System.out.println("Conexión fallida.");
		}
	}
	public void close() throws SQLException{
		if(conn != null){
			conn.close();
			conn = null;
		}
	}

}
