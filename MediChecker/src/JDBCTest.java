import java.sql.*;

public class JDBCTest {

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		   try{
		      Class.forName("com.mysql.cj.jdbc.Driver");

		      System.out.println("Connecting to a selected database...");
		      String url = "jdbc:mysql://agileserver.mysql.database.azure.com/medicalprovidercharges?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		      String user = "AgileTeam9@agileserver";
		      String pass = "Password1234";
		      
		      conn = DriverManager.getConnection(url, user, pass); 
		      if (conn != null)
		      {
		    	  System.out.println("Connected");
		      }
		      
		      System.out.println("Connected database successfully...");
		      
		      
		      Statement statement = conn.createStatement();
			  ResultSet resultSet = statement.executeQuery("select * from medichecker");
			  int counter = 0;
			  while (resultSet.next())
			  {
				  counter ++;
//				  String string = resultSet.getString("Definition");
//				  System.out.println(string);
			  }
			  System.out.println(counter);
		   }

		   catch(Exception e)
		   {
		      e.printStackTrace();
		   }
		   finally
		   {
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		   
		   
		   
		   try
		   {
//			   Statement statement = conn.createStatement();
//			   ResultSet resultSet = statement.executeQuery("select * from medichecker");
//			   System.out.println("<table border=1 width=50% height=50%>");
			   
		   }
		   catch (Exception e)
		   {
			   System.out.println("error");
		   }
		}
	}
