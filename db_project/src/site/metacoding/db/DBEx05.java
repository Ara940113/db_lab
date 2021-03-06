package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// dept 테이블의 모든 내용을 출력하시오
// SELECT deptno, dname, loc FROM dept
public class DBEx05 {

	public static void main(String[] args) {
		// connection 연결
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");
			// 2. 버퍼달기
			String sql = "SELECT deptno, dname, loc FROM dept";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			List<Dept> depts = new ArrayList<>();
			while (rs.next()) {
				Dept dept = new Dept(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc"));
				depts.add(dept);
			}

			// for each 문 사용해서 출력

			for (Dept dept : depts) { // 왼쪽 : 컬렉션 타입, 오른쪽 : 컬렉션
				System.out.println("deptno :" + dept.getDeptno());
				System.out.println("dname :" + dept.getDname());
				System.out.println("loc :" + dept.getLoc());
				System.out.println("===============");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
