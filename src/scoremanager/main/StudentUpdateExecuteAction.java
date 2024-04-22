package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String no = req.getParameter("no");
		int entyear = Integer.parseInt(req.getParameter("entyear"));
		String name = req.getParameter("name");
		String class_num = req.getParameter("class_num");
		boolean isAttend = req.getParameter("si_attend") != null;

		StudentDao sDao = new StudentDao();
		Student student = new Student();
		student.setEntYear(entyear);//入学年度
		student.setNo(no);//学生番号
		student.setName(name);//氏名
		student.setClassNum(class_num);//クラス
		student.setAttend(isAttend);
		sDao.save(student);




		req.getRequestDispatcher("student_update_done.jsp").forward(req, res);



	}
}



