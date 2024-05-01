package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	private String baseSql = "select * from test where school_cd=?";
	//Test型のインスタンスを１件取得する
//	public Test get(Student student, Subject subject, School school, int no) throws Exception {
//
//	}

	public List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		// リストの初期化
		List<Test> list = new ArrayList<>();
		try{
			StudentDao stuDao = new StudentDao();
			SubjectDao subDao = new SubjectDao();
			SchoolDao schDao = new SchoolDao();
			// リザルトを全権走査
			while(rSet.next()){
				// 学生インスタンスを初期化
				Test t = new Test();
				// 学生インスタンスを初期化
				t.setStudent(stuDao.get(rSet.getString("student_no")));
				t.setClassNum(rSet.getString("class_num"));
				t.setSubject(subDao.get(rSet.getString("subject_cd"),school));
				t.setSchool(schDao.get(rSet.getString("school_cd")));
				t.setNo(rSet.getInt("no"));
				t.setPoint(rSet.getInt("point"));

				if(stuDao.get(t.student().getNo())!=null){
					// リストに追加
					list.add(t);
				}

			}

		}catch(SQLException | NullPointerException e){
			e.printStackTrace();
		}




		// listを返す
		return list;
	}

	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// SQL文の条件
		String condition = " and class_num = ? and subject_cd = ? and no = ?";
		// SQL文のソート
		String order = " order by student_no asc";
		// リザルトセット
		ResultSet rSet = null;
		try{
			// プリペアードステートメントにSQｌ文をセット
			statement = connection.prepareStatement(baseSql + condition + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(2, classNum);

			statement.setString(3, subject.getCd());

			statement.setInt(4, num);

			// プライベートステートメントを実行
			rSet = statement.executeQuery();

			// リストへの格納処理を実行
			list = postFilter(rSet, school);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(statement != null){
				try{
					statement.close();

				} catch(SQLException sqle){
					throw sqle;
				}
			}
			if (connection != null){
				try{
					connection.close();
				}catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		// とってきたデータの数分ループ
		// listを返す
		return list;
	}
}

