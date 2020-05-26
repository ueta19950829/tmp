package shtjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest2 {

	public static void main(String[] args) {
		try {
			// 入力されていない場合に処理終了
			 if (args.length != 1) {
				 System.out.println("年齢を入力してください");
				 System.exit(1);
			 }
			 // 年齢取得
			 int age = Integer.parseInt(args[0]);
			 // JDBCドライバの登録
			Class.forName("com.mysql.cj.jdbc.Driver");
			// URL、ユーザ名、パスワードの設定
			String url = "jdbc:mysql://localhost/sample?serverTimezone=UTC&useSSL=false";
			String user = "student";
			String pass = "himitu";
			// データベースの接続
			Connection con = DriverManager.getConnection(url,user,pass);
			// SQL文の設定
			String sql = "SELECT * FROM emp WHERE age >= ?";
			// PreparedStatement の取得
			PreparedStatement st = con.prepareStatement(sql);
			// プレースホルダーの設定
			st.setInt(1,age);
			// SQLの実行
			ResultSet rs = st.executeQuery();
			// SQLの結果取得
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
