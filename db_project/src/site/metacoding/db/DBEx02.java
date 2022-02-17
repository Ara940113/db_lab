package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// dept 테이블의 모든 내용을 출력하시오
// SELECT deptno, dname, loc FROM dept
public class DBEx02 {

	public static void main(String[] args) {
		// connection 연결
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");
			// 2. 버퍼달기
			String sql = "SELECT deptno, dname, loc FROM dept";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			// 3. while (데이터값이 false가 나올때까지 돌자)
			while (rs.next()) {
				System.out.println("deptno :" + rs.getInt("deptno"));
				System.out.println("dname :" + rs.getString("dname"));
				System.out.println("loc :" + rs.getString("loc"));
				System.out.println("===============");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
