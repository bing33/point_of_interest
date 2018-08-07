/*
 * Class: User 
 * Description: User class contains everything that has to do with users.
 * 				It contains the following methods: 
 * 				1. alreadyExists - used to check if the user login already exists,
 * 				2. addUser - used to add a user, 
 * 				3. sameAddrExists - used to check if an address already exists,
 * 				4. addAddress - used to add an address, 
 * 				5. validatesLoginAsAdmin - used to validate admin's login,
 * 				6. validatesLoginAsUser - used to validate user's login,
 * 				7. displayUsersList - used to display all users,
 * 				8. alreadyRatedUser - used to check if already rated the same user,
 * 				9. updateTrusts - used to trusted or not trusted,
 * 				10. listUserTrustful - used to list most trusted users,
 * 				11. listUserHelpful - used to list most helpful users,
 * 				12. checkSep - used to check degrees of separation.		
 * By: xrawlinson
 * Last Update: 03/19/2016
 */

package cs5530;

import java.sql.*;

public class User
{
	//keeps track of address ID
	private int addrID = 0;
	
	public User()
	{}
	
	//checks if the user login already exists in User table
	public boolean alreadyExists(String login, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");
		
		String sqlCheckLogin = "SELECT login FROM User;";
		ResultSet rs=null;
		boolean b = false;
		
		try
		{
			rs = stmt.executeQuery(sqlCheckLogin);
			
			while (rs.next())
			{	
				//if already exists, returns true
		 		if(rs.getString("login").equals(login))
		 		{
		 			b = true;
		 			break;
		 		}
		 		//else, returns false
		 		else
		 			b = false;
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

		return b;
	}//end of alreadyExits
	
	//adds a new user
	public void addUser(String login, String pswd, String fname, String lname, 
			String type, String phn_num, Statement stmt)
	{
		//parse user type so that it is in the correct types
		Boolean user_type = Boolean.parseBoolean(type);
		
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");	
		if(pswd.contains("'"))
			pswd = pswd.replace("'", "''");
		if(fname.contains("'"))
			fname = fname.replace("'", "''");	
		if(lname.contains("'"))
			lname = lname.replace("'", "''");			
		
		//insert sql statement to add a user
		String sql = "INSERT INTO User VALUES('"+login+"','"+pswd+"','"+fname+"','"
				+lname+"',"+user_type+",'"+phn_num+"',"+addrID+");";
		
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
	}//end of addUser
	
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
				//if the same address already exists, obtain the existing address's address ID
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
		
		//if the address does not exist, adds it to the Address table in the database
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
			
			//after adding the address to the Address table, obtains its address ID
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
	
	//validates the admin's login
	public boolean validatesLoginAsAdmin(String login, String pswd, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");	
		if(pswd.contains("'"))
			pswd = pswd.replace("'", "''");
				
		String sql = "SELECT login, password, user_type FROM User;";
		ResultSet rs=null;
		boolean b = false;

		try
		{
			rs = stmt.executeQuery(sql);	      
	      
   		 	while (rs.next())
			{
   		 		//it is an admin when user type is false(0 in database)
	   		 	if(rs.getString("login").equals(login) && rs.getString("password").equals(pswd)
	   		 			&& rs.getString("user_type").equals("0"))
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
	}//end of validatesLoginAsAdmin
	
	//validates the user's login
	public boolean validatesLoginAsUser(String login, String pswd, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login.contains("'"))
			login = login.replace("'", "''");	
		if(pswd.contains("'"))
			pswd = pswd.replace("'", "''");
				
		String sql = "SELECT login, password, user_type FROM User;";
		ResultSet rs=null;
		boolean b = false;

		try
		{
			rs = stmt.executeQuery(sql);	      
	      
   		 	while (rs.next())
			{
   		 		//it is a user when user type is true(1 in database)
	   		 	if(rs.getString("login").equals(login) && rs.getString("password").equals(pswd)
	   		 			&& rs.getString("user_type").equals("1"))
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
	}//validatesLoginAsUser

	//displays existing users list
	public String displayUsersList(Statement stmt)
	{
		String sql = "SELECT login FROM User;";
		ResultSet rs = null;
		String output="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+="User Login: "+rs.getString("login")+"\n";
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
	}//end of displayUsersList
	
	//checks if already rated the same user
	public String alreadyRatedUser(String login1, String login2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login1.contains("'"))
			login1 = login1.replace("'", "''");
		if(login2.contains("'"))
			login2 = login2.replace("'", "''");
				
		String sql = "SELECT * FROM Trust;";
		ResultSet rs = null;
		//boolean b = false;
		String b ="";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				if(rs.getString("login1").equals(login1) && rs.getString("login2").equals(login2))
		 		{
		 			//b = true;	
		 			//displays the rate gave in the past
		 			//System.out.println("You have already rated this user.");
		 			if(rs.getString("is_trusted").equals("1"))
		 				b = "<BR>You rated \""+rs.getString("login2")+"\" as trusted.";
		 			else
		 				b="<BR>You rated \""+rs.getString("login2")+"\" as not-trusted.";					
		 			break;
		 		}
		 		else
		 			//b = false;
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
	}//end of alreadyRatedUser
	
	//updates trusted or not trusted to the database
	public String updateTrusts(String login1, String login2, String trust, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(login1.contains("'"))
			login1 = login1.replace("'", "''");	
		if(login2.contains("'"))
			login2 = login2.replace("'", "''");	
			
		String sql = "INSERT INTO Trust VALUES('"+login1+"','"+login2+"',"+trust+");";
		String output = "";
		
		try
		{
			stmt.executeUpdate(sql);
			//displays the rating
			if(trust.equals("true"))
				output = "You rated user \""+login2+"\" as trusted.";
			else
				output = "You rated user \""+login2+"\" as not-trusted.";
	 	}
		catch(SQLException se)
		{
		    System.out.println("Cannot execute the query.");
		}
	 	catch(Exception e)
	 	{
	 		 System.out.println("Issue.\n"+e);
	 	}
		
		return output;
	}//end of updateTrusts
	
	//lists most trusted users
	public String listUserTrustful(String num, Statement stmt)
	{
		String sql = "SELECT t.login2 AS login, COUNT(t.is_trusted) AS count FROM Trust t "
				+ "WHERE t.is_trusted = 1 AND login2 NOT IN "
				+ "(SELECT login2 FROM Trust WHERE is_trusted = 0 GROUP BY login2) "
				+ "GROUP BY t.login2 UNION "
				+ "SELECT l1.login1 AS login, l1.countone-l2.countzero AS count "
				+ "FROM (SELECT t1.login2 AS login1, COUNT(*) AS countone FROM Trust t1, Trust t2 "
				+ "WHERE t1.login2 = t2.login2 AND t1.is_trusted > t2.is_trusted "
				+ "GROUP BY t1.login2) AS l1, (SELECT t.login2 AS login2, COUNT(*) AS countzero FROM Trust t "
				+ "WHERE t.is_trusted = 0 GROUP BY t.login2) AS l2 "
				+ "WHERE l1.login1 = l2.login2 ORDER BY count DESC LIMIT 0,"+num+";";
		
		ResultSet rs = null;
		String output = "";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				//count of trusted - count of not-trusted needs to be greater than 0 to be considered as trusted
				if(Integer.parseInt(rs.getString("count"))>0)
				{
					output+=String.format("%-40s %s\n", "User Login: "+rs.getString("login"),
						"Trusting Count: "+rs.getString("count"));
				}
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
	}//end of listUserTrustful
	
	//lists most helpful users
	public String listUserHelpful(String num, Statement stmt)
	{
		String sql = "SELECT u.login AS login, AVG(r.rating) avgrating "
						+ "FROM User u, Feedback f, Rate r "
						+ "WHERE u.login = f.login AND f.fid = r.fid "
						+ "GROUP BY u.login ORDER BY avgrating DESC LIMIT 0,"+num+";";
		
		ResultSet rs = null;
		String output = "";
		
		try
		{
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				output+=String.format("%-40s %s\n", "User Login: "+rs.getString("login"),
						"Usefulness Score: "+rs.getString("avgrating"));
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
	}//end of listUserHelpful
	
	//checks degrees of separation
	public String checkSep(String user1, String user2, Statement stmt)
	{
		//handles apostrophe(single quote), 
		//without doing this it kept getting an error when ' exists within a word
		if(user2.contains("'"))
			user1 = user1.replace("'", "''");	
		if(user2.contains("'"))
			user2 = user2.replace("'", "''");
				
		String sql_one_degree = "SELECT * FROM (SELECT f.pid, f.login FROM Favorite f "
				+ "WHERE f.login = '"+user1+"') AS u1, (SELECT f.pid, f.login FROM Favorite f "
				+ "WHERE f.login = '"+user2+"') AS u2 WHERE u1.pid = u2.pid;";
		
		ResultSet rs = null;
		String output = "";
		
		try
		{
			rs = stmt.executeQuery(sql_one_degree);
			
			while(rs.next())
			{
				//one-degree
				output+=String.format("%-30s %-30s %s\n", "First User: "+user1,
						"Second User: "+user2, 
						"Degree of Separation: One");
			}
			
			//if one degree output is blank, check for two-degree
			if(output.equals(""))
			{
				String sql_two_degree = "SELECT l1.pid, l1.login, l2.pid, l2.login "
						+ "FROM (SELECT u1.pid, u2.login "
						+ "FROM (SELECT f.pid, f.login FROM Favorite f WHERE f.login = '"+user1+"') AS u1, "
						+ "(SELECT f.pid, f.login FROM Favorite f) AS u2 "
						+ "WHERE u1.pid = u2.pid AND u1.login != u2.login) AS l1, "
						+ "(SELECT u1.pid, u2.login "
						+ "FROM (SELECT f.pid, f.login FROM Favorite f WHERE f.login = '"+user2+"') AS u1, "
						+ "(SELECT f.pid, f.login FROM Favorite f) AS u2 "
						+ "WHERE u1.pid = u2.pid AND u1.login != u2.login) AS l2 WHERE l1.login = l2.login;";
				
				ResultSet rs1 = null;
				
				rs1 = stmt.executeQuery(sql_two_degree);
				
				while(rs1.next())
				{
					output+=String.format("%-30s %-30s %s\n", "First User: "+user1,
							"Second User: "+user2, 
							"Degree of Separation: Two");
					break;
				}
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
	}//end of checkSep
}//end of class User	
	