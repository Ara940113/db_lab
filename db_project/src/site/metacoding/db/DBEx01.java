package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBEx01 {

	public static void main(String[] args) {
		try {
			// 1. connection 연결 (세션생성) port, ip, id, password, protocol
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			// 2. 버퍼 달아서 통신 (ALL:SELECT * FROM emp)
			String sql = "SELECT empno,ename FROM emp WHERE empno = 7369"; // emp끝에 세미콜론 필요없으
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // SELECT - 자동 COMMIT 안됨
			// resultset = db에서 결과값, 커서는 항상 속성값을 가리킨다. 한칸씩 내려야 데이터값을 읽음
			// pstmt.executeUpdate(); // INSERT, UPDATE, DELETE - 자동 COMMIT
			// System.out.println(rs.next())

			while (rs.next()) {
				System.out.println("empno :" + rs.getInt("empno"));
				System.out.println("ename :" + rs.getString("ename"));
				System.out.println("======================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}