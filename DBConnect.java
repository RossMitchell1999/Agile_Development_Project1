package database_connect_temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try 
		{
		String host = ""; //TODO: hostname
		String username = "AgileTeam9@agileserver";
		String password = "Password1234";
		
			Connection connection = DriverManager.getConnection(host, username, password);
		}
		catch (SQLException err)
		{
			System.out.println(err.getMessage());
		}
		
	}

}
