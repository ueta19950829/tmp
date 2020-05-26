package chapter11.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chapter11.beans.ListBean;

public class ListDAO {
	private Connection con;

	public ListDAO() throws DAOException{
		getConnection();
	}

	public List<ListBean> findAll() throws DAOException{
		if(con == null)
			getConnection();


		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM list";
			st = con.prepareStatement(sql);
			rs  = st.executeQuery();
			List<ListBean>list = new ArrayList<ListBean>();
			while(rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				Date releasedate = rs.getDate("releasedate");
				ListBean bean = new ListBean(code,name,releasedate);
				list.add(bean);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}finally {
			try {
				if(rs !=null)rs.close();
				if(st !=null)st.close();
				close();
			}catch(Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	private void getConnection() throws DAOException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/songslist?serverTimezone=UTC&useSSL=false";
			String user = "sakana";
			String pass ="action";
			con= DriverManager.getConnection(url,user,pass);
		}catch (Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
	}

	private void close() throws SQLException{
		if(con !=null) {
			con.close();
			con =null;
		}
	}
}
