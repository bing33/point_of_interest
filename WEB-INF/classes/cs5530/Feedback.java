/*
 * Class: Feedback
 * Description: User Feedback contains everything that has to do with feedbacks.
 * 				It contains the following methods: 
 * 				1. feedbackExists - used to check if the feedback already exists,
 * 				2. addFeedback - used to add a feedback, 
 * 				3. displayTitle - display the title that includes the POI name,
 * 				4. displayFeedbacks - used to display all feedbacks for a given POI, 
 * 				5. displayTopFeedbacks - used to display top feedbacks for a POI base on its average usefulness score,
 * 				6. checkFBID - used check if the Feedback ID being selected is from the Feedback table.		
 * By: xrawlinson
 * Last Update: 03/19/2016
 */

package cs5530;

import java.sql.*;

public class Feedback 
{
	//keeps track of the feedback IDs that is related to the selected POI
	private String fb_IDs;
	
	//checks if the feedback already exists
 	public boolean feedbackExists(String login, String pid, Statement stmt)
	{
 		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
 				
		String sql_for_feedback = "SELECT login, pid FROM Feedback;";
		ResultSet rs = null;
		Boolean b = false;
		
		try
		{
			rs = stmt.executeQuery(sql_for_feedback);
			
			while(rs.next())
			{
				if(rs.getString("login").equals(login) && rs.getString("pid").equals(pid))
		 		{
		 			b = true;
		 			
		 			//display the feedback that the user provided for the POI in the past
		 			System.out.println("You have already added a feedback for this POI.");
		 			String sql_exist_fb = "SELECT p.pname, p.pid, f.fb_date, f.pid, f.login, f.score, f.comments "
		 					+ "FROM POI p, Feedback f WHERE f.pid = " 
		 					+ rs.getString("pid")+" AND f.login = '" + login + "';";
		 			
		 			ResultSet rs1 = null;
		 			rs1 = stmt.executeQuery(sql_exist_fb);

		 			while(rs1.next())
					{
		 				if(rs1.getString("f.login").equals(login) && rs1.getString("f.pid").equals(rs1.getString("p.pid")))
		 					System.out.println("You gave "+rs1.getString("p.pname")
		 							+ " a score of " + rs1.getString("f.score")+" on "
		 							+ rs1.getString("f.fb_date")+" with the comment: "
		 							+ rs1.getString("f.comments"));
					}
		 			break;
		 		}
		 		else
		 			b = false;
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
	}//feedbackExists
	
	//adds a feedback for a POI
	public void addFeedback(String fb_date, String pid, String login, String score, String comments, Statement stmt)	
	{	
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
		if(comments.contains("'"))
			comments = comments.replace("'", "''");
				
		int poiID = Integer.parseInt(pid);
		int fb_score = Integer.parseInt(score);
		String sql;
		
		//comment is optional
		if(comments.equals(""))
		{
			sql = "INSERT INTO Feedback (fb_date,pid,login,score)"
					+ "VALUES('"+fb_date+"',"+poiID+",'"+login+"',"+fb_score+");";
		}
		else
		{	
			sql = "INSERT INTO Feedback (fb_date,pid,login,score,comments)"
				+ "VALUES('"+fb_date+"',"+poiID+",'"+login+"',"+fb_score+",'"+comments+"');";
		}
		
		try
		{
			stmt.executeUpdate(sql);
			System.out.println("Feedback Date: "+fb_date+"    POI ID: "+poiID+"    Score: "+fb_score+"    Comment: "+comments);
		}
		catch(SQLException se)
		{
		    se.printStackTrace();
		    System.out.println("Cannot execute the query.");
		}
	 	catch(Exception e)
	 	{
	 		e.printStackTrace();
	 	}	
	}//end of addFeedback
	
	//displays the title that includes the POI name
	public String displayTitle(String pid, Statement stmt)
	{
		String sql_for_POI_name = "SELECT pid, pname FROM POI WHERE pid = " + pid;
		ResultSet rs = null;
		String poi_name ="";
		try
		{
			rs = stmt.executeQuery(sql_for_POI_name);
			
			while(rs.next())
			{
				if(rs.getString("pid").equals(pid))
				{
					poi_name = rs.getString("pname");
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
	
		System.out.println("\n**** Feedbacks of \"" + poi_name + "\" ****");
		return "<BR>**** Feedbacks of \"" + poi_name + "\" ****<BR>";
	}//end of displayTitle

	//displays feedbacks for a given POI
	public String displayFeedbacks(String pid, Statement stmt)
	{
		String sql = "SELECT f.fid, f.fb_date, f.login, f.score, f.comments, f.pid, p.pid"
				+ " FROM Feedback f, POI p WHERE f.pid = p.pid AND f.pid = '" + pid + "';";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				//gets the details of feedback
				if(rs.getString("f.pid").equals(pid) && rs.getString("f.pid").equals(rs.getString("p.pid")))
				{
					output+=String.format("%-20s %-20s %-20s %-15s %s\n", "FeedBack ID: "+rs.getString("fid"), 
							"Date: "+rs.getString("fb_date"), "User: "+rs.getString("login"), 
							"Score: "+rs.getString("score"), "Comment: "+rs.getString("comments"));
					
					//obtain he feedback ID
					fb_IDs+=rs.getString("fid")+" ";
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
	}//end of displayFeedbacks
		
	//displays top feedbacks for a given POI, base on its average usefulness score
	public String displayTopFeedbacks(String pid, String topnum, Statement stmt)
	{
		String sql = "SELECT f.fid, f.fb_date, f.login, f.score, f.comments, ls.fid, AVG(ls.rate) as avgrate "
					+ "FROM Feedback f, (SELECT f.fid AS fid, f.pid, r.rating as rate "
						+ "FROM Feedback f, Rate r WHERE f.fid = r.fid AND f.pid = '"+pid+"') "
							+ "AS ls WHERE f.fid = ls.fid GROUP BY ls.fid ORDER BY avgrate DESC LIMIT 0,"+topnum+";";
		
		ResultSet rs = null;
		String output="";
				
		try
		{
			rs = stmt.executeQuery(sql);
					
			while(rs.next())
			{
				//gets the details of feedback, also add the average score
				output+=String.format("%-20s %-30s %-20s %-20s %-15s %s\n","FeedBack ID: "
						+rs.getString("f.fid"), "AGV Rate: "+rs.getString("avgrate"),
							"Date: "+rs.getString("f.fb_date"), "User: "+rs.getString("f.login"),
								"Score: "+rs.getString("f.score"),"Comment: "+rs.getString("f.comments"));
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
	}//end of displayTopFeedbacks
	
	//ensures the Feedback ID being selected is from the Feedback table
	public boolean checkFBID(String fid, Statement stmt)
	{
		return fb_IDs.contains(fid);
	}//end of checkFBID
}//end of class Feedback
