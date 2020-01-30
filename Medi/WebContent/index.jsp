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

			<h1>Search by DRG Code</h1>
			<table border="1">

			<tr>
				<td>Definition</td>
				<td>Provider ID</td>
				<td>Provider Name</td>
				<td>Provider City</td>
				<td>Provider State</td>
				<td>Provider Postcode</td>
				<td>Hospital Referral</td>
				<td>Average Covered Charges</td>
				<td>Average Total Payments</td>
				<td>Average Medicare Payments</td>
			</tr>

	<%
	int drg = 280;
	
	Database test = new Database();
	List<Query> output = test.executeDBQuery("SELECT * FROM medicalprovidercharges WHERE Definition LIKE '%" + drg + "%';");
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
