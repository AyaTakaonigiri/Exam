package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	HttpSession session = req.getSession();//セッション
	Teacher teacher = (Teacher)session.getAttribute("user");
	String no = req.getParameter("no");//学生番号
	String num = req.getParameter("num");//回数
	String subjectcd = req.getParameter("subjectcd");
	SubjectDao subDao=new SubjectDao();
	Subject subject = subDao.get(subjectcd, teacher.getSchool());


	TestDao tDao = new TestDao();
	boolean delete = tDao.delete(no, num, subject);

	if(delete==true){
		req.getRequestDispatcher("test_delete_done.jsp").forward(req, res);
	}
	else{
		req.getRequestDispatcher("test_delete_error.jsp").forward(req, res);
	}

	}
}
