package chapter11.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdolUserSignUpDAO {
	private Connection con;

	//IdolUserSignUpDAOメソッド(例外があった場合はIdolDAOExceptionにいく)
	public IdolUserSignUpDAO() throws IdolDAOException{
		//データベースと接続
		getConnection();
	    //初期化
		PreparedStatement st = null;
		ResultSet rs = null;

		//tryブロック
		try {
			//SQL文
			String sql = "SELECT * FROM user";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

		//例外処理
		}catch(Exception e) {
			e.printStackTrace();
			//IdolDAOExceptionクラスのmessageに飛ばす
			throw new IdolDAOException("レコードの取得に失敗しました");
		//絶対する処理
		}finally {
			try {
				//データベースとの接続を終了
				if(rs != null)rs.close();
				if(rs != null)st.close();
				close();
			//例外処理
			}catch(Exception e) {
				throw new IdolDAOException("リソースの開放に失敗しました");
			}
		}
	}

	//userに値を登録するメソッド
	public int addUser(String name,int age,String password)
			throws IdolDAOException{
		//わかんない
		if(con == null)
		getConnection();

		//初期化
		PreparedStatement st = null;

		//tryブロック
		try {
			//SQL文
			 String sql = "INSERT INTO user(name,age,sex,password)VALUES(?,?,?)";
			 st = con.prepareStatement(sql);
			 //値をセット
			 st.setString(1,name);
			 st.setInt(2,age);
			 st.setString(4,password);

			 //？
			 int rows = st.executeUpdate();
			 return rows;


			 //例外処理
			 }catch(Exception e){
				 e.printStackTrace();
				 throw new IdolDAOException("レコードの取得に失敗しました");
			//絶対する処理
			 }finally {
				 //データベースとの接続を終了
				 try {
					 if(st != null)st.close();
					 close();
				//終了できなかった場合
				 }catch(Exception e) {
					 throw new IdolDAOException("リソースの開放に失敗しました");
				 }
			 }
	}
	//getConnectionメソッド
	private void getConnection() throws IdolDAOException{
		try {
			//userテーブルに接続
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = ("jdbc:mysql://localhost/user?serverTimezone=UTC&useSSL=false");
			String user = "idol_user";
			String pass = "eitaso";

			con = DriverManager.getConnection(url,user,pass);

		//例外処理
		}catch(Exception e) {
			throw new IdolDAOException("接続に失敗しました");
		}
	}
	//closeメソッド
	//データベース終了の処理
	private void close() throws SQLException{
		if(con != null) {
			con.close();
			con = null;
		}
	}



}
