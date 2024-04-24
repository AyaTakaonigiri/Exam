package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao{

	//科目1つの取得
	public Subject get(String cd, School school) throws Exception {
		//科目インスタンスを初期化
		Subject subject = new Subject();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("SELECT NAME FROM SUBJECT  where school_cd=? and cd=?");
			//プリペアードステートメント
			statement.setString(1, school.getCd());
			statement.setString(2, cd);

			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			//学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();


			if(rSet.next()) {
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setSchool(schoolDao.get(rSet.getString("school")));
				subject.setName(rSet.getString("name"));
			} else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				subject = null;
			}
		} catch(Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if(statement != null) {
				try {
					statement.close();
				} catch(SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if(connection != null) {
				try {
					connection.close();
				} catch(SQLException sqle) {
					throw sqle;
				}
			}
		}
		return subject;

	}

	//科目の一覧を取得
	public List<Subject> filter(School school)throws Exception{
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リストを初期化
		List<Subject> list = new ArrayList<>();

		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("SELECT * FROM SUBJECT  where school_cd=?");
			//プリペアードステートメント
			statement.setString(1, school.getCd());

			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			while(rSet.next()) {
				//リザルトセットが存在する場合
				//科目インスタンスを初期化
				Subject subject = new Subject();
				subject.setCd(rSet.getString("CD"));
				subject.setName(rSet.getString("NAME"));
				list.add(subject);
			}
		} catch(Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if(statement != null) {
				try {
					statement.close();
				} catch(SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if(connection != null) {
				try {
					connection.close();
				} catch(SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
//
//	public boolean save(Subject subject){
//
//	}
//
//	public boolean delete(Subject subject){
//
//	}
}
