/*
 * Class: POI
 * Description: User Keyword contains everything that has to do with POIs
 * 				It contains the following methods: 
 * 				1. sameAddrExists - used to check if the same address already exists in the Address table,
 * 				2. addAddress - used to add an address,
 * 				3. addPOI - used to add a POI,
 * 				4. keywordExits - used to check if the keyword already exists in the keyword table,
 * 				5. addKeywords - used to add keywords
 * 			    6. displayPOIList - used to display POIs for users to select,
 * 				7. checkPoiID - used to ensure the POI ID being selected is from the POI table,
 * 				8. displayPOIName - used to display POI names,
 * 				9. displaySpecificPOI - used to display a specific POI after a pid is selected by the user
 * 				10. updatesAField - used to update a specific field,
 * 				11. listPopularPOI - used to list popular POIs,
 * 				12. listExpPOI - used to list expensive POIs,
 * 				13. listHighlyRatedPOI - used to list highly rated POIs.
 * By: xrawlinson
 * Last Update: 03/19/2016
 */

package cs5530;

import java.sql.*;
import java.util.ArrayList;

public class POI
{
	//keeps track of address ID, POI ID, and keyword ID
	private int addrID = 0;
	private int poiID = 0;
	private int wid = 0;
	
	public POI()
	{}
	
	//checks if the same address already exists in the Address table
	private boolean sameAddrExists(String street, String city, String state, String zip, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(street.contains("'"))
			street = street.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
				
		String sqlCheckAddr = "SELECT * FROM Address;";
		ResultSet rs = null;
		boolean b = false;
		
		try
		{
			rs = stmt.executeQuery(sqlCheckAddr);
			
			while(rs.next())
			{
				//if the address already exists in the Address table, obtain the address ID
				if(rs.getString("street").equals(street) && rs.getString("city").equals(city)
						&& rs.getString("state").equals(state) && rs.getString("zip").equals(zip))
		 		{
		 			b = true;
		 			addrID = Integer.parseInt(rs.getString("addr_id"));		 			
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
	}//end of sameAddrExists
	
	//adds a new address
	public void addAddress(String st, String city, String state, String zip, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(st.contains("'"))
			st = st.replace("'", "''");
		if(city.contains("'"))
			city = city.replace("'", "''");
		
		//if the address does not already exists in the Address table, adds it
		if(!sameAddrExists(st, city, state, zip, stmt))
		{
			int zipcode = Integer.parseInt(zip);
			
			String sql = "INSERT INTO Address (street, city, state, zip) "
					+ "VALUES('"+st+"','"+city+"','"+state+"',"+zipcode+");";
			
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
			
			//obtains the address ID
			String sqlForAddrID = "SELECT addr_id FROM Address ORDER BY addr_id DESC LIMIT 1;";
			ResultSet rs=null;
			
			try
			{
				rs = stmt.executeQuery(sqlForAddrID);
				
				while (rs.next())
				{		 
			 		addrID = Integer.parseInt(rs.getString("addr_id"));
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
		}
	}//end of addAddress
	
	//adds a new POI
	public void addPOI(String pname, String URL, String phone_num, String year_estab, 
			String open_hr, String est_price_per_person, String category, Statement stmt)
	{	
		//parse to the correct types
		int year = Integer.parseInt(year_estab);
		int price = Integer.parseInt(est_price_per_person);
		
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(pname.contains("'"))
			pname = pname.replace("'", "''");
		if(open_hr.contains("'"))
			open_hr = open_hr.replace("'", "''");
		if(category.contains("'"))
			category = category.replace("'", "''");
			
		//insert sql statement to add a POI
		String sql = "INSERT INTO POI (pname, URL, phone_num, year_estab, open_hr, est_price_per_person, addr_id, category) "
				+ "VALUES('"+pname+"','"+URL+"',"+phone_num+","+year+",'"+open_hr+"',"+price+","+addrID+",'"+category+"');";
		
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
		
		//obtains the POI ID
		String sqlForPOIID = "SELECT pid FROM POI ORDER BY pid DESC LIMIT 1;";
		ResultSet rs=null;
		
		try
		{
			rs = stmt.executeQuery(sqlForPOIID);
			
			while (rs.next())
			{		 
		 		poiID = Integer.parseInt(rs.getString("pid"));
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
	}//end of addPOI
	
	//checks if the keyword already exists in the keyword table
	private boolean keywordExits(String keyword, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(keyword.contains("'"))
			keyword = keyword.replace("'", "''");
				
		String sqlCheckKW = "SELECT * FROM Keyword;";
		ResultSet rs = null;
		boolean b = false;
		
		try
		{
			rs = stmt.executeQuery(sqlCheckKW);
			
			while(rs.next())
			{
				if(rs.getString("word").equals(keyword))
		 		{
		 			b = true;
		 			wid = Integer.parseInt(rs.getString("wid"));		 			
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
	}//end of keywordExits
	
	//adds keywords
	public void addKeywords(String allkeywords, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(allkeywords.contains("'"))
			allkeywords = allkeywords.replace("'", "''");
		
		//splits user's entry to get each keyword and store each of them to the Keyword table
		String[] keyword = allkeywords.split(" ");
		for(int i = 0; i<keyword.length; i++)
		{	
			String sql_keyword = "INSERT INTO Keyword (word) VALUES('"+keyword[i]+"');";
	
			try
			{
				if(!keywordExits(keyword[i],stmt))
				{
					stmt.executeUpdate(sql_keyword);
			
					//for each keyword added, gets its wid in order to add the HasKeywordTable for the related POI
					String sql_getWid = "SELECT wid FROM Keyword ORDER BY wid DESC LIMIT 1;";
					ResultSet rs=null;
					
					try
					{
						rs = stmt.executeQuery(sql_getWid);
						
						while (rs.next())
						{		 
					 		wid = Integer.parseInt(rs.getString("wid"));
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
				}
				
				//adds pid and related wid to HasKeyword table
				String sql_haskeyword = "INSERT INTO HasKeyword VALUES("+poiID+","+wid+");";
				stmt.executeUpdate(sql_haskeyword);	
			}	 	
			catch(SQLException se)
			{
			    System.out.println("Cannot execute the query.");
			}
		 	catch(Exception e)
		 	{
		 		 System.out.println("Issue.\n"+e);
		 	}
		}//end of for
	}//end of addKeywords
	
	//displays existing POIs for users to select
	public String displayPOIList(Statement stmt)
	{
		String sql = "SELECT pid, pname FROM POI;";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=rs.getString("pid")+"   "+rs.getString("pname")+"\n";
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
	}//end of displayPOIList

	//ensures the POI ID being selected is from the POI table
	public boolean checkPoiID(String pid, Statement stmt)
	{
		String sql_POI_id = "SELECT pid FROM POI;";
		ResultSet rs = null;
		Boolean b = false;
		
		try
		{
			rs = stmt.executeQuery(sql_POI_id);
			
			while(rs.next())
			{
				if(rs.getString("pid").equals(pid))
		 		{
		 			b = true;		 			
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
	}//end of checkPoiID
	
	//displays the POI name
	public String displayPOIName(String pid, Statement stmt)
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
		
		return poi_name;
	}//end of displayPOIName

	//displays a specific POI after a pid is selected by the user
	public String displaySpecificPOI(String pid, Statement stmt)
	{
		//since there can be multiple keywords for one POI, needs to gather them into a string
		String sql_keywd = "SELECT k.word "
				+ "FROM HasKeyword h, Keyword k, (SELECT p.pid, p.pname, p.URL, p.phone_num, p.year_estab, "
				+ "p.open_hr, p.est_price_per_person, p.addr_id, p.category, a.street, a.city, "
				+ "a.state, a.zip FROM POI p, Address a WHERE p.addr_id = a.addr_id AND p.pid = '"+pid+"') as poi "
				+ "WHERE h.wid = k.wid AND h.pid = poi.pid;";
		ResultSet rs1 = null;
		String output = "";
		String keywords = "";
		
		try
		{
			rs1 = stmt.executeQuery(sql_keywd);
			//gets the keywords
			while(rs1.next())
			{
				keywords+= rs1.getString("k.word")+ " ";
			}
			
			String sql = "SELECT poi.pid, poi.pname, poi.URL, poi.phone_num, poi.year_estab, "
					+ "poi.open_hr, poi.est_price_per_person, poi.addr_id, poi.category, "
					+ "poi.street, poi.city, poi.state, poi.zip, k.word "
					+ "FROM HasKeyword h, Keyword k, (SELECT p.pid, p.pname, p.URL, p.phone_num, p.year_estab, "
					+ "p.open_hr, p.est_price_per_person, p.addr_id, p.category, a.street, a.city, "
					+ "a.state, a.zip FROM POI p, Address a WHERE p.addr_id = a.addr_id AND p.pid = '"+pid+"') as poi "
					+ "WHERE h.wid = k.wid AND h.pid = poi.pid;";
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			//after getting all the keywords, display details of the POI
			while(rs.next())
			{
				//rs.getString returns yyyy-mm-dd although only year exists as the data in the database
				//so needs to takes out only the year
				String year = rs.getString("poi.year_estab");
				String[] parts = year.split("-");
				year = parts[0];			

				output += "\n1. POI Name: "+rs.getString("poi.pname")+"\n2. URL: "+rs.getString("poi.URL")
					+"\n3. Phone Number: "+rs.getString("poi.phone_num")+"\n4. Year Established: "+year
						+"\n5. Open Hours: "+rs.getString("poi.open_hr")+"\n6. Estimated Price Per Person: $"
							+rs.getString("poi.est_price_per_person") +"\n7. Street: "+rs.getString("poi.street")
								+"\n8. City: "+rs.getString("poi.city") +"\n9. State: "+rs.getString("poi.state")
									+"\n10. Zip: "+rs.getString("poi.zip") +"\n11. Category: "+rs.getString("poi.category")
										+"\n12. Keywords: "+keywords;
				
				break;
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}

		return output;
	}//end of displaySpecificPOI

	//updates a specified field
	public void updatesAField(String pid, String num, String newdata, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(newdata.contains("'"))
			newdata = newdata.replace("'", "''");	
		
		String originalWid = "";
		
		String sql = "SELECT p.pid, p.pname, p.URL, p.phone_num, "
				+ "p.year_estab, p.open_hr, p.est_price_per_person, p.category, p.addr_id, a.addr_id, a.street, a.city, "
				+ "a.state, a.zip FROM POI p, Address a WHERE p.addr_id = a.addr_id AND p.pid = '"+pid+"';";
		ResultSet rs = null;
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				//base on user's selection, update the selected field
				if(rs.getString("p.pid").equals(pid) && rs.getString("p.addr_id").equals(rs.getString("a.addr_id")))
				{
					String poi_field = "";
					String addr_field = "";
					
					if(num.equals("1"))
						poi_field = "p.pname";
					else if(num.equals("2"))
						poi_field = "p.URL";
					else if(num.equals("3"))
						poi_field = "p.phone_num";
					else if(num.equals("4"))
						poi_field = "p.year_estab";
					else if(num.equals("5"))
						poi_field = "p.open_hr";
					else if(num.equals("6"))
						poi_field = "p.est_price_per_person";
					else if(num.equals("7"))
						addr_field = "a.street";
					else if(num.equals("8"))
						addr_field = "a.city";
					else if(num.equals("9"))
						addr_field = "a.state";
					else if(num.equals("10"))
						addr_field = "a.zip";
					else if(num.equals("11"))
						poi_field = "p.category";
					
					try
					{
						//update to POI table
						if(num.equals("1")|| num.equals("2")|| num.equals("3") || num.equals("4") 
								|| num.equals("5") || num.equals("6")|| num.equals("11"))
						{
							String sql_update_poi_field = "UPDATE POI p SET "+poi_field+" = '"
									+newdata+"' WHERE p.pid = '"+pid+"';";
							stmt.executeUpdate(sql_update_poi_field);
						}
						//update to Address table
						else if (num.equals("7")||num.equals("8")||num.equals("9")||num.equals("10"))
						{
							String sql_update_addr_field = "UPDATE Address a, POI p SET "+addr_field+" = '"+newdata+"' "
									+ "WHERE a.addr_id = p.addr_id AND p.pid = '"+pid+"';";
							stmt.executeUpdate(sql_update_addr_field );
						}
						//update to Keyword table
						else
						{
							String sql1 = "SELECT * FROM HasKeyword WHERE pid = '"+pid+"';";
							ResultSet rs1 = null;
							rs1 = stmt.executeQuery(sql1);
							while(rs1.next())
							{
								System.out.println(rs1.getString("pid")+" pid "+rs1.getString("wid")+" wid ");
								originalWid = rs1.getString("wid");

								String[] keyword = newdata.split(" ");
								for(int i = 0; i<keyword.length; i++)
								{	
									
									//String sql_update_keywd_field = "UPDATE Keyword k SET k.word = '"+keyword[i]+";";
									String sql_keyword = "INSERT INTO Keyword (word) VALUES('"+keyword[i]+"');";
							
									System.out.println("keyword: "+keyword[i]);
									if(!keywordExits(keyword[i],stmt))
									{
										stmt.executeUpdate(sql_keyword);
								
										//for each keyword added, gets its wid in order to add the HasKeywordTable for the related POI
										String sql_getWid = "SELECT wid FROM Keyword ORDER BY wid DESC LIMIT 1;";		
										ResultSet rs2 = null;
										
										try
										{
											rs2 = stmt.executeQuery(sql_getWid);
											
											while (rs2.next())
											{		 
												wid = Integer.parseInt(rs2.getString("wid"));
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
									}
									System.out.println("(((: "+originalWid);
		
									String sql_haskeyword = "UPDATE HasKeyword Set wid = '"+wid+"', pid = '"+pid+"' "
											+ "WHERE wid = '"+originalWid+"' AND pid = '"+pid+"';";
									
									stmt.executeUpdate(sql_haskeyword);		
								}//end of for
								break;
							}//end of while
						}//end of else
					}//end of try
					catch(SQLException se)
					{
					    System.out.println("Cannot execute the query.");
					}
				 	catch(Exception e)
				 	{
				 		 System.out.println("Issue.\n"+e);
				 		 e.printStackTrace();
				 	}
					
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
	}

	//lists popular POIs
	public String listCat(String num, Statement stmt)
	{
		//finds the categories
		String sql_cate = "SELECT DISTINCT p.category FROM POI p, Visit v WHERE p.pid = v.pid;";
		ResultSet rs1 = null;
		ArrayList<String> category = new ArrayList<String>(); 
		
		String output = "";
		String finalout ="checking";
		String s = "";
		
		try
		{
			rs1 = stmt.executeQuery(sql_cate);
			
			while(rs1.next())
			{
			    category.add(rs1.getString("category"));
			}
			
			ResultSet rs = null;
		

			for(int i=0; i<category.size();i++)
			{	
				//base on total visits for each category	
				String cate = category.get(i);
				s = "<BR>*** Most Popular POIs For \""+cate+"\" *** ";
			
				output = s + "<BR>" + listPopularPOI(cate, num, stmt) + output;
				finalout = output;
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
		
		return finalout;
	}//end of listPopularPOI

	//lists popular POIs
	public String listPopularPOI(String cate, String num, Statement stmt)
	{
		//finds the categories
		String sql_cate = "SELECT DISTINCT p.category FROM POI p, Visit v WHERE p.pid = v.pid;";
		ResultSet rs1 = null;
		ArrayList<String> category = new ArrayList<String>(); 
		
		String output = "";
		String s = "";
		
		try
		{
			
			ResultSet rs = null;

				
			String sql = "SELECT p1.pid, p1.pname, poi.totalvisit "
					+ "FROM POI p1, (SELECT p.pid, p.category, COUNT(p.pid) AS totalvisit "
					+ "FROM POI p, Visit v WHERE p.pid = v.pid GROUP BY p.pid, p.category) as poi "
					+ "WHERE p1.pid = poi.pid AND poi.category = '"+cate+"' ORDER BY poi.totalvisit DESC LIMIT 0,"+num+";";
			
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{		
				output = output + String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p1.pid"),
						"POI NAME: "+rs.getString("p1.pname"), "TOTAL VISIT: "+rs.getString("poi.totalvisit"));				
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
		
		return output;
	}//end of listPopularPOI
	
	//lists popular POIs
	/*public String listPopularPOI(String num, Statement stmt)
	{
		//finds the categories
		String sql_cate = "SELECT DISTINCT p.category FROM POI p, Visit v WHERE p.pid = v.pid;";
		ResultSet rs1 = null;
		ArrayList<String> category = new ArrayList<String>(); 
		
		String output = "";
		String s = "";
		
		try
		{
			rs1 = stmt.executeQuery(sql_cate);
			
			while(rs1.next())
			{
			    category.add(rs1.getString("category"));
			}
			String cate = "";
			ResultSet rs = null;

			for(int i=0; i<category.size();i++)
			{	
				//base on total visits for each category	
				cate = category.get(i);
				//System.out.println("\n*** Most Popular POIs For \""+cate+"\" ***");
				
				String sql = "SELECT p1.pid, p1.pname, poi.totalvisit "
						+ "FROM POI p1, (SELECT p.pid, p.category, COUNT(p.pid) AS totalvisit "
						+ "FROM POI p, Visit v WHERE p.pid = v.pid GROUP BY p.pid, p.category) as poi "
						+ "WHERE p1.pid = poi.pid AND poi.category = '"+cate+"' ORDER BY poi.totalvisit DESC LIMIT 0,"+num+";";
				
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{		
					//s = "*** Most Popular POIs For \""+cate+"\" *** ";

					//System.out.print(String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p1.pid"),
						//"POI NAME: "+rs.getString("p1.pname"), "TOTAL VISIT: "+rs.getString("poi.totalvisit")));
					output = output +  "<BR>" + String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p1.pid"),
							"POI NAME: "+rs.getString("p1.pname"), "TOTAL VISIT: "+rs.getString("poi.totalvisit"));
					
				}			

				//System.out.println("");
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
		
		return output;
	}//end of listPopularPOI*/

	//lists popular POIs
	public String listEx(String num, Statement stmt)
	{
		//finds the categories
		String sql_cate = "SELECT DISTINCT p.category FROM POI p, Visit v WHERE p.pid = v.pid;";
		ResultSet rs1 = null;
		ArrayList<String> category = new ArrayList<String>(); 
		
		String output = "";
		String finalout ="checking";
		String s = "";
		
		try
		{
			rs1 = stmt.executeQuery(sql_cate);
			
			while(rs1.next())
			{
			    category.add(rs1.getString("category"));
			}
			
			ResultSet rs = null;
		

			for(int i=0; i<category.size();i++)
			{	
				//base on total visits for each category	
				String cate = category.get(i);
				s = "<BR>*** Most Expensive POIs For \""+cate+"\" *** ";
			
				output = s + "<BR>" + listExpPOI(cate, num, stmt) + output;
				finalout = output;
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
		
		return finalout;
	}//end of listPopularPOI

	//lists popular POIs
	public String listExpPOI(String cate, String num, Statement stmt)
	{
		//finds the categories
		String sql_cate = "SELECT DISTINCT p.category FROM POI p, Visit v WHERE p.pid = v.pid;";
		ResultSet rs1 = null;
		ArrayList<String> category = new ArrayList<String>(); 
		
		String output = "";
		String s = "";
		
		try
		{
			
			ResultSet rs = null;

				
			String sql = "SELECT p.pid, p.pname, poi.avgcost "
						+ "FROM POI p, (SELECT v.pid, AVG(ve.cost/ve.num_of_people) AS avgcost "
						+ "FROM Visit v, VisEvent ve WHERE v.vid = ve.vid GROUP BY v.pid) AS poi "
						+ "WHERE p.pid = poi.pid AND p.category = '"+cate+"' ORDER BY poi.avgcost DESC LIMIT 0,"+num+";";
			
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{		
				output = output + String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"),
						"POI NAME: "+rs.getString("p.pname"), "AVERAGE COST PER PERSON: "+rs.getString("poi.avgcost"));				
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
		
		return output;
	}//end of listPopularPOI
	
	//lists expensive POIs
	/*public void listExpPOI(String num, Statement stmt)
	{
		//finds the categories
		String sql_cate = "SELECT DISTINCT p.category FROM POI p, Visit v WHERE p.pid = v.pid;";
		ResultSet rs1 = null;
		ArrayList<String> category = new ArrayList<String>(); 
		
		try
		{
			rs1 = stmt.executeQuery(sql_cate);
			
			while(rs1.next())
			{
			    category.add(rs1.getString("category"));
			}
			
			for(int i=0; i<category.size();i++)
			{	
					
				//base on average cost per person of all visits to a POI for each category
				String cate = category.get(i);
				System.out.println("\n*** Most Expensive POIs For \""+cate+"\" ***");
				String sql = "SELECT p.pid, p.pname, poi.avgcost "
						+ "FROM POI p, (SELECT v.pid, AVG(ve.cost/ve.num_of_people) AS avgcost "
						+ "FROM Visit v, VisEvent ve WHERE v.vid = ve.vid GROUP BY v.pid) AS poi "
						+ "WHERE p.pid = poi.pid AND p.category = '"+cate+"' ORDER BY poi.avgcost DESC LIMIT 0,"+num+";";
						
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{	
					System.out.print(String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"),
						"POI NAME: "+rs.getString("p.pname"), "AVERAGE COST PER PERSON: "+rs.getString("poi.avgcost")));
				}
				
				System.out.println("");
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
	}//end of listExpPOI*/

	//lists popular POIs
	public String listHi(String num, Statement stmt)
	{
		//finds the categories
		String sql_cate = "SELECT DISTINCT p.category FROM POI p, Feedback f WHERE p.pid = f.pid;";
		ResultSet rs1 = null;
		ArrayList<String> category = new ArrayList<String>(); 
		
		String output = "";
		String finalout ="checking";
		String s = "";
		
		try
		{
			rs1 = stmt.executeQuery(sql_cate);
			
			while(rs1.next())
			{
			    category.add(rs1.getString("category"));
			}
			
			ResultSet rs = null;
		

			for(int i=0; i<category.size();i++)
			{	
				//base on total visits for each category	
				String cate = category.get(i);
				s = "<BR>*** Highly Rated POIs For \""+cate+"\" *** ";
			
				output = s + "<BR>" + listHighlyRatedPOI(cate, num, stmt) + output;
				finalout = output;
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
		
		return finalout;
	}//end of listPopularPOI

	//lists popular POIs
	public String listHighlyRatedPOI(String cate, String num, Statement stmt)
	{
		//finds the categories
		String sql_cate = "SELECT DISTINCT p.category FROM POI p, Feedback f WHERE p.pid = f.pid;";
		ResultSet rs1 = null;
		ArrayList<String> category = new ArrayList<String>(); 
		
		String output = "";
		String s = "";
		
		try
		{
			
			ResultSet rs = null;

				
			String sql = "SELECT p.pid, p.pname, poi.avgscore "
						+ "FROM POI p, (SELECT pid, AVG(score) avgscore FROM Feedback GROUP BY pid) AS poi "
						+ "WHERE p.pid = poi.pid AND p.category = '"+cate+"' ORDER BY poi.avgscore DESC LIMIT 0,"+num+";";;
			
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{		
				output = output + String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"),
						"POI NAME: "+rs.getString("p.pname"), "AVERAGE SCORE: "+rs.getString("poi.avgscore"));			
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
		
		return output;
	}//end of listPopularPOI
		
	//lists highly rated POIs
	/*public void listHighlyRatedPOI(String num, Statement stmt)
	{
		//finds the categories
		String sql_cate = "SELECT DISTINCT p.category FROM POI p, Feedback f WHERE p.pid = f.pid;";
		ResultSet rs1 = null;
		ArrayList<String> category = new ArrayList<String>(); 
		
		try
		{
			rs1 = stmt.executeQuery(sql_cate);
			
			while(rs1.next())
			{
			    category.add(rs1.getString("category"));
			}
			
			for(int i=0; i<category.size();i++)
			{	
				//base on average scores from all feedbacks a POI has received for each category	
				String cate = category.get(i);
				System.out.println("\n*** Highly Rated POIs For \""+cate+"\" ***");
				String sql = "SELECT p.pid, p.pname, poi.avgscore "
						+ "FROM POI p, (SELECT pid, AVG(score) avgscore FROM Feedback GROUP BY pid) AS poi "
						+ "WHERE p.pid = poi.pid AND p.category = '"+cate+"' ORDER BY poi.avgscore DESC LIMIT 0,"+num+";";

				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				while(rs.next())
				{	
					System.out.print(String.format("%-20s %-40s %s\n", "POI ID: "+rs.getString("p.pid"),
						"POI NAME: "+rs.getString("p.pname"), "AVERAGE SCORE: "+rs.getString("poi.avgscore")));
				}
				
				System.out.println("");
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
		 		if (rs1!=null && !rs1.isClosed())
		 			rs1.close();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("Cannot close resultset");
	 		}
	 	}
	}//end of listHighlyRatedPOI*/
}//end of class POI

