<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:130%;">**** Read Useful Feedbacks (Scroll Down To Bottom of Page To Go To Different Pages ****</h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();
	Feedback fb = new Feedback();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);
%>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">**** Existing POI List ****"</label>
<%
	out.println("<pre>"+poi.displayPOIList(connector.stmt)+"</pre>");
	out.println("<BR>");

	String poiID = request.getParameter("poiID");
	String num = request.getParameter("num");
%>	
	<form method=get>
	<BR>
	<label>Please select the POI to read its feedbacks(enter a POI ID from above list):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="poiID">
	<BR>
	<label>Please enter the TOP Numbers of feedbacks that you would like to view(eg.10):&nbsp;</label> 
	<input type=text name="num">
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<input type="submit" name="s" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	</form>
<%
	
	login = request.getParameter("login");
	session.setAttribute( "login", login);

	if(request.getParameter("s") != null)
	{
		if(fb.displayTopFeedbacks(poiID, num, connector.stmt).equals(""))
		{
			out.println("<BR>No existing RATED feedbacks for this POI.<BR>");
		}
		else
		{
			response.sendRedirect("read.jsp?poiID=" + poiID+"&login="+login+"&num="+num);
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
		response.sendRedirect("user.jsp?login="+login);
	}
%>
</body>
</html>