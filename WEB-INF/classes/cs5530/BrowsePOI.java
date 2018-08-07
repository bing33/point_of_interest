/*
 * Class: BrowsePOI
 * Description: User Keyword contains everything that has to do with browsing a POI
 * 				It contains the following methods: 
 * 				1. sortPriceL2H - used to sort the POIs base on the cost (from low to high),
 * 				2. sortPriceH2L - used to sort the POIs base on the cost (from high to low),
 * 				3. sortPriceL2H - used to sort the POIs base on the average feedback scores (from low to high),
 * 				4. sortAvgFBH2L - used to the POIs base on the average feedback scores (from high to low),
 * 			    5. sortAvgTrustedUserFBL2H - used to sort POIs base on the average feedback scores by trusted users (from low to high),
 * 				6. sortAvgTrustedUserFBH2L - used to sort POIs base on the average feedback scores by trusted users (from high to low).
 * 			    NOTE: and a lot of other methods for specifying price and/or address and/or category and/or keyword...
 * By: xrawlinson
 * Last Update: 03/21/2016
 */

package cs5530;

import java.sql.*;

public class BrowsePOI 
{
	public BrowsePOI()
	{}
	
	//sorts the POIs base on the cost (from low to high)
	public String sortPriceL2H(Statement stmt)
	{
		String sql = "SELECT pid, pname, est_price_per_person FROM POI ORDER BY est_price_per_person ASC;";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("pid"), 
						"NAME: "+rs.getString("pname"), "COST: $"+rs.getString("est_price_per_person"));
				
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
	}//end of sortPriceL2H
	
	//sorts the POIs base on the cost (from high to low)
	public String sortPriceH2L(Statement stmt)
	{
		String sql = "SELECT pid, pname, est_price_per_person FROM POI ORDER BY est_price_per_person DESC;";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("pid"), 
						"NAME: "+rs.getString("pname"), "COST: $"+rs.getString("est_price_per_person"));
				
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
	}//end of sortPriceH2L

	//sorts the POIs base on the average feedback scores (from low to high)
	public String sortAvgFBL2H(Statement stmt)
	{
		
		String sql = "SELECT p.pid, p.pname, fb.avgscr "
						+ "FROM POI p, (SELECT f.pid, AVG(f.score) AS avgscr FROM Feedback f GROUP BY f.pid) AS fb "
							+ "WHERE p.pid = fb.pid ORDER BY fb.avgscr ASC;";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), 
						"NAME: "+rs.getString("p.pname"), "Average Feedback Score: "+rs.getString("fb.avgscr"));
				
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
	}//end of sortAvgFBL2H
	
	//sorts the POIs base on the average feedback scores (from high to to)
	public String sortAvgFBH2L(Statement stmt)
	{
		String sql = "SELECT p.pid, p.pname, fb.avgscr "
				+ "FROM POI p, (SELECT f.pid, AVG(f.score) AS avgscr FROM Feedback f GROUP BY f.pid) AS fb "
					+ "WHERE p.pid = fb.pid ORDER BY fb.avgscr DESC;";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), 
						"NAME: "+rs.getString("p.pname"), "Average Feedback Score: "+rs.getString("fb.avgscr"));
				
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
	}//end of sortAvgFBH2L
	
	//sorts POIs base on the average feedback scores by trusted users (from low to high)
	public String sortAvgTrustedUserFBL2H(Statement stmt)
	{
		String sql = "SELECT p.pid, p.pname, fb.avgscr FROM POI p, "
				+ "(SELECT f.pid, AVG(f.score) AS avgscr "
					+ "FROM (SELECT DISTINCT f.pid, f.score, t.login2, t.is_trusted "
					+ "	FROM Feedback f, Trust t WHERE f.login = t.login2 AND t.is_trusted = 1) "
					+ "	AS f GROUP BY f.pid) AS fb WHERE p.pid = fb.pid ORDER BY fb.avgscr ASC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Trusted User's AVG Feedback Scores: "+rs.getString("fb.avgscr"));
				
			}
		}
		catch(Exception e)
	 	{
	 		System.out.println("cannot execute the query.");
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
	 			System.out.println("cannot close resultset");
	 		}
	 	}
	
		return output;
	}//end of sortAvgTrustedUserFBL2H
	
	//sorts POIs base on the average feedback scores by trusted users (from low to high)
	public String sortAvgTrustedUserFBH2L(Statement stmt)
	{
		String sql = "SELECT p.pid, p.pname, fb.avgscr FROM POI p, "
				+ "(SELECT f.pid, AVG(f.score) AS avgscr "
					+ "FROM (SELECT DISTINCT f.pid, f.score, t.login2, t.is_trusted "
					+ "	FROM Feedback f, Trust t WHERE f.login = t.login2 AND t.is_trusted = 1) "
					+ "	AS f GROUP BY f.pid) AS fb WHERE p.pid = fb.pid ORDER BY fb.avgscr DESC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Trusted User's AVG Feedback Scores: "+rs.getString("fb.avgscr"));
				
			}
		}
		catch(Exception e)
			{
				System.out.println("cannot execute the query.");
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
					System.out.println("cannot close resultset");
				}
			}
		
		return output;
	}//end of sortAvgTrustedUserFBH2L

	//lists POIs by specifying price, greater than or equal to
	public String listPriceLToH(String price1, String price2, Statement stmt)
	{
		String sql = "SELECT * FROM POI WHERE POI.est_price_per_person >= '"+price1+"' AND POI.est_price_per_person <= '"+price2+"' ORDER BY POI.est_price_per_person ASC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"), 
						"Estimated Price Per Person: "+rs.getString("est_price_per_person"));			
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
	}//end of listPriceGE

	public String listPriceHToL(String price1, String price2, Statement stmt)
	{
		String sql = "SELECT * FROM POI WHERE POI.est_price_per_person >= '"+price1+"' AND POI.est_price_per_person <= '"+price2+"' ORDER BY POI.est_price_per_person DESC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"), 
						"Estimated Price Per Person: "+rs.getString("est_price_per_person"));			
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
	}//end of listPriceGE

	//lists POIs by specifying price, greater than or equal to
	public String listPriceGE(String price, Statement stmt)
	{
		String sql = "SELECT * FROM POI WHERE POI.est_price_per_person >= '"+price+"' ORDER BY POI.est_price_per_person ASC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"), 
						"Estimated Price Per Person: "+rs.getString("est_price_per_person"));			
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
	}//end of listPriceGE
	
	//lists POIs by specifying price, less than or equal to
	public String listPriceLE(String price, Statement stmt)
	{
		String sql = "SELECT * FROM POI WHERE est_price_per_person <= '"+price+"' "
				+ "ORDER BY est_price_per_person ASC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"), 
						"Estimated Price Per Person: "+rs.getString("est_price_per_person"));			
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
	}//end of listPriceLE

	//displays all cities in POI table
	public String dispCities(Statement stmt)
	{
		String sql = "SELECT DISTINCT a.city FROM POI p, Address a "
				+ "WHERE a.addr_id = p.addr_id;;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output += rs.getString("city")+"\n";			
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
	}//end of dispCities
	
	//displays all states in POI table
	public String dispStates(Statement stmt)
	{
		String sql = "SELECT DISTINCT a.state FROM POI p, Address a "
				+ "WHERE a.addr_id = p.addr_id;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output += rs.getString("state")+"\n";			
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
	}//end of dispStates
	
	//displays cities and states 
	public String dispCityState(Statement stmt)
	{
		String sql = "SELECT DISTINCT a.city, a.state FROM POI p, Address a "
				+ "WHERE a.addr_id = p.addr_id;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output += String.format("%-20s %s\n", rs.getString("city"), rs.getString("state"));			
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
	}//end of dispCityState
	
	//lists POIs by specifying the city
	public String listByCity(String city, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(city.contains("'"))
			city = city.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a WHERE p.addr_id = a.addr_id AND a.city = '"+city+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listByCity
	
	//lists POIs by specifying the state
	public String listByState(String state, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a WHERE p.addr_id = a.addr_id AND a.state = '"+state+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listByState

	//list POIs by specifying cities and states
	public String listByCityState(String city, String state, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, (SELECT a.addr_id FROM Address a "
				+ "WHERE a.city = '"+city+"' AND a.state = '"+state+"') as addr "
				+ "WHERE p.addr_id = addr.addr_id;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listByCityState

	//display categories 
	public String disCate(Statement stmt)
	{
		String sql = "SELECT DISTINCT p.category FROM POI p ORDER BY p.category ASC;";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=rs.getString("p.category")+"\n";
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
	}//end of disCate
	
	//display keywords 
	public String disKeyword(Statement stmt)
	{
		String sql = "SELECT word FROM Keyword ORDER BY word ASC;";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=rs.getString("word")+"\n";
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
	}//end of disKeyword

	//list POIs by specifying categories
	public String listByCate(String cate, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(cate.contains("'"))
			cate = cate.replace("'", "''");
		
		String sql = "SELECT * FROM POI WHERE category = '"+cate+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listByCate

	//list POIs by specifying keywords
	public String listByKeyword(String kw, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(kw.contains("'"))
			kw = kw.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, HasKeyword h, Keyword k "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+kw+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listByHW

	//lists POIs by specifying city and price, greater than or equal to
	public String listCityPriceGE(String city, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(city.contains("'"))
			city = city.replace("'", "''");

		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.addr_id = a.addr_id AND a.city = '"+city+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCityPriceGE
	
	//lists POIs by specifying city and price, less than or equal to
	public String listCityPriceLE(String city, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(city.contains("'"))
			city = city.replace("'", "''");

		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.addr_id = a.addr_id AND a.city = '"+city+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCityPriceLE
	
	//lists POIs by specifying state and price, greater than or equal to
	public String listStatePriceGE(String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(state.contains("'"))
			state = state.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.addr_id = a.addr_id AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listStatePriceGE
		
	//lists POIs by specifying state and price, less than or equal to
	public String listStatePriceLE(String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.addr_id = a.addr_id AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listStatePriceLE
	
	//lists POIs by specifying city, state and price, greater than or equal to
	public String listCityStatePriceGE (String city, String state, String price, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCityStateGE 
		
	//lists POIs by specifying city, state and price, less than or equal to
	public String listCityStatePriceLE(String city, String state, String price, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person <= '"+price+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCityStatePriceLE

	//lists POIs by specifying keyword and price, greater than or equal to
	public String listByKwPriceGE(String keyword, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listByKwPriceGE
		
	//lists POIs by specifying keyword and price, less than or equal to
	public String listByKwPriceLE(String keyword, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listByKwPriceLE

	//lists POIs by specifying category, city and price, greater than or equal to
	public String listCateCityPriceGE(String category, String city, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");

		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.category = '"+category+"' AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCateCityPriceGE
	
	//lists POIs by specifying category, city and price, less than or equal to
	public String listCateCityPriceLE(String category, String city, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");

		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.category = '"+category+"' AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCateCityPriceLE
	
	//lists POIs by specifying category, state and price, greater than or equal to
	public String listCateStatePriceGE(String category, String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.category = '"+category+"' AND p.addr_id = a.addr_id AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCateStatePriceGE
		
	//lists POIs by specifying category, state and price, less than or equal to
	public String listCateStatePriceLE(String category, String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.category = '"+category+"' AND p.addr_id = a.addr_id AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCateStatePriceLE
	
	//lists POIs by specifying category, city, state and price, greater than or equal to
	public String listCateCityStatePriceGE (String category, String city, String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.category = '"+category+"' AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCateCityStatePriceGE
		
	//lists POIs by specifying category, city, state and price, less than or equal to
	public String listCateCityStatePriceLE(String category, String city, String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.category = '"+category+"' AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listCateCityStatePriceLE

	//lists POIs by specifying category and price, greater than or equal to
	public String listByCatePriceGE(String category, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		
		String sql = "SELECT * FROM POI p "
				+ "WHERE p.category = '"+category+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of CatePriceGE

	//lists POIs by specifying category and price, less than or equal to
	public String listByCatePriceLE(String category, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
				
		String sql = "SELECT * FROM POI p "
				+ "WHERE p.category = '"+category+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of CatePriceLE

	//lists POIs by specifying the keyword and the city
	public String listByKwCity(String keyword, String city, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listByKwCity
	
	//lists POIs by specifying the keyword and state
	public String listByKwState(String keyword, String state, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.state = '"+state+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listByKwState

	//list POIs by specifying keyword, cities and states
	public String listByKwCityState(String keyword, String city, String state, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listByKwCityState

	//list POIs by specifying keyword and category
	public String listKwCate(String kw, String category, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(kw.contains("'"))
			kw = kw.replace("'", "''");
		if(category.contains("'"))
			category = category.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, HasKeyword h, Keyword k "
				+ "WHERE p.pid = h.pid AND category = '"+category+"' AND h.wid = k.wid AND k.word = '"+kw+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listKwCate

	//lists POIs by specifying the category and the city
	public String listCateCity(String category, String city, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.category = '"+category+"' AND p.addr_id = a.addr_id AND a.city = '"+city+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listCateCity
	
	//lists POIs by specifying the category and state
	public String listCateState(String category, String state, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.category = '"+category+"' AND p.addr_id = a.addr_id AND a.state = '"+state+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listCateState

	//list POIs by specifying category, cities and states
	public String listCateCityState(String category, String city, String state, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(category.contains("'"))
			category = category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a "
				+ "WHERE p.category = '"+category+"' AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listCateCityState

	//lists POIs by specifying keyword, city and price, greater than or equal to
	public String listKwCityPriceGE(String keyword, String city, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND "
				+ "p.est_price_per_person >= "+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCityPriceGE
	
	//lists POIs by specifying keyword, city and price, less than or equal to
	public String listKwCityPriceLE(String keyword, String city, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");

		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCityPriceLE
	
	//lists POIs by specifying keyword, state and price, greater than or equal to
	public String listKwStatePriceGE(String keyword, String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwStatePriceGE
		
	//lists POIs by specifying keyword, state and price, less than or equal to
	public String listKwStatePriceLE(String keyword, String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwStatePriceLE
	
	//lists POIs by specifying keyword, city, state and price, greater than or equal to
	public String listKwCityStatePriceGE(String keyword, String city, String state, String price, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCityStatePriceGE
		
	//lists POIs by specifying keyword, city, state and price, less than or equal to
	public String listKwCityStatePriceLE(String keyword, String city, String state, String price, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person <= '"+price+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCityStatePriceLE

	//lists POIs by specifying keyword, category and price, greater than or equal to
	public String listKwCatePriceGE(String keyword, String category, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category = category.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}	//end of listKwCatePriceGE

	//lists POIs by specifying keyword, category and price, less than or equal to
	public String listKwCatePriceLE(String keyword, String category, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category = category.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC;";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCatePriceLE

	//lists POIs by specifying the keyword, category and the city
	public String listKwCateCity(String keyword, String category, String city, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category= category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listKwCateCity
	
	//lists POIs by specifying the keyword, category, and state
	public String listKwCateState(String keyword, String category, String state, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category= category.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.state = '"+state+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listKwCateState

	//list POIs by specifying keyword, categories, cities and states
	public String listKwCateCityState(String keyword, String category, String city, String state, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category= category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"';";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %s\n", "POI ID: "+rs.getString("pid"), "NAME: "+rs.getString("pname"));			
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
	}//end of listKwCateCityState
	
	//lists POIs by specifying keyword, category, city and price, greater than or equal to
	public String listKwCateCityPriceGE(String keyword, String category, String city, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category= category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCateCityPriceGE
	
	//lists POIs by specifying keyword, category, city and price, less than or equal to
	public String listKwCateCityPriceLE(String keyword, String category, String city, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category= category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");

		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCateCityPriceLE
	
	//lists POIs by specifying keyword, category, state and price, greater than or equal to
	public String listKwCateStatePriceGE(String keyword, String category, String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category= category.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person DESC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCateStatePriceGE
		
	//lists POIs by specifying keyword, category, state and price, less than or equal to
	public String listKwCateStatePriceLE(String keyword, String category, String state, String price1, String price2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category= category.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price1+"' AND p.est_price_per_person <= '"+price2+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCateStatePriceLE
	
	//lists POIs by specifying keyword, category, city, state and price, greater than or equal to
	public String listKwCateCityStatePriceGE(String keyword, String category, String city, String state, String price, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category= category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
		
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person >= '"+price+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCateCityStatePriceGE
		
	//lists POIs by specifying keyword, category, city, state and price, less than or equal to
	public String listKwCateCityStatePriceLE(String keyword, String category, String city, String state, String price, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
		if(category.contains("'"))
			category= category.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		if(state.contains("'"))
			state = state.replace("'", "''");
				
		String sql = "SELECT * FROM POI p, Address a, Keyword k, HasKeyword h "
				+ "WHERE p.pid = h.pid AND p.category = '"+category+"' AND h.wid = k.wid AND k.word = '"+keyword+"' "
						+ "AND p.addr_id = a.addr_id AND a.city = '"+city+"' AND a.state = '"+state+"' AND "
				+ "p.est_price_per_person <= '"+price+"' ORDER BY p.est_price_per_person ASC";
		
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"), "NAME: "+rs.getString("p.pname"), 
						"Estimated Price Per Person: "+rs.getString("p.est_price_per_person"));			
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
	}//end of listKwCateCityStatePriceLE

}//end of class BrowsePOI
