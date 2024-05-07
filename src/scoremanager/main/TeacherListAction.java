package scoremanager.main;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherListAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //ログインしているアカウント情報を取得
        HttpSession session = request.getSession();//セッション
        Teacher teacher = (Teacher) session.getAttribute("user");//ログインユーザー

        if (teacher == null) {
            // ログインしていない場合は何らかの処理を行うか、エラーメッセージを表示してリダイレクトするなどの対処が必要です。
            response.sendRedirect("login.jsp"); // 例: ログインページにリダイレクト
            return;
        }

        List<Teacher> teachers = null;//教師のリスト（空）

        TeacherDao tDao = new TeacherDao();

        //ログインユーザーの学校の教師一覧を取得
        teachers = tDao.filter(teacher.getSchool());

        //レスポンス値をセット
        request.setAttribute("teachers", teachers);

        if (!"admin".equals(teacher.getId())) {
            request.setAttribute("error", "このアカウントはページへのアクセス権限がありません。");
            request.getRequestDispatcher("/auth_error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("teacher_list.jsp").forward(request, response);//フォワード
    }
}
