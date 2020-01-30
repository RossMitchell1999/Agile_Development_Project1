<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<%@page import="MediChecker.Database" %>
<%@page import="MediChecker.Query" %>

<!-- 
Setup: Eclipse EE, Apache Tomcat v9, Java 11, my-sql-connector-java-8.0.19.jar
Download Tomcat v9, add it to the Environment Runtime
Add Java Build Path -> Classpath -> Add Library -> Server Runtime -> Add v9
Add Java Build Path -> Classpath -> Add External JAR -> Pick my-sql-connector-java-8.0.19.jar
Put Database and Query in a MediChecker package
pom.xml should be configured already

-->

<!DOCTYPE html>
	<html>
		<head>
			<title>Data table</title>
		</head>
		<body>

			<h1>Search results</h1>
			<table border="1">

			<tr>
				<td>Definition</td>
				<td>Provider ID</td>
				<td>Provider Name</td>
				<td>Provider Address</td>
				<td>Provider City</td>
				<td>Provider State</td>
				<td>Provider Postcode</td>
				<td>Hospital Referral</td>
				<td>Average Covered Charges</td>
				<td>Average Total Payments</td>
				<td>Average Medicare Payments</td>
			</tr>

	<%
	
	/*
	* This function needs to be optimized and refactored
	* User input will be integrated from the form on the front end
	*/
	
	String id = request.getParameter("search");		// User selects this with radio buttons on the front-end and is either "code" or "procedure"
	String input = request.getParameter("input");	// This input will either be a DRG code or procedure/condition input from the user form 
	
	String inputMinPrice = request.getParameter("minprice"); // Gets minimum price from the user input
	 	if (inputMinPrice.isEmpty()) { // If no min price is selected the default value is set
		 	inputMinPrice = "0";
	 	}
	 	
	String inputMaxPrice = request.getParameter("maxprice"); // Gets maximum price from the user input
	 	if (inputMaxPrice.isEmpty()) { // If no max price is selected the default value is set
		 	inputMaxPrice = "99999999";
	 	}
	
	List<Query> output = null;
	Database db = new Database();
	
	if (id.equals("code")) { // If the radio button selected is search by code
		
		output = db.executeDBQuery("SELECT * FROM medichecker WHERE Definition LIKE '" + input + "%' AND AverageTotalPayments > " + inputMinPrice + " AND AverageTotalPayments < " + inputMaxPrice + " ORDER BY AverageTotalPayments ASC;");
	}
	
	if (id.equals("procedure")) { // If the radio button selected is search by procedure/condition
		
		output = db.executeDBQuery("SELECT * FROM medichecker WHERE Definition LIKE '%" + input + "%' AND AverageTotalPayments > " + inputMinPrice + " AND AverageTotalPayments < " + inputMaxPrice + " ORDER BY AverageTotalPayments ASC;");
	}
	
  	for (Query obj : output)
  		{
   		 %>
    		<tr>
    			<td><%=obj.getDefinition()%></td>
    			<td><%=obj.getProviderID()%></td>
    			<td><%=obj.getProviderName()%></td>
    			<td><%=obj.getProviderAddress()%></td>
    			<td><%=obj.getProviderCity()%></td>
    			<td><%=obj.getProviderState()%></td>
    			<td><%=obj.getProviderZip()%></td>
    			<td><%=obj.getHospitalReferral()%></td>
    			<td><%=obj.getAvgCoveredCharges()%></td>
    			<td><%=obj.getAvgTotalPayments()%></td>
    			<td><%=obj.getAvgMedicarePayments()%></td>
    		</tr>

   		<% 
  		}
		%>
		</table>
	</body>
</html>
