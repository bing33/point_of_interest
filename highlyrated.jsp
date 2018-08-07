<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Highly Rated POIs ****</h1>

	<BR><BR>
<%
	Connector connector = new Connector();
	POI poi = new POI();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);

	String num = request.getParameter("num");
%>	
	<form method=get>
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<label>Please enter the numbers of highly rated POIs that you would like to view:&nbsp;</label> 
	<input type=text name="num">
	<input type="submit" name = "s" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	</form>	
<%
	login = request.getParameter("login");
	session.setAttribute( "login", login);
	out.println("<pre>"+poi.listHi(num,connector.stmt)+"</pre>");
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
		response.sendRedirect("stats.jsp?login="+login);
	}
%>
</body>
</html>