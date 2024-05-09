package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectUpdateAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ローカル変数の宣言
		String cd = "";//科目コード
		String name = "";//科目名
//		HttpSession session = request.getSession(); // セッション

		//リクエストパラメータの取得
		cd = request.getParameter("cd");
		name = request.getParameter("name");

//		//変更中に削除された場合セッションから取得し、エラーメッセージを表示する
//		if (cd==null || name==null) {
//			cd = (String)session.getAttribute("code");
//			name = (String)session.getAttribute("name");
//			request.setAttribute("error", "科目が存在していません");
//		}

		//レスポンス値をセット
		request.setAttribute("code", cd);
		request.setAttribute("name", name);
//		session.setAttribute("code", cd);
//		session.setAttribute("name", name);

		request.getRequestDispatcher("subject_update.jsp").forward(request, response);
	}
}
