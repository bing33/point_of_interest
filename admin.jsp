<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%; ">**** Select One of The Following Options ****</h1>

	<BR><BR>
	<BR><BR>
	<form method=get>
	<label>Add A Point of Interest&nbsp;</label> 
	<input type="submit" name="add" value="Add" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Update A Point of Interest&nbsp;</label> 
	<input type="submit" name="update" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>View Users (Useful Data To Help With Awarding Users)&nbsp;</label> 		
	<input type="submit" name="view" value="View" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	</form>	
<%
	if(request.getParameter("add") != null)
		response.sendRedirect("addPOI.jsp");
	if(request.getParameter("update") != null)
		response.sendRedirect("updatePOI.jsp");
	if(request.getParameter("view") != null)
		response.sendRedirect("viewUsers.jsp");
%>
	<BR><BR>
	<input type = "submit" value="Go Back To Home Page" onclick="javascript:window.location='homepage.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
</body>
</html>