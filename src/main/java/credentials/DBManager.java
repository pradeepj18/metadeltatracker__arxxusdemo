package credentials;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class DBManager {
	public static Connection con;
	static Statement stmt;
	static ResultSet rs;

	/*
	 * create type checkstatus as enum('true','false'); create table sfdcmetadata
	 * (datakey varchar(100),entrydate timestamp,metadata text,status checkstatus);
	 */

	public static Connection loadDriver() throws URISyntaxException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		con = DriverManager.getConnection(dbUrl, username, password);
		return con;
	}

	public static Connection loadDriverTest() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/herokutest", "postgres", "psql");
		} catch (ClassNotFoundException e) {
			System.out.println("Exception in DBManager....for load driver.." + e);
		}

		return con;
	}

	public static ResultSet fetchQuery(String query) {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			return rs;
		} catch (Exception e) {
			System.out.println("Exception in DBManager....for ResultSet.." + e.getMessage());
		}
		return null;
	}

	public static int CreUpDel(String query) {
		try {
			System.out.println("---"+query);
			stmt = con.createStatement();
			int rowAffected = stmt.executeUpdate(query);
			if (rowAffected > 0)
				return rowAffected;
		}

		catch (Exception e) {
			System.out.println("Exception in DBManager....for CreUpDel.." + e.getMessage());
		}
		return 0;
	}

	public static void close() {
		try {
			stmt.close();
			con.close();
		}

		catch (Exception e) {
			System.out.println("Exception in DBManager....for CreUpDel.." + e.getMessage());
		}
	}

	public static void deleteFiles(File file) {
		try {
			if (file.exists()) {
				file.delete();
			}
			else {System.out.println("not");}
		} catch (Exception e) {
			System.out.println("deletefile " + e.getMessage());
		}
	}

}