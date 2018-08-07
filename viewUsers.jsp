<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">


</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Viewing Users ****</h1>

<%
	String num1 = request.getParameter("num1");
	String num2 = request.getParameter("num2");

	if(num1 == null && num2 == null)
	{
%>
		<form method=get>
		<BR><BR>
		<label>See Most Trusted Users.&nbsp;</label> 
		<label>Please enter the numbers of most useful users that you would like to view:&nbsp;</label> 
		<input type=text name="num1">
		<input type="submit" name="s1" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		<BR><BR>
		<label>See Most Useful Users.&nbsp;</label> 
		<label>Please enter the numbers of most useful users that you would like to view:&nbsp;&nbsp;</label> 
		<input type=text name="num2">
		<input type="submit" name="s2" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		<BR><BR>
		</form>	
<%
	}
	else
	{
		Connector connector = new Connector();
		User user = new User();
		if(request.getParameter("s1") != null)
		{
			out.println("<BR>");
			out.println("*** Most Trusted Users ***");
			out.println("<pre>"+user.listUserTrustful(num1,connector.stmt)+"</pre>");
		}
		if(request.getParameter("s2") != null)
		{
			out.println("<BR>");
			out.println("*** Most Useful Users ***");
			out.println("<pre>"+user.listUserHelpful(num2,connector.stmt)+"</pre>");
		}

	}
%>
	<BR><BR>
	<input type = "submit" value="Go Back To Home Page" onclick="javascript:window.location='homepage.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type = "submit" value="Go Back To Previous Page" onclick="javascript:window.location='admin.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
</body>
</html>