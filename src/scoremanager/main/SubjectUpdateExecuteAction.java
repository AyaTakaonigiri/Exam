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
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		String cd = request.getParameter("cd");
		String name = "";
		School school = new School();
		Subject subject = new Subject();
		SubjectDao subDao = new SubjectDao();
		cd = subDao.get(cd, teacher.getSchool()).getCd();
		name = request.getParameter("name");
		school = teacher.getSchool();

		subject.setCd(cd);//科目コード
		subject.setName(name);//科目名
		subject.setSchool(teacher.getSchool());
		System.out.println(teacher.getSchool());

		//科目コードと科目名がnullでないときsaveを実行
		if(cd != null && name != null) {
			subDao.save(subject);
			request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("SubjectUpdate.action").forward(request, response);
		}
	}
}



