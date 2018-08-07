<%@ page language="java" import="cs5530.*" %>
<html>
<head>
<script LANGUAGE="javascript">
</script> 
</head>
<body style="background-color: #e6e6e6;">
<h1 style="color:purple; font-family: chalkduster; font-size:150%;">**** Updating POI (Scroll Down To Bottom of Page To Go To Different Pages ****</h1>
</body>
<%
	Connector connector = new Connector();
	POI poi = new POI();

	String poiID = request.getParameter("poiID");
	session.setAttribute( "pid", poiID);
 	out.println("<BR>");
	out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");	

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

	if(request.getParameter("u1") != null)
	{	
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "1", poiName, connector.stmt);

		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:150%; text-indent: 1em;">*** After The Update ***</label>
<%	
		
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u2") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "2", url, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u3") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "3", phn_num, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u4") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "4", year_estab, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u5") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "5", open_hr, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u6") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "6", est_price_per_person, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u7") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "7", street, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u8") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "8", city, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u9") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "9", state, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u10") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "10", zip, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u11") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "11", category, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
	else if(request.getParameter("u12") != null)
	{
		poiID = request.getParameter("pid");
		poi.updatesAField(poiID, "12", allkeywords, connector.stmt);
		out.println("<BR>");
%>
		<label style="color:purple; font-family: chalkduster; font-size:100%;">*** After The Update ***</label>
<%			
		out.println("<pre>"+poi.displaySpecificPOI(poiID, connector.stmt)+"</pre>");
	}
%>
	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;"> IMPORTANT! You can ONLY update one field at a time, please click on below 'Continue' button FIRST every time before proceeding. If you forget to do so, you will need to go back to previous page to start over!!! ('Go Back' Button Is On The Bottom of This Page)</label>
	<BR><BR>
	<input type = "submit" name="continue" value="Continue" onclick="javascript:window.location='update.jsp?poiID=<%=poiID%>';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>

	<BR><BR>
	<label style="color:purple; font-family: chalkduster; font-size:100%;">Please fill in any field that you want to update, then click on the "Update" next to it. </label>
	<BR><BR>
	<form method=get>
	<label>POI Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="poiName">
	<input type="hidden" name="pid" value="<%=session.getAttribute("pid")%>">
	<input type="submit" name="u1" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>URL:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="url" />
	<input type="submit" name="u2" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Phone Number:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="phn_num" >
	<input type="submit" name="u3" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Year Estab.(1901-2155)&nbsp;</label> 
	<input type=text name="year_estab" />
	<input type="submit" name="u4" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>	
	<label>Open Hours:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="open_hr" />
	<input type="submit" name="u5" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Est. Price Per Person:&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="est_price_per_person" />
	<input type="submit" name="u6" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Street:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="street" >
	<input type="submit" name="u7" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>City:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="city" >
	<input type="submit" name="u8" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>State:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="state" >
	<input type="submit" name="u9" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Zip Code:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="zip" >
	<input type="submit" name="u10" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Category:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="category" >
	<input type="submit" name="u11" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<BR><BR>
	<label>Keywords:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
	<input type=text name="allkeywords" >
	<input type="submit" name="u12" value="Update" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	** use ONE space between keywords and do not enter duplicate keywords **
	</form>	
	<BR><BR>
	<input type = "submit" value="Go Back To Home Page" onclick="javascript:window.location='homepage.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
	<input type = "submit" value="Go Back To Previous Page" onclick="javascript:window.location='updatePOI.jsp';" style="padding: 0; background: white; color:purple; font-family: chalkduster; font-size: 100%;"/>
</body>
</html>