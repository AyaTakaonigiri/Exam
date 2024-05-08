package scoremanager.main;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import tool.Action;

public class TeacherListAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ログインしているアカウント情報を取得
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher) session.getAttribute("user");//ログインユーザー
		SchoolDao sDao = new SchoolDao();
		School school = new School();
		String cd = "";

		List<Teacher> teachers = null;//教師のリスト（空）

		TeacherDao tDao = new TeacherDao();

		//教師の所属学校名を取得
		cd = teacher.getSchool().getCd();
		school = sDao.get(cd);

		//ログインユーザーの学校の教師一覧を取得
		teachers = tDao.filter(teacher.getSchool());

		//レスポンス値をセット
		request.setAttribute("teachers", teachers);
		request.setAttribute("school", school);

		//adminユーザーでない場合、エラーページにフォワード
		if (!"admin".equals(teacher.getId())) {
			//エラーメッセージをセット
			request.setAttribute("error", "このアカウントはページへのアクセス権限がありません。");
			request.getRequestDispatcher("/auth_error.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("teacher_list.jsp").forward(request, response);//フォワード
	}
}
