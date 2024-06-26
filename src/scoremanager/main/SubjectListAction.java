package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ログインしているアカウント情報を取得
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		List<Subject> subjects = null;//科目のリスト（空）

		SubjectDao subDao = new SubjectDao();

		//ログインユーザーの学校の科目一覧を取得
		subjects = subDao.filter(teacher.getSchool());

		//レスポンス値をセット
		request.setAttribute("subjects", subjects);

		request.getRequestDispatcher("subject_list.jsp").forward(request, response);//フォワード
	}
}
