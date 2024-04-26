package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

	private String baseSql = "SELECT subject.name, subject.cd, test.no, test.point FROM test LEFT JOIN student ON student.no = test.student_no LEFT JOIN subject ON subject.cd= test.subject_cd WHERE student.no=?";

	private List<TestListStudent> postFilter(ResultSet rSet) throws SQLException{
		//リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		try {
			//リザルトセットを全権操作
			while (rSet.next()) {
				if (rSet != null) {
					TestListStudent testStu = new TestListStudent();
					//科目名
					testStu.setSubjectName(rSet.getString("name"));
					//科目コード
					testStu.setSubjectCd(rSet.getString("cd"));
					//テスト回数
					testStu.setNum(rSet.getInt("no"));
					//点数
					testStu.setPoint(rSet.getInt("point"));
					list.add(testStu);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TestListStudent> filter (Student student) throws Exception {
		//リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;

		try {
			//SQL文をセット
			statement = connection.prepareStatement(baseSql);
			//学生番号をバインド
			statement.setString(1, student.getNo());
			//プリペアードステートメントを実行
			rSet = statement.executeQuery();
			//リストへ格納
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if(connection != null) {
				try{
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
}