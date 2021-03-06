package site.metacoding.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

// DELETE
public class DBEx07 {

	public static void main(String[] args) {
		try {
			// 1. connection 연결 (세션생성) port, ip, id, password, protocol
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			// 2. 버퍼 달아서 통신 (ALL:SELECT * FROM emp)
			String sql = "DELETE FROM userTbl WHERE id = ?"; // 끝에 세미콜론 필요 없음.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1000); // 물음표의 순서, 값

			// 에러 -1, 성공 수행된(생성,삭제,수정) row개수, 아무변화가 없으면 0
			int result = pstmt.executeUpdate(); // delete, update, insert (내부에 commit 존재)

			if (result > 0) {
				System.out.println("성공"); // 1
			} else {
				System.out.println("실패"); // 0
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}