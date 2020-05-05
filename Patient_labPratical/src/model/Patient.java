package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Patient {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			 // Class.forName("com.mysql.cj.jdbc.Driver");
			  Class.forName("com.mysql.jdbc.Driver");
		

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/patientservice", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertItem(String pname, String ppsw, String pdob, String pnic) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into patient(`pid`,`pname`,`ppsw`,`pdob`,`pnic`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pname);
			preparedStmt.setString(3, ppsw);
			preparedStmt.setString(4, pdob);
			preparedStmt.setString(5, pnic);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>pname</th><th>ppsw</th><th>pdob</th><th>pnic</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String pid = Integer.toString(rs.getInt("pid"));
				String pname = rs.getString("pname");
				String ppsw = rs.getString("ppsw");
				String pdob = rs.getString("pdob");
				String pnic = rs.getString("pnic");
				
				// Add into the html table
				output += "<tr><td><input id=\"hidItemIDUpdate\" name=\"hidItemIDUpdate\"type=\"hidden\" value=\"" + pid + "\">"+ pname + "</td>";

				
				output += "<td>" + ppsw + "</td>";
				output += "<td>" + pdob + "</td>";
				output += "<td>" + pnic + "</td>";
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btnUpdate btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"patient.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + pid + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateItem(String pid, String pname, String ppsw, String pdob, String pnic) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE patient SET pname=?,ppsw=?,pdob=?,pnic=? WHERE pid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, pname);
			preparedStmt.setString(2, ppsw);
			preparedStmt.setString(3, pdob);
			preparedStmt.setString(4, pnic);
			preparedStmt.setInt(5, Integer.parseInt(pid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteItem(String pid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from patient where pid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
