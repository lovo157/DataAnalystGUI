package application;
import java.sql.*;

public class Queries {

	private Connection conn() throws SQLException 
	{
		
		Connection conn = DriverManager.getConnection("jdbc:sqLite:C:\\sqlite3\mydb");
		return conn;
	}
	
	public void q1() throws SQLException
	{
		Connection conn = this.conn();
		ResultsSet rs = conn.createStatement().executeQuery(SELECT * FROM titles);
		while(rs.next());{
			System.out.println(rs.getNstring("id"));
		}
	}
	
}
