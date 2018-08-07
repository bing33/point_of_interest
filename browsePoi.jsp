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
	<label>Search POIs By Specifying The Price Range&nbsp;</label> 
	<input type="submit" name="s1" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Address&nbsp;</label> 
	<input type="submit" name="s2" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Keywords&nbsp;</label> 		
	<input type="submit" name="s3" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Category&nbsp;</label> 		
	<input type="submit" name="s4" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Price And The Address&nbsp;</label> 		
	<input type="submit" name="s5" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Price, The Address And The Keywords&nbsp;</label> 		
	<input type="submit" name="s6" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Price, The Address, The Keywords, And The Category&nbsp;</label> 		
	<input type="submit" name="s7" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Price and The Keywords&nbsp;</label> 		
	<input type="submit" name="s8" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Price, The Address And The Category&nbsp;</label> 		
	<input type="submit" name="s9" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Address And The Category&nbsp;</label> 		
	<input type="submit" name="s10" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Price, The Keywords, And The Category&nbsp;</label> 		
	<input type="submit" name="s11" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Price and The Category&nbsp;</label> 		
	<input type="submit" name="s12" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Address And The Keywords&nbsp;</label> 		
	<input type="submit" name="s13" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Search POIs By Specifying The Keywords And The Category&nbsp;</label> 		
	<input type="submit" name="s14" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>earch POIs By Specifying The Address, The Keywords, And The Category&nbsp;</label> 		
	<input type="submit" name="s15" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	</form>	
<%
	String login = request.getParameter("login");
	session.setAttribute( "login", login);

	if(request.getParameter("s1") != null)
		response.sendRedirect("b1.jsp?login="+login);
	if(request.getParameter("s2") != null)
		response.sendRedirect("b2.jsp?login="+login);
	if(request.getParameter("s3") != null)
		response.sendRedirect("b3.jsp?login="+login);
	if(request.getParameter("s4") != null)
		response.sendRedirect("b4.jsp?login="+login);
	if(request.getParameter("s5") != null)
		response.sendRedirect("b5.jsp?login="+login);
	if(request.getParameter("s6") != null)
		response.sendRedirect("b6.jsp?login="+login);
	if(request.getParameter("s7") != null)
		response.sendRedirect("b7.jsp?login="+login);
	if(request.getParameter("s8") != null)
		response.sendRedirect("b8.jsp?login="+login);
	if(request.getParameter("s9") != null)
		response.sendRedirect("b9.jsp?login="+login);
	if(request.getParameter("s10") != null)
		response.sendRedirect("b10.jsp?login="+login);
	if(request.getParameter("s11") != null)
		response.sendRedirect("b11.jsp?login="+login);
	if(request.getParameter("s12") != null)
		response.sendRedirect("b12.jsp?login="+login);
	if(request.getParameter("s13") != null)
		response.sendRedirect("b13.jsp?login="+login);
	if(request.getParameter("s14") != null)
		response.sendRedirect("b14.jsp?login="+login);
	if(request.getParameter("s15") != null)
		response.sendRedirect("b15.jsp?login="+login);
	
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

