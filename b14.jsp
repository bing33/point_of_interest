<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:120%;">**** Browse POIs By Keyword, and Category ****</h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();
	BrowsePOI bp = new BrowsePOI();

	String login = request.getParameter("login");
	session.setAttribute( "login", login);
%>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">Please Enter Keyword, and Category to Filter ("Low To High" or "High To Low"): </label>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">*** POI List ***</label>
<%
	out.println("<pre>"+poi.displayPOIList(connector.stmt)+"</pre>");
	out.println("<BR>");
%>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">*** Available Keywords List ***</label>
<%
	out.println("<pre>"+bp.disKeyword(connector.stmt)+"</pre>");
%>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">*** Available Categories List ***</label>
<%
	out.println("<pre>"+bp.disCate(connector.stmt)+"</pre>");

	String kw = request.getParameter("kw");
	String cate = request.getParameter("cate");
%>	
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">Please Enter Keyword, and Category to Filter ("Low To High" or "High To Low"): </label>
	<BR><BR>
	<form method=get>
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<label>Keyword:&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="kw">
	<BR><BR>
	<label>Category:&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="cate">
	<input type="submit" name="s1" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	</form>
<%
	login = request.getParameter("login");
	
	if(request.getParameter("s1") != null)
	{
%>
		<BR><BR>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** POI List Based On Your Selection ***</label>
<%
		out.println("<pre>"+bp.listKwCate(kw, cate, connector.stmt)+"</pre>");

	}
%>
	<form method=get>
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">*** Sort POI By Average Feedback Scores ***</label>
	<BR><BR>
	<input type="submit" name="sort1" value="Low To High" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type="submit" name="sort2" value="High To Low" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">*** Sort POI By Average Feedback Scores of Trusted Users ***</label>
	<BR><BR>
	<input type="submit" name="sort3" value="Low To High" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type="submit" name="sort4" value="High To Low" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">*** Sort POI By Cost Per Person ***</label>
	<BR><BR>
	<input type="submit" name="sort5" value="Low To High" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type="submit" name="sort6" value="High To Low" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	</form>
<%
	if(request.getParameter("sort1") != null)
	{
		out.println("<BR>*** After Sorting ***<BR>");
		out.println("<pre>"+bp.sortAvgFBL2H(connector.stmt)+"</pre>");
	}
	if(request.getParameter("sort2") != null)
	{
		out.println("<BR>*** After Sorting ***<BR>");
		out.println("<pre>"+bp.sortAvgFBH2L(connector.stmt)+"</pre>");
	}
	if(request.getParameter("sort3") != null)
	{
		out.println("<BR>*** After Sorting ***<BR>");
		out.println("<pre>"+bp.sortAvgTrustedUserFBL2H(connector.stmt)+"</pre>");
	}
	if(request.getParameter("sort4") != null)
	{
		out.println("<BR>*** After Sorting ***<BR>");
		out.println("<pre>"+bp.sortAvgTrustedUserFBH2L(connector.stmt)+"</pre>");
	}
	if(request.getParameter("sort5") != null)
	{
		out.println("<BR>*** After Sorting ***<BR>");
		out.println("<pre>"+bp.sortPriceL2H(connector.stmt)+"</pre>");
	}
	if(request.getParameter("sort6") != null)
	{
		out.println("<BR>*** After Sorting ***<BR>");
		out.println("<pre>"+bp.sortPriceH2L(connector.stmt)+"</pre>");
	}
	
	String poiID = request.getParameter("poiID");
%>
	<form method=get>
	<label>Please select the POI to view its details (enter a POI ID from above list):&nbsp;</label> 
	<input type=text name="poiID">
	<input type="hidden" name="login" value="<%=session.getAttribute("login")%>">
	<input type="submit" name="s" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	</form>
<%
	if(request.getParameter("s") != null)
	{
		out.println("<BR>");
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
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
		response.sendRedirect("browsePoi.jsp?login="+login);
	}
%>
</body>
</html>
