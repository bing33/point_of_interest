<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:130%;">**** Adding A Visit to A POI (Scroll Down To Bottom of Page To Go To Different Pages ****</h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();
	VisitPOI visit = new VisitPOI();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);
%>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">**** Existing POI List ****"</label>
<%
	out.println("<pre>"+poi.displayPOIList(connector.stmt)+"</pre>");
	out.println("<BR>");

	String poiID = request.getParameter("poiID");
	String cart = request.getParameter("cart");
	session.setAttribute("cart", cart);
	out.println("<BR>");
	if(cart == null || request.getParameter("submitVisits") != null)
		out.println("In Cart: Nothing");
	else
		out.println("In Cart: "+cart);
%>	
	<BR><BR>
	<form method=get>
	<input type="submit" name="submitVisits" value="Submit Your Visits" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
<%
	if(request.getParameter("submitVisits") != null)
	{
		String[] records = cart.split("<BR>");
		for(int i=1; i<records.length; i++)
		{
			String s = records[i];
			String[] record = s.split(" ");
			String ln = record[0];
			String pid = record[1];
			String date = record[2];
			String cost = record[3];
			String num_of_people = record[4];
			visit.addVisit(ln, pid, date, cost, num_of_people, connector.stmt);
			out.println("The visit: \""+ln+"  "+pid+"  "+date+"  "+cost+"  "+num_of_people+"\" has been added successfully!<BR>");
		}
		cart = "";
	}
%>
	<BR><BR>
	<input type="hidden" name="cart" value="<%=session.getAttribute("cart")%>">
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<label>Please select a POI that you would like to add a visit for (enter a POI ID from above list):&nbsp;</label> 
	<input type=text name="poiID">
	<input type="submit" name="s" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	</form>
<%
	login = request.getParameter("login");
	cart = request.getParameter("cart");

	if(request.getParameter("s") != null)
		response.sendRedirect("add.jsp?poiID=" + poiID+"&login="+login+"&cart="+cart);
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