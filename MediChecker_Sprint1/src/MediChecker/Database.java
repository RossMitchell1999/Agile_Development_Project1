package MediChecker;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;

public class Database {

	public Connection connectToDB() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			//System.out.println("Connecting to a selected database...");
			String url = "jdbc:mysql://agileserver.mysql.database.azure.com/medicalprovidercharges?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "AgileTeam9@agileserver";
			String pass = "Password1234";

			conn = DriverManager.getConnection(url, user, pass);
			if (conn != null) {
				//System.out.println("Connected");
			}

			//System.out.println("Connected database successfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public List<Query> executeDBQuery(String query) {
		Connection conn = connectToDB();

		List<Query> queryOutput = new ArrayList<Query>();
		int counter = 0;
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String definition = resultSet.getString("Definition");
				String providerID = resultSet.getString("ProviderID");
				String providerName = resultSet.getString("ProviderName");
				String providerAddress = resultSet.getString("ProviderAddress");
				// String providerCity = resultSet.getString("ProviderCity");
				// String providerState = resultSet.getString("ProviderState");
				String providerZip = resultSet.getString("ProviderZipCode");
				// String hospitalReferral = resultSet.getString("HospitalReferral");
				// float avgCoveredCharges = resultSet.getFloat("AverageCoveredCharges");
				float avgTotalPayments = resultSet.getFloat("AverageTotalPayments");
				// float avgMedicarePayments = resultSet.getFloat("AverageCMedicarePayments");
				// double distance = getDistance(zip, maxDist, resultSet.getString("ProviderID"));
				// String providerName=resultSet.getString("ProviderName");
				// System.out.println(definition);
				// System.out.println(providerName);
				// counter++;
//				if (distance == 0.0)
//				{
//					continue;
//				}
				Query querytemp = new Query(definition, providerID, providerName, avgTotalPayments, providerAddress, providerZip);
				// Query querytemp = new Query(definition, providerID, providerName,
				// providerAddress, providerCity, providerState, providerZip, hospitalReferral,
				// avgCoveredCharges, avgTotalPayments, avgMedicarePayments);
				queryOutput.add(querytemp);
				counter++;
			}
			//System.out.println(counter);
//			statement.close();
//			resultSet.close();
		} catch (Exception e) {
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

	/**
	 * Method to get all distances between the ZIP code entered by the user and the
	 * ZIP codes for all the hospitals within the range specified
	 * 
	 * @param inputZip - the ZIP code entered by the user
	 * @param maxDist  - the maximum distance for the search range entered by the
	 *                 user
	 * 
	 * @return results - a list of the distances between the ZIP code entered by the
	 *         user and the ZIP codes of all hospitals within the range specified (0
	 *         -> maxDist)
	 */
	public double getDistanceValue(String inputZip, int maxDist, String provID) {
		Connection conn = connectToDB();
		// List<Double> results = new ArrayList<Double>();

		try {
			Statement statement = conn.createStatement();
			CallableStatement stmt;
			stmt = conn.prepareCall("{CALL getCoords(?)}");
			stmt.setString(1, inputZip);
			ResultSet resultSet = stmt.executeQuery();
			Double lat = 0.0;
			Double longi = 0.0;
			int counter = 0;
			double distance = 0.0;
			// GETTING LAT AND LONG OF ZIP ENTERED BY USER
			while (resultSet.next()) {
				lat = resultSet.getDouble("Latitude");
				longi = resultSet.getDouble("Longitude");
			}

			Statement statement2 = conn.createStatement();
			CallableStatement stmt2;
			stmt2 = conn.prepareCall("{CALL getLongLat(?)}");
			stmt2.setString(1, provID);
			ResultSet resultSet2 = stmt2.executeQuery();
		
			while (resultSet2.next()) {
				if (resultSet2.isLast()) {
					distance = calculateDistance(lat, longi, resultSet2.getDouble("Latitude"),resultSet2.getDouble("Longitude"));
				}
			}
			
			conn.close();
			//System.out.println("hi, I got to here :)");
			if (distance < maxDist)
			 { 
				 
				return distance;
			 }
			 else
			 {
				 return 0.0;
			 }


		} catch (Exception e) {
			e.printStackTrace();
			 return 0.0;
		}
	

	}

	/**
	 * Method to calculate the distance between the ZIP code entered by the user and
	 * the ZIP codes of the hospitals stored in the database using the longitudes
	 * and latitudes Source:
	 * https://www.geeksforgeeks.org/program-distance-two-points-earth/
	 * 
	 * @param lat1   = latitude for entered location
	 * @param longi1 = longitude for entered location
	 * @param lat2   = latitude for hospital location
	 * @param longi2 = longitude for hospital location
	 *
	 * @return the distance between the hospital and the location entered by the
	 *         user
	 * 
	 */
	public double calculateDistance(Double lat1, Double longi1, Double lat2, Double longi2) {
		lat1 = Math.toRadians(lat1);
		longi1 = Math.toRadians(longi1);
		lat2 = Math.toRadians(lat2);
		longi2 = Math.toRadians(longi2);

		double dlongi = longi2 - longi1;
		double dlat = lat2 - lat1;

		double a = Math.pow(Math.sin(dlat / 2), 2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlongi / 2), 2);

		double c = 2 * Math.asin(Math.sqrt(a));

		double r = 6371;

		return (c * r);

	}

}