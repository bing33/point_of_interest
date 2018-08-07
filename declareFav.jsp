<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">


</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:130%;">**** Adding Your Favorite POI (Scroll Down To Bottom of Page To Go To Different Pages ****</h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();
	Favorite favor = new Favorite();

	//VisitPOI visit = new VisitPOI();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);
%>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">**** Existing POI List ****"</label>
<%
	out.println("<pre>"+poi.displayPOIList(connector.stmt)+"</pre>");
	out.println("<BR>");

	String poiID = request.getParameter("poiID");
%>	
	<form method=get>
	<label>Please select your favorite POI(enter a POI ID from above list):&nbsp;</label> 
	<input type=text name="poiID">
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<input type="submit" name="s" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	</form>
<%
	login = request.getParameter("login");

	if(request.getParameter("s") != null)
		response.sendRedirect("declare.jsp?poiID=" + poiID+"&login="+login);
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