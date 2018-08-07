/*
 * Class: Favorite 
 * Description: User Keyword contains everything that has to do with favorite POIs
 * 				It contains the following methods: 
 * 				1. favoriteExists - used to check if the favorite already exists,
 * 				2. addFavorite - used to add a favorite.
 * By: xrawlinson
 * Last Update: 03/19/2016
 */

package cs5530;

import java.sql.*;

public class Favorite 
{
	//checks if the favorite already exists
	public boolean favoriteExists(String login, String pid, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
				
		String sql_for_favorite = "SELECT pid, login FROM Favorite;";
		ResultSet rs = null;
		Boolean b = false;
		
		try
		{
			rs = stmt.executeQuery(sql_for_favorite);
			
			while(rs.next())
			{
				//if the user already declared the same POI as a favorite in the past, advise
				if(rs.getString("login").equals(login) && rs.getString("pid").equals(pid))
		 		{
		 			b = true;
		 			System.out.println("You have already declared this favorite POI.");
		 			String sql_exist_fav = "SELECT p.pname, p.pid, f.pid, f.login, f.fv_date FROM Favorite f, POI p WHERE f.pid = " 
		 					+ rs.getString("pid")+" AND f.login = '" + login + "';";
		 			
		 			ResultSet rs1 = null;
		 			
		 			rs1 = stmt.executeQuery(sql_exist_fav);

		 			while(rs1.next())
					{
		 				if(rs1.getString("f.login").equals(login) && rs1.getString("f.pid").equals(rs1.getString("p.pid")))
		 					System.out.println("You declared "+rs1.getString("p.pname")
		 							+ " as your favorite POI on "+rs1.getString("f.fv_date")+".");
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
	}//end of favoriteExists

	//adds a favorite POI 
	public void addFavorite(String pid, String login, String fv_date, Statement stmt)
	{	
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
		
		int poiID = Integer.parseInt(pid);
		
		String sql = "INSERT INTO Favorite VALUES("+poiID+",'"+login+"','"+fv_date+"');";
		
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
	}//end of addFavorite
}//end of class Favorite 
