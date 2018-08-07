/*
 * Class: Rating
 * Description: User Rating contains everything that has to do with ratings
 * 				It contains the following methods: 
 * 				1. rateOwnFB - used to check if trying to rate own feedbacks,
 * 				2. ratingExists - used to checks if a rating already exists from the user for the same feedback,
 * 				3. addRating - used to add a new rating for a feedback.
 * By: xrawlinson
 * Last Update: 03/19/2016
 */

package cs5530;

import java.sql.*;

public class Rating 
{
	//checks to see if selected own feedback to rate
	public boolean rateOwnFB(String login, String fid, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
				
		String sql_check_own_feedback = "SELECT r.fid, f.fid, f.login FROM Rate r, Feedback f WHERE f.fid = '" + fid + "';";//r.fid = f.fid AND 
		ResultSet rs = null;
		Boolean b = false;
		
		try
		{
			rs = stmt.executeQuery(sql_check_own_feedback);
			
			while(rs.next())
			{
				if(rs.getString("f.fid").equals(fid)
						&& rs.getString("f.login").equals(login))
				{
					b = true;
					System.out.println("You cannot rate your own feedbacks!");
					break;
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
		return b;
	}//end of rateOwnFB
	
	//checks if a rating already exists from the user for the same feedback
	public String ratingExists(String login, String fid, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
				
		String sql_for_rate = "SELECT login, fid FROM Rate;";
		ResultSet rs = null;
		//Boolean b = false;
		String b ="";
		
		try
		{
		
			rs = stmt.executeQuery(sql_for_rate);
			
			while(rs.next())
			{
				if(rs.getString("login").equals(login) && rs.getString("fid").equals(fid))
		 		{
		 			//b = true;
		 			//display the feedback that the user provided for the POI in the past
		 			System.out.println("You have already rated this feedback.");
		 			String sql_exist_rate = "SELECT r.login, r.fid, r.rating, f.fid "
		 					+ "FROM Rate r, Feedback f WHERE f.fid = " 
		 					+ rs.getString("fid")+" AND r.login = '" + login + "';";
		 			
		 			ResultSet rs1 = null;
		 			rs1 = stmt.executeQuery(sql_exist_rate);

		 			while(rs1.next())
					{
		 				if(rs1.getString("r.login").equals(login) && rs1.getString("r.fid").equals(rs1.getString("f.fid")))
		 				{
		 					System.out.println("You gave a rating of " + rs1.getString("r.rating"));
		 					b="<BR>You gave a rating of " + rs1.getString("r.rating")+".";
		 					break;
		 				}
					}
		 			break;
		 		}
		 		else
		 			b="";
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
		return b;
	}//end of ratingExists
	
	//adds a new rating for a feedback
	public void addRating(String login, String fid, String rating, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
				
		String sql = "INSERT INTO Rate VALUES('"+login+"',"+fid+",'"+rating+"');";
		
		try
		{
			stmt.executeUpdate(sql);
		}
		catch(SQLException se)
		{
		    System.out.println("Cannot execute the query.");
		}
	 	catch(Exception e)
	 	{
	 		 System.out.println("Issue.\n"+e);
	 	}	
	}//end of addRating
}//end of class Rating
