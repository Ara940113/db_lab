package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// preparestatement 변수바인딩하기
public class DBEx03 {

	public static void login(String username, String password) {
		try {

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			String sql = "SELECT * FROM userTbl WHERE username = ? AND password =? "; // 쿼리에는 변수를 넣을수 없게 되어있어서 ?
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username); // ? 위치
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println("로그인되었습니다.");
				Session.isLogin = true; // 어느파일에서든지 확인가능
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		login("ssar", "1234");

	}
}
