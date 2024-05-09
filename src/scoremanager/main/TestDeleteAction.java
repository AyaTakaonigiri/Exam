package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		//student型の全データを取得
		boolean isAttend = true;
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
		List<Student> students = studentDao.filter(teacher.getSchool(), isAttend);
		//学生番号
		String no = req.getParameter("no");

		//回数
		String num = req.getParameter("num");

		//科目コード
		String subjectcd = req.getParameter("subjectcd");
		SubjectDao subDao = new SubjectDao();
		//subject型
		Subject subject = subDao.get(subjectcd, teacher.getSchool());

		//点数（空白の人のデータは６６６になる）
		String point = req.getParameter("point");


		//Student型
		Student student = studentDao.get(no);


		//レスポンス値をセット 6
		//リクエストに学生番号をセット
		req.setAttribute("no",no);
		//リクエストに入学年度をセット
		req.setAttribute("year",student.getEntYear());
		//リクエストに氏名をセット
		req.setAttribute("name",student.getName());
		//リクエストにクラスをセット
		req.setAttribute("class_num", student.getClassNum());
		//リクエストに科目をセット
		req.setAttribute("subject", subject);
		//リクエストに点数をセット
		req.setAttribute("point", point);
		//リクエストに回数をセット
		req.setAttribute("num", num);
		//JSPへフォワード 7
		if(!point.equals("666")){
			req.getRequestDispatcher("test_delete.jsp").forward(req, res);
		}
		else if(point.equals("666")){
			req.getRequestDispatcher("test_delete_error.jsp").forward(req, res);
		}
	}
}