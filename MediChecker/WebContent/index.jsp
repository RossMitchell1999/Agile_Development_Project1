<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<form method="post" action="print.jsp">
     <input type="submit" id="btn1" name="btn1"/>
</form>

<%
	Database test = new Database();
	List <Query> output = output.executeDBQuery(select * from MediChecker);
  for (Query obj : output)
  {
    out.print(obj.getDefinition())
  }

%>
</body>
</html>
