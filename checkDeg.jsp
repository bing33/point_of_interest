<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Checking Degrees of Separation... ****</h1>

	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">**** Users List ****</label>
<%
	Connector connector = new Connector();
	User user = new User();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);

	out.println("<pre>"+user.displayUsersList(connector.stmt)+"</pre>"); 
	out.println("<BR>");

	String user1 = request.getParameter("u1");
	String user2 = request.getParameter("u2");
	if(user1 == null)
	{
%>	
		<BR><BR>
		<form method=get>
		<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
		<label>First User's Login:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="u1"/>
		<BR><BR>
		<label>Second User's Login:&nbsp;</label> 
		<input type=text name="u2" />
		<BR><BR>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">Please fill in both fields with valid entries, scroll down to bottom of page to see the result after clicking: </label>
		<input type="submit" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		</form>	
<%
	}
	else
	{	
		login = request.getParameter("login");
		session.setAttribute( "login", login);
%>		
		<label style="color:purple; font-family: chalkduster; font-size:100%;">**** Result ****</label>
<%
		if(user.checkSep(user1, user2, connector.stmt).equals(""))
			out.println("<pre>"+"They do not have neither 1-degree nor 2-degree of separation."+"</pre>");
		else
			out.println("<pre>"+user.checkSep(user1, user2, connector.stmt)+"</pre>");
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