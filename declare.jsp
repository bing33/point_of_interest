<%@ page language="java" import="cs5530.*" import="java.util.ArrayList" import="java.util.Arrays" import="java.util.List"%>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Your Selected POI (Scroll Down To Bottom of Page To Go To Different Pages ****</h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();
	Favorite favor = new Favorite();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);

	String poiID = request.getParameter("poiID");
	session.setAttribute("pid", poiID);
 	out.println("<BR>");
	out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");	

	String date = request.getParameter("date");

	if(request.getParameter("date") == null)
	{		
%>
		<BR><BR>
		<BR><BR>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">Please fill in the following feilds: </label>
		<BR><BR>
		<form method=get>
		<label>Today's date:&nbsp;</label> 
		<input type=text name="date">
		<input type="hidden" name="pid" value="<%=session.getAttribute("pid")%>">
		<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
		<input type="submit" name="submit" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		<BR><BR>
		</form>	
<%
	}
	else
	{
		if(!favor.favoriteExists(login, poiID, connector.stmt))
		{
			poiID = request.getParameter("pid");
			login = request.getParameter("login");
			session.setAttribute( "login", login);
			favor.addFavorite(poiID, login, date, connector.stmt);		
			out.println("You have added a favorite successfully!");
		}
	}	
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
		response.sendRedirect("declareFav.jsp?login="+login);
	}
%>
</body>
</html>