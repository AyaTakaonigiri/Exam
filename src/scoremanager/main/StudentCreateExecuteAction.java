package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		int ent_year;
		String no;
		String name;
		String class_num;
		School school;
		Student student = new Student();
		boolean isAttend = true;
		StudentDao sDao = new StudentDao();
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメータ―の取得 2
		ent_year = Integer.parseInt(req.getParameter("ent_year"));//入学年度
		no = req.getParameter("no");//学生番号
		name = req.getParameter("name");//氏名
		class_num = req.getParameter("class_num");//クラス
		school = teacher.getSchool();//学校コード
		System.out.println(ent_year);

		if(ent_year != 0 && sDao.get(no) == null) {
			student.setEntYear(ent_year);//入学年度
			student.setNo(no);//学生番号
			student.setName(name);//氏名
			student.setClassNum(class_num);//クラス
			student.setAttend(isAttend);//在学フラグ
			student.setSchool(school);//学校コード
			sDao.save(student);
		}
		else if(ent_year == 0 && sDao.get(no) == null) {
			errors.put("f1", "入学年度を指定してください");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("StudentCreate.action").forward(req, res);
		}
		else if(ent_year != 0 && sDao.get(no) != (null)){
			errors.put("f2", "学生番号が重複しています");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("StudentCreate.action").forward(req, res);
		}
		else {
			errors.put("f1", "入学年度を指定してください");
			errors.put("f2", "学生番号が重複しています");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("StudentCreate.action").forward(req, res);
		}



		//リダイレクト


		//DBからデータ取得 3




		//ビジネスロジック 4
		//DBへデータ保存 5
		//insert_num = studentDao.save(student);


		//レスポンス値をセット 6
		//フォワード 7
		//JSPへフォワード 7
		req.getRequestDispatcher("student_create_done.jsp").forward(req, res);

	}

}