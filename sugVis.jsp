<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** POI Suggestions (Scroll Down To Bottom of Page To Go To Different Pages ****</h1>

	<BR><BR>
<%
	Connector connector = new Connector();
	VisitPOI v = new VisitPOI();
	String login = request.getParameter("login");
	session.setAttribute( "login", login);
%>
	<label style="color:purple; font-family: chalkduster; font-size:100%;"> *** Your Most Recent Visit *** </label> 
	<BR><BR>
<%
	out.println("<pre>"+v.listMostRecentVisit(login, connector.stmt)+"</pre>");
%>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;"> *** Based On This Visit, Below Are Some Visit Suggestions *** </label>
	<BR><BR>
<%
	out.println("<pre>"+v.listVisitSuggestions(login,connector.stmt)+"</pre>");
	POI poi = new POI();
	String poiID = request.getParameter("poiID");
%>
	<BR><BR>
	<form method=get>
	<label>Please Enter A POI ID That You Would Like To View The Details For:&nbsp;</label> 
	<input type=text name="poiID">
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<input type="submit" name = "s" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>

<%
	if(request.getParameter("s") != null)
%>
		<BR><BR>
		<label style="color:purple; font-family: chalkduster; font-size:100%;"> **** Your Selected POI **** </label> 
		<BR><BR>
<%
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
		response.sendRedirect("user.jsp?login="+login);
	}
%>
</body>
</html>