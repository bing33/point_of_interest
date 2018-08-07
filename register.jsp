<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Registering ... **** </h1>
<%
	Connector connector = new Connector();
	User user = new User();

	String login = request.getParameter("login");
	String pswd = request.getParameter("pswd");
	String fname = request.getParameter("fname");
	String lname = request.getParameter("lname");
	String type = request.getParameter("type");
	String phn_num = request.getParameter("phn_num");
	String street = request.getParameter("street");
	String city = request.getParameter("city");
	String state = request.getParameter("state");
	String zip = request.getParameter("zip");
	if(login == null)
	{
%>	
		<BR><BR>
		<form method=get>
		<label>Login:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="login">
		<input type="submit" name = "s1" value="Check Availability" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		<BR><BR>
		<label>Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="pswd" />
		<BR><BR>
		<label>First Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="fname" />
		<BR><BR>	
		<label>Last Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="lname" />
		<BR><BR>
		<label>Type:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="type" />
		&nbsp;&nbsp; ** "true" for user, "false" for admin **
		<BR><BR>
		<label>Phone Number:&nbsp;</label> 
		<input type=text name="phn_num" >
		<BR><BR>
		<label>Street:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="street" >
		<BR><BR>
		<label>City:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="city" >
		<BR><BR>
		<label>State:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="state" >
		<BR><BR>
		<label>Zip Code:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="zip" >
		<BR><BR>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">Please fill in all the above fields then click: </label>
		<input type="submit" name = "s" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		</form>		
<%
	}
	else
	{
		if(request.getParameter("s1") != null)
		{
			if(user.alreadyExists(login, connector.stmt))
			{
				out.println("<BR>");
				out.println("The user name already exists, please try a different one.");
				out.println("<BR>");
			}
			else
			{
				out.println("<BR>");
		 		out.println("It is good to use, please go back to previous page and start registering.");
		 		out.println("<BR>");
			}
		}
%>
<BR><BR>
<input type = "submit" value="Go Back To Previous Page" onclick="javascript:window.location='register.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
<%
		if(request.getParameter("s") != null)
		{
			if(user.alreadyExists(login, connector.stmt))
			{
				out.println("<BR>");
				out.println("The user name already exists, please try a different one.");
				out.println("<BR>");
			}
			else
			{
				//adds the address
				user.addAddress(street, city, state, zip, connector.stmt);
				//adds the user
				user.addUser(login, pswd, fname, lname, type, phn_num, connector.stmt);
				
		 		out.println("<BR>");
		 		out.println("You have registered successfully! Please log in from the homepage.");
		 		out.println("<BR>");
	 		}
 		}
	}
%>
	<BR><BR>
	<input type = "submit" value="Go Back To Home Page" onclick="javascript:window.location='homepage.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
</body>
</html>
