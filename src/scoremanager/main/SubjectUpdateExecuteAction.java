package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		String cd = "";
		String name = "";
		School school = new School();
		Subject subject = new Subject();
		SubjectDao subDao = new SubjectDao();

		cd = subject.getCd();
		name = req.getParameter("name");
		school = teacher.getSchool();

		subject.setCd(cd);//科目コード
		subject.setName(name);//科目名
		subject.setSchool(school);

		//科目コードを科目名ががnullでないときsaveを実行
		if(cd != null && name != null) {
			subDao.save(subject);
			req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
		} else {
			//科目コードが存在しない場合SubjectUpdate.actionにフォワードする
			res.sendRedirect("SubjectUpdate.action");
		}
	}
}



