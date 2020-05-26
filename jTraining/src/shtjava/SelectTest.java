package shtjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost/sample?serverTimezone=UTC&useSSL=false";
			String user = "student";
			String pass = "himitu";

			Connection con = DriverManager.getConnection(url,user,pass);

			String sql = "SELECT * FROM emp";
			PreparedStatement st = con.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				System.out.print(rs.getInt("code") + " : ");
				System.out.print(rs.getString("name") + " : ");
				System.out.print(rs.getInt("age") + " : ");
				System.out.println(rs.getString("tel"));
			}

			rs.close();
			st.close();
			con.close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
