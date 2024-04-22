package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String no = req.getParameter("no");//学生番号
		StudentDao sDao = new StudentDao();
		Student student = sDao.get(no);

		req.setAttribute("entyear", student.getEntYear());
		req.setAttribute("no", student.getNo());
		req.setAttribute("name", student.getName());
		req.setAttribute("class_num", student.getClassNum());
		req.setAttribute("attend", student.isAttend());
		//JSPへフォワード 7
		req.getRequestDispatcher("student_delete.jsp").forward(req, res);

	}
}