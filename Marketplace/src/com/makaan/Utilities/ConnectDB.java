package com.makaan.Utilities;

import java.sql.*;
import java.util.*;

public class ConnectDB {

	// JDBC driver name and database URL
	public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static String DB_URL = "jdbc:mysql://prop-mp-slave.clnbagmrulmr.ap-southeast-1.rds.amazonaws.com";

	// Database credentials
	public static String USER = "s3l3ct_us3r";
	public static String PASS = "6E6q:vWMT^F";
	public static Connection conn = null;
	public static Statement stmt = null;

	public  void Connect() {

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public  ResultSet Execute(String Query, String Database) throws SQLException {
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try {
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql, sql1;
			ArrayList<String> arr = new ArrayList();
			sql = Database;
			sql1 = Query;
			System.out.println("connected to proptiger");
			rs = stmt.executeQuery(sql);
			rs1 = stmt.executeQuery(sql1);
			return rs1;
			

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		/*finally{
			rs.close();
			rs1.close();
			stmt.close();
			conn.close();
		}*/
	
		return rs1;
}
}	
