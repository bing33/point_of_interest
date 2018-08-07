<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">


</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Adding A POI ****</h1>
</body>
<%
	String poiName = request.getParameter("poiName");
	String url = request.getParameter("url");
	String phn_num = request.getParameter("phn_num");
	String year_estab = request.getParameter("year_estab");
	String open_hr = request.getParameter("open_hr");
	String est_price_per_person = request. getParameter("est_price_per_person");
	String street = request.getParameter("street");
	String city = request.getParameter("city");
	String state = request.getParameter("state");
	String zip = request.getParameter("zip");
	String category = request.getParameter("category");
	String allkeywords = request.getParameter("allkeywords");
	if(poiName == null)
	{
%>	
		<BR><BR>
		<form method=get>
		<label>POI Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="poiName" action="return check_all_fields(this)">
		<BR><BR>
		<label>URL:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="url" />
		<BR><BR>
		<label>Phone Number:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="phn_num" >
		<BR><BR>
		<label>Year Estab.(1901-2155)&nbsp;</label> 
		<input type=text name="year_estab" />
		<BR><BR>	
		<label>Open Hours:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="open_hr" />
		<BR><BR>
		<label>Est. Price Per Person:&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="est_price_per_person" />
		<BR><BR>
		<label>Street:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="street" >
		<BR><BR>
		<label>City:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="city" >
		<BR><BR>
		<label>State:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="state" >
		<BR><BR>
		<label>Zip Code:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="zip" >
		<BR><BR>
		<label>Category:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="category" >
		<BR><BR>
		<label>Keywords:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
		<input type=text name="allkeywords" >
		** use ONE space between keywords and do not enter duplicate keywords **
		<BR><BR>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">Please fill in all the above fields then click: </label>
		<input type="submit" value="Submit" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
		</form>		
<%
	}
	else
	{
		Connector connector = new Connector();
		POI poi = new POI();

		poi.addAddress(street, city, state, zip, connector.stmt);
		poi.addPOI(poiName, url, phn_num, year_estab, open_hr, est_price_per_person, category, connector.stmt);	
		poi.addKeywords(allkeywords, connector.stmt);
		out.println("<BR>");
	 	out.println("You have added a POI successfully.");
	 	out.println("<BR>");
	}
%>
	<BR>
	<input type = "submit" value="Go Back To Home Page" onclick="javascript:window.location='homepage.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type = "submit" value="Go Back To Previous Page" onclick="javascript:window.location='admin.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
</body>
</html>