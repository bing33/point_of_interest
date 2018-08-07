<%@ page language="java" import="cs5530.*" import="java.util.ArrayList" import="java.util.Arrays" import="java.util.List"%>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Your Selected POI (Scroll Down To Bottom of Page To Go To Different Pages ****</h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();
	VisitPOI visit = new VisitPOI();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);

	String poiID = request.getParameter("poiID");
	session.setAttribute("pid", poiID);
 	out.println("<BR>");
	out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");	

	String date = request.getParameter("date");
	session.setAttribute("date", date);
	String cost = request.getParameter("cost");
	session.setAttribute("cost", cost);
	String num_of_people = request.getParameter("num_of_people");	
	session.setAttribute("num_of_people", num_of_people);	

	String cart = request.getParameter("cart");
	session.setAttribute("cart", cart);

	String oneRecord = "";	
	
	if(request.getParameter("date") == null)
	{	
		out.println("<BR>");
		if(cart == null)
			out.println("In Cart: Nothing");
		else
			out.println("In Cart: "+cart);
		out.println("<BR>");
%>
		<BR><BR>
		<BR><BR>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">Please fill in the following feilds: </label>
		<BR><BR>
		<form method=get>
		<input type="hidden" name="pid" value="<%=session.getAttribute("pid")%>">
		<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
		<input type="hidden" name="cart" value="<%=session.getAttribute("cart")%>">
		<label>Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="date">
		<BR><BR>
		<label>Cost:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="cost" />
		<BR><BR>
		<label>Number of People:&nbsp;</label> 
		<input type=text name="num_of_people" >
		<input type="submit" name="submit" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		<BR><BR>
		</form>	
<%
	}
	else
	{
		poiID = request.getParameter("pid");
		session.setAttribute("pid", poiID);
		login = request.getParameter("login");
		session.setAttribute("login", login);
		date = request.getParameter("date");
		session.setAttribute("date", date);
		cost = request.getParameter("cost");
		session.setAttribute("cost", cost);
		num_of_people = request.getParameter("num_of_people");
		session.setAttribute("num_of_people", num_of_people);
		cart = request.getParameter("cart");
		session.setAttribute("cart", cart);
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">Your Entered Data: </label>
<%
		oneRecord = "<pre>"+"POI: "+poi.displayPOIName(poiID, connector.stmt)+"<BR>Visit Date: "+date+"<BR>Cost: $"
					+cost+"<BR>Number of People: "+num_of_people+"</pre>";

		out.println(oneRecord);   
	}
%>
	<BR><BR>
	<form method=get>	
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<input type="hidden" name="pid" value="<%=session.getAttribute("pid")%>">
	<input type="hidden" name="date" value="<%=session.getAttribute("date")%>">
	<input type="hidden" name="cost" value="<%=session.getAttribute("cost")%>">
	<input type="hidden" name="num_of_people" value="<%=session.getAttribute("num_of_people")%>">
	<input type="hidden" name="cart" value="<%=session.getAttribute("cart")%>">
	<input type = "submit" name = "s1" value="Save For Later" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type = "submit" name = "s2" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
    </form>	
<%
	login = request.getParameter("login");
	session.setAttribute("login", login);

	cart = request.getParameter("cart");
	session.setAttribute("cart", cart);

	poiID = request.getParameter("pid");
	date = request.getParameter("date");
	cost = request.getParameter("cost");
	num_of_people = request.getParameter("num_of_people");

	if(request.getParameter("s1") != null)
	{		
		cart = cart + "<BR>" + login + " " + poiID + " " + date + " " + cost + " " + num_of_people;
		session.setAttribute("cart",  cart);
		out.println("In Cart: "+cart);			
	}
	if(request.getParameter("s2") != null)
	{
		visit.addVisit(login, poiID, date, cost, num_of_people, connector.stmt);
		out.println("The visit: \""+login+"  "+poiID+"  "+date+"  "+cost+"  "+num_of_people+"\" has been added successfully!<BR>");
	}
%>	
	<BR><BR>
	<form action="add.jsp" method=get>	
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<input type="hidden" name="cart" value="<%=session.getAttribute("cart")%>">
	<input type="hidden" name="pid" value="<%=session.getAttribute("pid")%>">
	<input type="hidden" name="date" value="<%=session.getAttribute("date")%>">
	<input type="hidden" name="cost" value="<%=session.getAttribute("cost")%>">
	<input type="hidden" name="num_of_people" value="<%=session.getAttribute("num_of_people")%>">
	<input type = "submit" name = "hp" value="Go Back To Home Page" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type = "submit" name = "gb" value="Go Back To Previous Page" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	</form>
<%
	login = request.getParameter("login");
	cart = request.getParameter("cart");

	if(request.getParameter("hp") != null)
		response.sendRedirect("index.jsp");
	if(request.getParameter("gb") != null)
	{
		response.sendRedirect("addVisit.jsp?login="+login+"&cart="+cart);
	}
%>
</body>
</html>