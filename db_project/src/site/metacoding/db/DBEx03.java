package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// preparestatement �������ε��ϱ�
public class DBEx03 {

	public static void login(String username, String password) {
		try {

			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB����Ϸ�");

			String sql = "SELECT * FROM userTbl WHERE username = ? AND password =? "; // �������� ������ ������ ���� �Ǿ��־ ?
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username); // ? ��ġ
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println("�α��εǾ����ϴ�.");
				Session.isLogin = true; // ������Ͽ������� Ȯ�ΰ���
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		login("ssar", "1234");

	}
}