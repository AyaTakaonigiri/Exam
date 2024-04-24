package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		String cd = "";
		String name = "";
		School school = new School();

		cd = (String)session.getAttribute("code");
		name = req.getParameter("name");
		school = teacher.getSchool();

		SubjectDao subDao = new SubjectDao();
		Subject subject = new Subject();
		subject.setCd(cd);//科目コード
		subject.setName(name);//科目名
		subject.setSchool(school);

		subDao.delete(subject);

		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}



