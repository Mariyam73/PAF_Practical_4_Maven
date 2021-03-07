package com;

import java.sql.*;

public class Item {
	
	//Connection Code
			public Connection connect()
			{
				Connection con = null;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					con= DriverManager.getConnection("jdbc:mysql://localhost:3306/paf_lab_3",
					"root", "Shahimaria@123");
					//For testing
					System.out.print("Successfully connected");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return con;
			}
			
			
			//inserting item
			public String insertItem(String code, String name, String price, String desc) throws SQLException {
				
				String output = "";
				
				try
				{
					Connection con = connect();
					
					if (con == null)
					{
						return "Error while connecting to the database";
					}
					
					// create a prepared statement
					String query = " insert into item(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
					+ " values (?, ?, ?, ?, ?)";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, code);
					preparedStmt.setString(3, name);
					preparedStmt.setDouble(4, Double.parseDouble(price));
					preparedStmt.setString(5, desc);
					
					//execute the statement
					preparedStmt.execute();
					con.close();
					
					output = "Inserted successfully";
				}
				catch (Exception e)
				{
					output = "Error while inserting";
					System.err.println(e.getMessage());
				}
				
				return output;
			}
			
			
			
			//Read Items
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
					output = "<table border='1'><tr><th>Item Code</th>"
					+"<th>Item Name</th><th>Item Price</th>"
					+ "<th>Item Description</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
					String query = "select * from item";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
						String itemID = Integer.toString(rs.getInt("itemID"));
						String itemCode = rs.getString("itemCode");
						String itemName = rs.getString("itemName");
						String itemPrice = Double.toString(rs.getDouble("itemPrice"));
						String itemDesc = rs.getString("itemDesc");
						
						// Add a row into the html table
						output += "<tr><td>" + itemCode + "</td>";
						output += "<td>" + itemName + "</td>";
						output += "<td>" + itemPrice + "</td>";
						output += "<td>" + itemDesc + "</td>";
						// buttons
						output += "<td><form method='post' action='updateItem.jsp'>"
						+ "<input name='btnUpdate' "
						+ " type='submit' value='Update' class='btn btn-secondary'>"
						+ "<input name='itemID' type='hidden' "
						+ " value=' " + itemID + "'>"
						+ "<input name='itemCode' type='hidden' "
						+ " value=' " + itemCode + "'>"
						+ "<input name='itemName' type='hidden' "
						+ " value=' " + itemName + "'>"
						+ "<input name='itemPrice' type='hidden' "
						+ " value=' " + itemPrice + "'>"
						+ "<input name='itemDesc' type='hidden' "
						+ " value=' " + itemDesc + "'>"
						+ "</form></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' "
						+ " type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' "
						+ " value='" + itemID + "'>" + "</form></td></tr>";
						}
						con.close();
						
						// Complete the html table
						output += "</table>";
					}
					catch (Exception e)
					{
						output = "Error while reading the items.";
						System.err.println(e.getMessage());
					}
					return output;
			}
			
			
			
			
			
			//update Item
			public String updateItem(String code, String name, String price, String desc) throws SQLException {
				
				String output = "";
				
				try
				{
					Connection con = connect();
					
					if (con == null)
					{
						return "Error while connecting to the database";
					}
					
					// create a prepared statement
					String query = "update item set itemName=?, itemPrice=?, itemDesc=?" + "where itemCode="+code;
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setString(1, name);
					preparedStmt.setDouble(2, Double.parseDouble(price));
					preparedStmt.setString(3, desc);
					
					//execute the statement
					preparedStmt.execute();
					con.close();
					
					output = "Updated successfully";
				}
				catch (Exception e)
				{
					output = "Error while updating";
					System.err.println(e.getMessage());
				}
				
				return output;
			}
			
			
			//Delete Item
			public String deleteItem(int itemCode) {
				
				String output = "";
				
				Connection con = connect();
				
				String query = "delete from item " + "where itemID="+itemCode;
				
				try{
					PreparedStatement preparedStmt = con.prepareStatement(query);

					preparedStmt.executeUpdate();
					
					output = "Deleted Successfully!!";
				}
				catch (Exception e) {
					output = "Error while deleting";
					e.printStackTrace();
				}
				
				return output;
			}

			


}
