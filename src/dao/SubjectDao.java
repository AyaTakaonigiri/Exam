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

			statement = connection.prepareStatement("SELECT * FROM SUBJECT where school_cd=? and cd=?");
			//プリペアードステートメント
			statement.setString(1, school.getCd());
			statement.setString(2, cd);

			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			if(rSet.next()) {
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
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
	public List<Subject> filter(School school) throws Exception{
		//リストを初期化

		List<Subject> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文の条件
		String base = "select * from subject where school_cd=?";

		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(base);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			//プリペアードステートメントを実行
			rSet = statement.executeQuery();
			//学校インスタンスを初期化
			SchoolDao schoolDao = new SchoolDao();
			while (rSet.next()) {
				Subject subject = new Subject();
				//科目インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
				list.add(subject);
			}
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

	public boolean save(Subject subject) throws Exception{
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try {
			//データベースから科目を取得
			Subject Sold = get(subject.getCd(), subject.getSchool());
			if (Sold==null) {
				//科目が存在しなかった場合
				//プリペアードステートメントにinsert文をセット
				statement = connection.prepareStatement("insert into subject(cd, name, school_cd) values (?, ?, ?)");
				//プリペアードステートメントに値をバインド
				statement.setString(1,  subject.getCd());
				statement.setString(2, subject.getName());
				statement.setString(3, subject.getSchool().getCd());
			} else {
				//科目が存在した場合
				//プリペアードステートメントにupdate文をセット
				statement = connection.prepareStatement("update subject set name=? where cd=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, subject.getName());
				statement.setString(2, subject.getCd());
			}
			//プリペアードステートメントを実行
			count = statement.executeUpdate();
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

		if(count > 0) {
			//実行件数が１件以上ある場合
			return true;
		} else {
			//実行件数が０件の場合
			return false;
		}
	}

	public boolean delete(Subject subject) throws Exception{
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try {
			//プリペアードステートメントにdelete文をセット
			statement = connection.prepareStatement("delete subject where cd=?");
			//プリペアードステートメントに値をバインド
			statement.setString(1,  subject.getCd());

			//プリペアードステートメントを実行
			count = statement.executeUpdate();
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

		if(count > 0) {
			//実行件数が１件以上ある場合
			return true;
		} else {
			//実行件数が０件の場合
			return false;
		}
	}
}
