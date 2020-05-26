package chapter11.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chapter11.beans.ItemBean;

public class ItemDAO {
	private Connection con;
	 ArrayList<String> arrList;

	public ItemDAO() throws DAOException{
		getConnection();
	}

	public List<ItemBean> findAll() throws DAOException{
		if(con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM item";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<ItemBean> list = new ArrayList<ItemBean>();
			while(rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code,name,price);
				list.add(bean);

			}
			// 商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの解放
				if(rs != null)rs.close();
				if(st != null)st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");

			}
		}
	}

	public List<ItemBean> sortPrice(boolean isAscending) throws DAOException{
		if(con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			String sql;
			// isAscendingがTrueの場合にif文内の処理を行う。
			if(isAscending) {
				sql = "SELECT * FROM item ORDER BY price";
			} else {
				sql = "SELECT * FROM item ORDER BY price desc";
			}
			st = con.prepareStatement(sql);

			rs = st.executeQuery();

			List<ItemBean> list = new ArrayList<ItemBean>();
			while(rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code,name,price);
				list.add(bean);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	public int addItem(String name,int price) throws DAOException{
		if(con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		try {

			String sql = "INSERT INTO item(name,price)VALUES(?,?)";

			st = con.prepareStatement(sql);

			st.setString(1,name);
			st.setInt(2,price);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {

				if(st != null)st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	public List<ItemBean> findByPrice(int lePrice) throws DAOException{
		if(con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT * FROM item WHERE price <=?";

			st = con.prepareStatement(sql);

			st.setInt(1, lePrice);

			rs = st.executeQuery();

			List<ItemBean> list = new ArrayList<ItemBean>();
			while(rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code,name,price);
				list.add(bean);
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		}finally {
			try {

				if(rs != null)rs.close();
				if(st != null)st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	public int deleteByPrimaryKey(int key) throws DAOException{
		if(con == null)
			getConnection();

		PreparedStatement st = null;
		try {

			String sql = "DELETE FROM item WHERE code = ?";

			st = con.prepareStatement(sql);

			st.setInt(1, key);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {

				if(st != null)st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	private void getConnection() throws DAOException{
		try {
			// JDBCドライバの登録
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/sample2?serverTimezone=UTC&useSSL=false";
			String user = "student";
			String pass = "himitu";
			// データベースへの接続
			con = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
	}

	private void close() throws SQLException{
		if(con != null) {
			con.close();
			con = null;
		}
	}
}
