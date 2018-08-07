<%@ page language="java" import="cs5530.*" import="java.util.ArrayList" import="java.util.Arrays" import="java.util.List"%>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:130%;">**** Enter Your Feedback (Scroll Down To Bottom of Page To Go To Different Pages ****</h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();
	Feedback fb = new Feedback();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);

	String poiID = request.getParameter("poiID");
	session.setAttribute("pid", poiID);
 	out.println("<BR>");
	out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");	

	String date = request.getParameter("date");
	String score = request.getParameter("score");
	String comment = request.getParameter("comment");

	if(request.getParameter("date") == null)
	{		
%>
		<BR><BR>
		<BR><BR>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">Please fill in the following feilds: </label>
		<BR><BR>
		<form method=get>
		<label>Today's date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="date">
		<BR><BR>
		<label>Score 0-10(terrible-excellent):&nbsp;</label> 
		<input type=text name="score">
		<BR><BR>
		<label>Comment:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="comment">
		<BR><BR>
		<input type="hidden" name="pid" value="<%=session.getAttribute("pid")%>">
		<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
		<input type="submit" name="submit" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		<BR><BR>
		</form>	
<%
	}
	else
	{
		login = request.getParameter("login");
		session.setAttribute( "login", login);
		poiID = request.getParameter("poiID");

		if(!fb.feedbackExists(login, poiID, connector.stmt))
		{
			poiID = request.getParameter("pid");
			login = request.getParameter("login");
			session.setAttribute( "login", login);
			if(comment == null)
				comment = "";
			fb.addFeedback(date, poiID, login, score, comment, connector.stmt);			
			out.println("You have added a feedback successfully!");
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
		response.sendRedirect("provFB.jsp?login="+login);
	}
%>
</body>
</html>