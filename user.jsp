<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Select One of The Following Options ****</h1>

	<BR><BR>
	<form method=get>
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<label>Record A Visit of A POI&nbsp;</label> 
	<input type="submit" name="s1" value="Record" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Declare Your Favorite POI&nbsp;</label> 
	<input type="submit" name="s2" value="Declare" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Add A Feedback For A POI&nbsp;</label> 		
	<input type="submit" name="s3" value="Add" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Rate Another User's Feedback&nbsp;</label> 		
	<input type="submit" name="s4" value="Rate" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Record Another User As Trusted Or Not&nbsp;</label> 		
	<input type="submit" name="s5" value="Record" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Browse POIs&nbsp;</label> 		
	<input type="submit" name="s6" value="Browse" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Read Useful Feedbacks&nbsp;</label> 		
	<input type="submit" name="s7" value="Read" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>See Visiting Suggestions For You&nbsp;</label> 		
	<input type="submit" name="s8" value="View" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>See Two Degree Of Seperation&nbsp;</label> 		
	<input type="submit" name="s9" value="View" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>See Statistics About POIs&nbsp;</label> 		
	<input type="submit" name="s10" value="View" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	</form>	
<%
	String login = request.getParameter("login");;

	if(request.getParameter("s1") != null)
		response.sendRedirect("addVisit.jsp?login="+login);
	if(request.getParameter("s2") != null)
		response.sendRedirect("declareFav.jsp?login="+login);
	if(request.getParameter("s3") != null)
		response.sendRedirect("provFB.jsp?login="+login);
	if(request.getParameter("s4") != null)
		response.sendRedirect("rateFB.jsp?login="+login);
	if(request.getParameter("s5") != null)
		response.sendRedirect("trustUser.jsp?login="+login);
	if(request.getParameter("s6") != null)
		response.sendRedirect("browsePoi.jsp?login="+login);
	if(request.getParameter("s7") != null)
		response.sendRedirect("usefulFB.jsp?login="+login);
	if(request.getParameter("s8") != null)
		response.sendRedirect("sugVis.jsp?login="+login);
	if(request.getParameter("s9") != null)
		response.sendRedirect("checkDeg.jsp?login="+login);
	if(request.getParameter("s10") != null)
		response.sendRedirect("stats.jsp?login="+login);
%>
	<BR><BR>
	<input type = "submit" value="Go Back To Home Page" onclick="javascript:window.location='homepage.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type = "submit" value="Go Back To Previous Page" onclick="javascript:window.location='login.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
</body>
</html>

