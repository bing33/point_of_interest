/*
 * Class: VisitPOI
 * Description: User VisitPOI contains everything that has to do with POI visits
 * 				It contains the following methods: 
 * 				1. addVisit - used to add a visit,
 * 				2. listVisitSuggestions - used to list visit suggestions.		
 * By: xrawlinson
 * Last Update: 03/19/2016
 */

package cs5530;

import java.sql.*;

public class VisitPOI
{	
	//adds a visit 
	public void addVisit(String login, String pid, String vis_date, String cost, String num_of_people, Statement stmt)
	{		
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
				
		int poiID = Integer.parseInt(pid);
		int totalcost = Integer.parseInt(cost);
		int numOfPeople = Integer.parseInt(num_of_people);
		int visitID = 0;
		
		String sql = "INSERT INTO VisEvent (cost, num_of_people) VALUES("+totalcost+","+numOfPeople+");";
		
		try
		{
			stmt.executeUpdate(sql);
			
			String sqlForVisitID = "SELECT vid FROM VisEvent ORDER BY vid DESC LIMIT 1;";
			ResultSet rs=null;
			
			try
			{
				rs = stmt.executeQuery(sqlForVisitID);
				
				while (rs.next())
				{	
					//gets the vid for the visit event that was just added
					visitID = Integer.parseInt(rs.getString("vid"));
				}
				
				String sqlForVisit = "INSERT INTO Visit VALUES('"+login+"',"+poiID+","+visitID+",'"+vis_date+"');"; 
			
				stmt.executeUpdate(sqlForVisit);									
			}
			catch(Exception e)
		 	{
		 		System.out.println("Cannot execute the query.");
		 	}
		 	finally
		 	{
		 		try
		 		{
			 		if (rs!=null && !rs.isClosed())
			 			rs.close();
		 		}
		 		catch(Exception e)
		 		{
		 			System.out.println("Cannot close resultset");
		 		}
		 	}
	 	}
		catch(SQLException se)
		{
		    System.out.println("Cannot execute the query.");
		}
	 	catch(Exception e)
	 	{
	 		 System.out.println("Issue.\n"+e);
	 	}		
	}//end of addVisit

	// added for the web
	public String listMostRecentVisit(String login, Statement stmt)
	{
		//handles apostrophe(single quote), 
				//without doing this it kept getting an error when ' exists within a word
				if(login.contains("'"))
					login = login.replace("'", "''");
				
				String sql = "SELECT p.pid, p.pname, v.vis_date "
						+ "FROM POI p, Visit v "
						+ "Where v.pid = p.pid AND v.login = '"+login+"' "
						+ "ORDER BY v.vid DESC LIMIT 1;;";
				ResultSet rs = null;
				String output="";
				
				try
				{
					rs = stmt.executeQuery(sql);
					
					while(rs.next())
					{
						//gets the most recent recorded visit
						output+="POI ID: "+rs.getString("p.pid")+"     POI NAME: "
							+rs.getString("p.pname")+"     Visit Date: "+rs.getString("v.vis_date")+"\n";
						break;
					}
					
					//if the output is blank, advise of no recorded visit yet
					if(output.equals(""))
					{
						System.out.print("You have not recorded any visits yet.");
						output = "You have not recorded any visits yet.";
					}
					else
					{
						System.out.println("\n*** Your Most Recent Visit ***\n"+output);
					}
				}
		catch(Exception e)
	 	{
	 		System.out.println("Cannot execute the query.");
	 	}
	 	finally
	 	{
	 		try
	 		{
		 		if (rs!=null && !rs.isClosed())
		 			rs.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
		
		return output;
	}

	//lists visit suggestions
	public String listVisitSuggestions(String login, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
		
		String sql = "SELECT p.pid, p.pname, v.vis_date "
				+ "FROM POI p, Visit v "
				+ "Where v.pid = p.pid AND v.login = '"+login+"' "
				+ "ORDER BY v.vid DESC LIMIT 1;;";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				//gets the most recent recorded visit
				output+="POI ID: "+rs.getString("p.pid")+"     POI NAME: "
					+rs.getString("p.pname")+"     Visit Date: "+rs.getString("v.vis_date")+"\n";
				break;
			}
			
			//if the output is blank, advise of no recorded visit yet
			if(output.equals(""))
				System.out.print("You have not recorded any visits yet.");
			else
			{
				System.out.println("\n*** Your Most Recent Visit ***\n"+output);
				output = "";
				
				String sql_sugg = "SELECT result.pid, p.pname, COUNT(*) AS count "
						+ "FROM POI p, (SELECT v1.pid, v1.login "
						+ "FROM Visit v1, (SELECT DISTINCT p.pid, p.pname, v.login "
						+ "FROM POI p, Visit v WHERE p.pid = v.pid AND v.pid = '"+rs.getString("p.pid")+"') AS v2 "
						+ "WHERE v1.login = v2.login AND v1.pid != '"+rs.getString("p.pid")+"')AS result "
						+ "WHERE p.pid = result.pid GROUP BY result.pid ORDER BY count DESC;";
				ResultSet rs1 = null;
				rs1 = stmt.executeQuery(sql_sugg);
				
				while(rs1.next())
				{
					output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs1.getString("result.pid"), 
							"POI NAME: "+rs1.getString("p.pname"),"Total Visit Count: "+rs1.getString("count"));
				}
				
				//if the output is blank, advise of no suggestion available
				if(output.equals(""))
				{
					System.out.print("No suggestion available at this time.");
					output = "No suggestion available at this time.";//new code for web
				}
				else
				{
					System.out.println("*** Based On This Visit, Below Are Some Visit Suggestions ***\n"+output);
				}
			}
		}
		catch(Exception e)
	 	{
	 		System.out.println("Cannot execute the query.");
	 	}
	 	finally
	 	{
	 		try
	 		{
		 		if (rs!=null && !rs.isClosed())
		 			rs.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}

	 	return output;
	}//end of listVisitSuggestions
}//end of class VisitPOI



