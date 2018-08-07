<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;"> **** Updating A POI **** </h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();
%>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">**** Existing POI List ****"</label>
<%
	out.println("<pre>"+poi.displayPOIList(connector.stmt)+"</pre>");
	out.println("<BR>");

	String poiID = request.getParameter("poiID");
%>	
	<form name="updatePOI" method=get>
	<label>Please select a POI that you would like to update for (enter a POI ID from above list):&nbsp;</label> 
	<input type=text name="poiID">
	<input type="submit" name = "s" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	</form>	
<%
	if(request.getParameter("s") != null)
		response.sendRedirect("update.jsp?poiID=" + poiID);
%>
</body>
</html>

<input type = "submit" value="Go Back To Home Page" onclick="javascript:window.location='homepage.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
<input type = "submit" value="Go Back To Previous Page" onclick="javascript:window.location='admin.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>