package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	private String baseSql = "select * from test where school_cd=?";
	//Test型のインスタンスを１件取得する
//	public Test get(Student student, Subject subject, School school, int no) throws Exception {
//
//	}

	public List<Test> postFilter(ResultSet rSet, School school, String classNum, int entYear) throws Exception {
		// リストの初期化
		List<Test> list = new ArrayList<>();
		StudentDao studentDao = new StudentDao();
		//クラス指定された全生徒
		List<Student> students = studentDao.filter(school, entYear, classNum, true);
		List<String> studentsno = new ArrayList<>();
		int flag=0;

		try{

			StudentDao stuDao = new StudentDao();
			SubjectDao subDao = new SubjectDao();
			SchoolDao schDao = new SchoolDao();

			while(rSet.next()){
				// テストインスタンスを初期化
				Test t = new Test();
				t.setStudent(stuDao.get(rSet.getString("student_no")));
				t.setClassNum(rSet.getString("class_num"));
				t.setSubject(subDao.get(rSet.getString("subject_cd"),school));
				t.setSchool(schDao.get(rSet.getString("school_cd")));
				t.setNo(rSet.getInt("no"));
				t.setPoint(rSet.getInt("point"));
				// リストに追加
				list.add(t);
				studentsno.add(rSet.getString("student_no"));
			}

			// リザルトを全権走査
			for(int i=0; i<students.size(); i++){
				for(int ii=0; ii<studentsno.size(); ii++) {
					if(students.get(i).getNo()==studentsno.get(ii)){
						flag=1;
					}
				}
				if(flag!=1){
					Test t = new Test();
					Student student = stuDao.get(students.get(i).getNo());
					t.setStudent(student);
					t.setClassNum(student.getClassNum());
					t.setSchool(student.getSchool());
					t.setPoint(666);
					System.out.println(t.getPoint());
					list.add(t);
				}
				flag=0;
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
			list = postFilter(rSet, school, classNum, entYear);

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

	public boolean save(List<Test> tests) throws Exception {
		int count = 0;
		for(int i=0; i<tests.size(); i++){
			Test test = tests.get(i);
			// コネクションを確立
			Connection connection = getConnection();
			// プリペアードステートメント
			PreparedStatement statement = null;
			if(tests.get(i).getSubject()!=null){
				try{
					// プリペアードステートメントにSQｌ文をセット
					statement = connection.prepareStatement("update TEST SET POINT=? where STUDENT_NO=? and SUBJECT_CD=? and SCHOOL_CD='"+test.student().getSchool().getCd()+"' and NO=?");
					// プリペアードステートメントに点数をバインド
					statement.setInt(1, test.getPoint());
					// プリペアードステートメントに学生番号をバインド
					statement.setString(2, test.student().getNo());
					// プリペアードステートメントに科目コードをバインド
					statement.setString(3, test.getSubject().getCd());
					// プリペアードステートメントに回数をバインド
					statement.setInt(4, test.getNo());
					//プリペアードステートメントを実行
					count = statement.executeUpdate();
					if(count==0){
						try{
							// プリペアードステートメントにSQｌ文をセット
							statement = connection.prepareStatement(
								"INSERT INTO TEST VALUES (?, ?, '"+test.student().getSchool().getCd()+"', ?, ?, '"+test.student().getClassNum()+"');");
							// プリペアードステートメントに学生番号をバインド
							statement.setString(1, test.student().getNo());
							// プリペアードステートメントに科目コードをバインド
							statement.setString(2, test.getSubject().getCd());
							// プリペアードステートメントに回数をバインド
							statement.setInt(3, test.getNo());
							// プリペアードステートメントに点数をバインド
							statement.setInt(4, test.getPoint());
							//プリペアードステートメントを実行
							count = statement.executeUpdate();

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

					}
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
			}
		}
		return true;
	}
}

