package com.cinemamanage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConncection {
	static Connection con=null;
	public static Connection getConnection() {
		//Connection con = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema_management","root","2004");
			System.out.println("Connecting...");
		} catch (ClassNotFoundException e) {

		System.out.println("Driver class not found");

		} catch (SQLException e) {

		System.out.println("Database Connectin not found "+e);

		}
		return con;
	}

}
