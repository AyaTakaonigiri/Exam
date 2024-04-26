package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	private String baseSql = "select * from student where school_cd=? and ent_year=? and class_num=?";

	private List<TestListSubject> postFilter(ResultSet rSet) throws SQLException{
		//リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		try {
			//リザルトセットを全権操作
			while (rSet.next()) {
				if (rSet != null) {
					TestListSubject testSub = new TestListSubject();
					testSub.setEntYear(rSet.getInt("ent_year"));
					testSub.setStudentNo(rSet.getString("no"));
					testSub.setStudentName(rSet.getString("name"));
					testSub.setClassNum(rSet.getString("class_num"));
					list.add(testSub);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TestListSubject> filter (Student student) throws Exception {
		//リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文の条件①
		String condition = "select cd from subject where ";
		//SQL文の条件②
		String subNamesort = "select name from subject LEFT JOIN test on subject.cd = test.subject_cd where cd=?";

		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition);
			//プリペアードステートメントに学校コード
			statement.setString(1, student.getSchool().getCd());
			//プリペアードステートメントに入学年度
			statement.setInt(2, student.getEntYear());
			//プリペアードステートメントにクラス番号
			statement.setString(3, student.getClassNum());
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