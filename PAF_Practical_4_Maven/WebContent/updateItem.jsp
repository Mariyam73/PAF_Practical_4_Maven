<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import = "com.PAF.Item" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="views/bootstrap.min.css">
</head>
<body>


<!-- retrieving the passed parameters to update form -->
<%
	String itemNo = request.getParameter("itemID");
	String itemCode = request.getParameter("itemCode");
	String itemName =  request.getParameter("itemName");
	String itemPrice = request.getParameter("itemPrice");
	String itemDesc = request.getParameter("itemDesc");	
%>

<div class="container">
	<div class="row">
		<div class="col">

		<%-- Update Form --%>
		<form method="post" action="UpdateProcess.jsp">
			<input name="itemNo" type="text" value="<%=itemNo%>" hidden><br>
			Item code: <input name="itemCode" type="text" value="<%=itemCode%>" readonly class="form-control"><br>
			Item name: <input name="itemName" type="text" value="<%=itemName%>" class="form-control"><br>
			Item price: <input name="itemPrice" type="text" value="<%=itemPrice%>" class="form-control"><br>
			Item description: <input name="itemDesc" type="text" value="<%=itemDesc%>" class="form-control"><br>
			<input name="btnSubmit" type="submit" value="Update" class="btn btn-primary">
		</form>
		
		
		<br><br>
		<%
			Item itemObj = new Item();
			out.print(itemObj.readItems());
		%>
		</div>
		</div>
		</div>

</body>
</html>