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

		String cd = "";
		String name = "";
		School school = new School();
		Subject subject = new Subject();
		SubjectDao subDao = new SubjectDao();

		cd = request.getParameter("cd");
		name = request.getParameter("name");
		school = teacher.getSchool();

		subject = subDao.get(cd, school);

		// 科目コードと科目名がnullでないときsaveを実行
		if (subject != null && name != null) {
			//入力された科目名をsubjectにセット
			subject.setName(name);
			//save実行
			subDao.save(subject);
			//subject_update_done.jspにフォワード
			request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
		} else {
			//レスポンス値をセット
			request.setAttribute("code", cd);
			request.setAttribute("name", name);
			//エラーメッセージをセット
			request.setAttribute("error", "科目が存在していません");
			//subject_update.jspにフォワード
			request.getRequestDispatcher("subject_update.jsp").forward(request, response);
		}
	}
}