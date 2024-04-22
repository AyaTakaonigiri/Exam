package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Servlt implementation class FrontController
 */


@WebServlet("*.action")
public class FrontController extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		//パスを取得
		String path = request.getServletPath().substring(1);
		//ファイル名を取得しクラス名に変換
		String name = path.replace(".a", "A").replace('/', '.');

		System.out.println("★servlet path ->" + request.getServletPath());
		System.out.println("★class name ->" + name);

		//actionクラスのインスタンスを返却
		Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

		//遷移先URLを取得
		action.execute(request, response);
		//String url = action.execute(request, response);
		//request.getRequestDispatcher(url).forward(request, response);

	} catch(Exception e) {
		e.printStackTrace();
		//エラーページへリダイレクト
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
