<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page import = "com.PAF.Item" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update</title>
</head>
<body>

<!-- Calling Update -->
<%
	if (request.getParameter("itemCode") != null)
	{
		Item itemObj = new Item();
				
		String stsMsg = itemObj.updateItem(request.getParameter("itemCode"),
		request.getParameter("itemName"),
		request.getParameter("itemPrice"),
		request.getParameter("itemDesc"));
		session.setAttribute("statusMsg", stsMsg); 
		session.setAttribute("statusMsg", stsMsg);
	}

%>

		<%
			out.print(session.getAttribute("statusMsg"));
		%>
		
		<!-- Resetting the session to null -->
		<%  session.setAttribute("statusMsg", "");
			out.print(session.getAttribute("statusMsg"));
		%>
		
		<br><br>
		<%
			Item itemObj = new Item();
			out.print(itemObj.readItems());
		%>
		
		<br><br>
		<form method="post" action="items.jsp">
			<input name="btnSubmit" type="submit" value="Back to Home">
		</form>
		

</body>
</html>