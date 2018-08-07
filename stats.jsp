<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">*** Please Select One of The Following Options ***</h1>
	<BR><BR>
	<form method=get>
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<label>Most popular POIs&nbsp;</label> 
	<input type="submit" name="s1" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Most expensive POIs&nbsp;</label> 
	<input type="submit" name="s2" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Highly Rated POIs&nbsp;</label> 		
	<input type="submit" name="s3" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	</form>	

<%
	String login = request.getParameter("login");
	session.setAttribute( "login", login);
	
	if(request.getParameter("s1") != null)
		response.sendRedirect("popular.jsp?login="+login);
	if(request.getParameter("s2") != null)
		response.sendRedirect("expensive.jsp?login="+login);
	if(request.getParameter("s3") != null)
		response.sendRedirect("highlyrated.jsp?login="+login);
%>
	<BR><BR>
	<form method=get>	
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<input type = "submit" name = "hp" value="Go Back To Home Page" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type = "submit" name = "gb" value="Go Back To Previous Page" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	</form>
<%
	login = request.getParameter("login");
	if(request.getParameter("hp") != null)
		response.sendRedirect("index.jsp");
	if(request.getParameter("gb") != null)
	{
		response.sendRedirect("user.jsp?login="+login);
	}
%>
</body>
</html>