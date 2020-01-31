package MediChecker;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Database {

	
	public Connection connectToDB()
	{
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
		    	  System.out.println("hisdfhuy Connected");
		      }
		      
		      System.out.println("Connected database successfully...asdfjksadfhkjsdlafhkdjlsfhsadklfh");
		   }
		   catch(Exception e)
		   {
		      e.printStackTrace();
		   }
	return conn;
	}
	
	public List<Query> executeDBQuery(String query)
	{
		Connection conn = connectToDB();
		
		List<Query> queryOutput = new ArrayList<Query>();
		//int counter = 0;
		try
		{
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next())
			{
				String definition = resultSet.getString("Definition");
				//int providerID = resultSet.getInt("ProviderID");
				String providerName = resultSet.getString("ProviderName");
				//String providerAddress = resultSet.getString("ProviderAddress");
				//String providerCity = resultSet.getString("ProviderCity");
				//String providerState = resultSet.getString("ProviderState");
				//String providerZip = resultSet.getString("ProviderZipCode");
				//String hospitalReferral = resultSet.getString("HospitalReferral");
				//float avgCoveredCharges = resultSet.getFloat("AverageCoveredCharges");
				float avgTotalPayments = resultSet.getFloat("AverageTotalPayments");
				//float avgMedicarePayments = resultSet.getFloat("AverageCMedicarePayments");
				
				//String providerName=resultSet.getString("ProviderName");
				//System.out.println(definition);
				//System.out.println(providerName);
				//counter++;
				Query querytemp = new Query(definition, providerName, avgTotalPayments);
				//Query querytemp = new Query(definition, providerID, providerName, providerAddress, providerCity, providerState, providerZip, hospitalReferral, avgCoveredCharges, avgTotalPayments, avgMedicarePayments);
				queryOutput.add(querytemp);
			}
			//System.out.println(counter);
//			statement.close();
//			resultSet.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
//		finally {
//		      try{
//	         	if(conn!=null)
//	           		 conn.close();
//	      	}catch(SQLException se){
//	         se.printStackTrace();
//	     	 }
//		     
//		}
		return queryOutput;
	}

}