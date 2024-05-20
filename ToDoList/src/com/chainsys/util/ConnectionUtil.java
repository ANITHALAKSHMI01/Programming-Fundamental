package com.chainsys.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionUtil
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Connection connection=ConnectionUtil.getConnection();
		System.out.println(connection);
		connection.close();
	}
	public static Connection getConnection()throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db_1","root","Ranitha01@");
		return connection;
	}
}
