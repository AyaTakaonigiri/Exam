package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		LocalDate todaysDate = LocalDate.now();//LcalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		//リクエストパラメータ―の取得 2
		List<Integer> entYearSet = new ArrayList<>();
		//10年前から10年後まで年をリストに追加
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}
		//DBからデータ取得 3
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());
		//ビジネスロジック 4

		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//リクエストに入学年度をセット
		req.setAttribute("ent_year_set", entYearSet);
		//リクエストにクラス番号をセット
		req.setAttribute("class_num_set", list);

		//JSPへフォワード 7
		req.getRequestDispatcher("student_create.jsp").forward(req, res);

	}
}
