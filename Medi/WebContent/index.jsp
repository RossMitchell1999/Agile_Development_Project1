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
<title>Insert title here</title>
</head>
<body>

<%
	Database test = new Database();
	List<Query> output = test.executeDBQuery("select * from MediChecker");
  for (Query obj : output)
  {
    out.print( obj.getDefinition());
  }

%>
</body>
</html>
