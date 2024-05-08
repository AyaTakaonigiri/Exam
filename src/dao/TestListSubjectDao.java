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

	private String baseSql = "SELECT student.ent_year, student.class_num, student.no AS student_no, student.name AS student_name, MAX(CASE WHEN test.no = 1 THEN test.point ELSE NULL END) AS point_1, MAX(CASE WHEN test.no = 2 THEN test.point ELSE NULL END) AS point_2 FROM student LEFT JOIN test ON student.no = test.student_no LEFT JOIN subject ON subject.cd = test.subject_cd WHERE student.ent_year = ? AND student.class_num = ? AND subject.name = ? AND student.school_cd = ? GROUP BY student.ent_year, student.class_num, student.no, student.name";

	private List<TestListSubject> postFilter(ResultSet rSet) throws SQLException{
		//リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				TestListSubject testSub = new TestListSubject();
				// TestListSubject の各フィールドに値を設定する
				testSub.setEntYear(rSet.getInt("ent_year"));
				testSub.setStudentNo(rSet.getString("student_no"));
				testSub.setStudentName(rSet.getString("student_name"));
				testSub.setClassNum(rSet.getString("class_num"));
				testSub.putPoint(1, rSet.getInt("point_1"));
				if (rSet.wasNull()) {
					testSub.putPoint(1, null);
				}
				testSub.putPoint(2, rSet.getInt("point_2"));
				if (rSet.wasNull()) {
					testSub.putPoint(2, null);
				}
				list.add(testSub);
			}
		}  catch (NullPointerException e) {
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
			//学校
			statement.setString(4, school.getCd());
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