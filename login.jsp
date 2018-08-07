<%@ page language="java" import="cs5530.*" %>

<html>
<head>


</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Logging In ... ****</h1>
<%
	String login = request.getParameter("login");
	session.setAttribute( "login", login);
	String pswd = request.getParameter("pswd");
	if(login == null)
	{
%>
		<BR><BR>
		<form method=get>
		<label>Login:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="login" action="return check_all_fields(this)">
		<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
		<BR><BR>
		<label>Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="pswd" />
		<BR><BR>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">Please fill in all the above fields then click: </label>
		<input type="submit" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		</form>	
<%
	}
	else
	{
		Connector connector = new Connector();
		User user = new User();

		if(user.validatesLoginAsAdmin(login, pswd, connector.stmt))
		{
			response.sendRedirect("admin.jsp?login="+login);
		}
		else if(user.validatesLoginAsUser(login, pswd, connector.stmt))
		{
			response.sendRedirect("user.jsp?login="+login);
		}
		else
		{
			out.println("<BR>");
			out.println("Sorry, we cannot log you in at this time. Please check the following possible reasons:");
	        out.println("<BR>");
	        out.println("1. Your login information was entered incorrectly, please retry.");
	        out.println("<BR>");
	        out.println("2. You have not registered. Please go to the registration section to register.");
	    }
	}
%>
	<BR><BR>
	<input type = "submit" value="Go Back To Home Page" onclick="javascript:window.location='homepage.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
</body>
</html>

