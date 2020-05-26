package shtjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteTest {

	public static void main(String[] args) {

		try {
			// JDBCドライバの登録
			Class.forName("com.mysql.cj.jdbc.Driver");
			// URL、ユーザ名、パスワードの設定
			String url = "jdbc:mysql://localhost/sample?serverTimezone=UTC&useSSL=false";
			String user = "student";
			String pass = "himitu";
			// データベースの接続
			Connection con = DriverManager.getConnection(url, user, pass);
			// SQL文の作成
			String sql = "DELETE FROM emp WHERE code = 4";
			// PreparedStatementオブジェクトの取得
			PreparedStatement st = con.prepareStatement(sql);
			// SQLの実行
			int rows = st.executeUpdate();
			System.out.println(rows + "件、レコードを削除しました。");
			// リソースの開放
			st.close();
			con.close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
