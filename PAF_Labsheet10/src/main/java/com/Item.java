package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Item 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powereg", "root", "1234"); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertPayment(String holder_name, String ctype, String card_no, String cvv, String pay_date){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into Payment (`pay_id`, `holder_name`,`ctype`,`card_no`,`cvv`, `pay_date`)" + " values (?, ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, holder_name); 
						preparedStmt.setString(3, ctype); 
						preparedStmt.setString(4, card_no); 
						preparedStmt.setString(5, cvv); 
						preparedStmt.setString(6, pay_date); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newItems = readItems(); 
						output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readItems() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\" class=\"table\"><tr><th>Pay ID</th>"
		 		+ "<th>Holder Name</th><th>Card Type</th>"
		 		+ "<th>Card No</th>"
		 		+ "<th>CVV</th>"
		 		+ "<th>Paid Date</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from Payment"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String pay_id = Integer.toString(rs.getInt("pay_id")); 
		 String holder_name = rs.getString("holder_name"); 
		 String ctype = rs.getString("ctype"); 
		 String card_no = rs.getString("card_no");  
		 String cvv = rs.getString("cvv"); 
		 String pay_date = rs.getString("pay_date"); 
		 // Add into the html table
		 output += "<tr><td><input id ='hidpay_idUpdate' name='hidpay_idUpdate' type='hidden' value='"+pay_id+"'>"+pay_id+"</td>"; 
		 output += "<td>" + holder_name + "</td>"; 
		 output += "<td>" + ctype + "</td>"; 
		 output += "<td>" + card_no + "</td>"; 
		 output += "<td>" + cvv + "</td>"; 
		 output += "<td>" + pay_date + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-primary' data-pay_id='" + pay_id + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-pay_id='" + pay_id + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the Payment."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updatePayment(String pay_id, String holder_name, String ctype, String card_no, String cvv, String pay_date){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE Payment SET holder_name=?, ctype=?, card_no=?, cvv=?, pay_date=? WHERE pay_id=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, holder_name); 
							preparedStmt.setString(2, ctype); 
							preparedStmt.setString(3, card_no); 
							preparedStmt.setString(4, cvv); 
							preparedStmt.setString(5, pay_date); 
							preparedStmt.setInt(6, Integer.parseInt(pay_id)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newItems = readItems(); 
							output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the Payment.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deletePayment(String pay_id){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from Payment where pay_id=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(pay_id)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newItems = readItems(); 
						 output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the Payment.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
