<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">


</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:100%;">**** Users (Scroll Down To Bottom of Page To Go To Different Pages ****</h1>
</body>
<%
	Connector connector = new Connector();
	User user = new User();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);

	out.println("<pre>"+user.displayUsersList(connector.stmt)+"</pre>");
	out.println("<BR>");

	String userToRate = request.getParameter("user");
	String trust = request.getParameter("trust");
	if(request.getParameter("user")==null)
	{
%>	
		<form method=get>
		<label>Please select the user to rate (select a login from above):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="user">
		<BR><BR>
		<label>Please enter "true" for "trusted" and "false" for "not-trusted":&nbsp;</label> 
		<input type=text name="trust">
		<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
		<input type="submit" name="s" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		</form>
<%
	}
	else
	{
		login = request.getParameter("login");
		
		if(request.getParameter("s") != null)
		{
			if(login.equals(userToRate))
				out.println("<BR>You cannot rate yourself.");
			else
			{
				String existTrust = user.alreadyRatedUser(login, userToRate, connector.stmt);
				if(existTrust.equals(""))
				{
					out.println(user.updateTrusts(login, userToRate, trust, connector.stmt));
					out.println("<BR>You have rated the user successfully!");
				}
				else
				{
					out.println("You have already rated this user.");
					out.println(existTrust);
				}
			}
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