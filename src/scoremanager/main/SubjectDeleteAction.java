package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class SubjectDeleteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ローカル変数の宣言
		String cd = "";//科目コード
		String name = "";//科目名
		HttpSession session = request.getSession(); // セッション


		//リクエストパラメータの取得
		cd = request.getParameter("cd");
		name = request.getParameter("name");

		//レスポンス値をセット
		request.setAttribute("cd", cd);
		request.setAttribute("name", name);
	    session.setAttribute("code", cd);
	    session.setAttribute("name", name);

		request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
	}
}
