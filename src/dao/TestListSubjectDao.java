package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	private String baseSql = "SELECT student.ent_year, student.class_num, student.no AS student_no, student.name AS student_name, MAX(CASE WHEN test.no = 1 THEN test.point ELSE NULL END) AS point_1, MAX(CASE WHEN test.no = 2 THEN test.point ELSE NULL END) AS point_2 FROM student LEFT JOIN test ON student.no = test.student_no LEFT JOIN subject ON subject.cd = test.subject_cd WHERE student.ent_year = ? AND student.class_num = ? AND subject.name = ? GROUP BY student.ent_year, student.class_num, student.no, student.name";

	private List<TestListSubject> postFilter(ResultSet rSet) throws SQLException{
		//リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		try {
			//リザルトセットを全権操作
			while (rSet.next()) {
				if (rSet != null) {
					TestListSubject testSub = new TestListSubject();
					//入学年度
					testSub.setEntYear(rSet.getInt("ent_year"));
					//学生番号
					testSub.setStudentNo(rSet.getString("student_no"));
					//学生名
					testSub.setStudentName(rSet.getString("student_name"));
					//クラス番号
					testSub.setClassNum(rSet.getString("class_num"));
					//テストの点数
					testSub.putPoint(1, rSet.getInt("point_1"));
					testSub.putPoint(2, rSet.getInt("point_2"));
					//リストに追加
					list.add(testSub);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TestListSubject> filter (int entYear, String classNum, Subject subject, School school) throws Exception {
		//リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;

		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql);
			//入学年度
			statement.setInt(1, entYear);
			//クラス
			statement.setString(2, classNum);
			//科目
			statement.setString(3, subject.getName());
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