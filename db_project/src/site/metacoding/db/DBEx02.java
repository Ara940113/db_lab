package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// dept ���̺��� ��� ������ ����Ͻÿ�
// SELECT deptno, dname, loc FROM dept
public class DBEx02 {

	public static void main(String[] args) {
		// connection ����
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB����Ϸ�");
			// 2. ���۴ޱ�
			String sql = "SELECT deptno, dname, loc FROM dept";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			// 3. while (�����Ͱ��� false�� ���ö����� ����)
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
