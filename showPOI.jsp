<%@ page language="java" import="cs5530.*" import="java.util.ArrayList" import="java.util.Arrays" import="java.util.List"%>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:130%;">**** Your Selected POI ****</h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);

	String poiID = request.getParameter("poiID");
	session.setAttribute("pid", poiID);
 	out.println("<BR>");
	out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
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
		response.sendRedirect("browsePoi.jsp?login="+login);
	}
%>
</body>
</html>