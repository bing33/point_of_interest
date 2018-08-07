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
	Rating rate = new Rating();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);

	String poiID = request.getParameter("poiID");
	session.setAttribute("pid", poiID);
 	out.println("<BR>");
	out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");

	String fid = request.getParameter("fid");
	session.setAttribute("fid", fid);
	String rating = request.getParameter("rate");	

	if(request.getParameter("rate") == null)
	{		
		out.print(fb.displayTitle(poiID, connector.stmt)+"<BR>");
		out.println("<pre>"+fb.displayFeedbacks(poiID, connector.stmt)+"</pre>");	

%>
		<BR><BR>
		<form method=get>
		<label>Please select the Feedback to rate(enter a Feedback ID from above list):&nbsp;</label> 
		<input type=text name="fid">
		<BR><BR>
		<label>Enter a rating among 0(useless), 1(useful), or 2(very useful):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="rate">
		<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
		<input type="submit" name="submit" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		<BR><BR>
		</form>	
<%
	
	}
	else
	{	
		login = request.getParameter("login");

		String existRate = rate.ratingExists(login, fid, connector.stmt);

		if(rate.rateOwnFB(login, fid, connector.stmt))
		{
			out.println("<BR>You cannot rate your own feedbacks.");
		}
		else if(!existRate.equals(""))
		{
			out.println("<BR>You have already rated this feedback.");
			out.println(existRate);
		}	
		else
		{		
			session.setAttribute( "login", login);

			rate.addRating(login, fid, rating, connector.stmt);		
			out.println("<BR>You have added a rating successfully!");
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
		response.sendRedirect("rateFB.jsp?login="+login);
	}
%>
</body>
</html>