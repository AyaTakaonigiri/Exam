package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entyearStr="";//入力された入学年度
		String subjectStr = "";//入力されたクラス番号
		String classnumStr="";//入力された在学フラグ
		String countnumStr="";//入力された在学フラグ

		ClassNumDao cNumDao = new ClassNumDao();
		LocalDate todaysDate = LocalDate.now();//LcalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		SubjectDao subjectDao = new SubjectDao();//科目Daoを初期化


		//リクエストパラメータの取得２
		entyearStr = req.getParameter("ent_year");
		subjectStr = req.getParameter("subject");
		classnumStr =  req.getParameter("class_num");
		countnumStr =  req.getParameter("num");



		//リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		//10年前から10年後まで年をリストに追加
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}

		List<Subject> subject_list = subjectDao.filter(teacher.getSchool());

		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> class_list = cNumDao.filter(teacher.getSchool());

		//リストを初期化
		List<Integer> num_list = new ArrayList<>();
		for (int i=1; i<3; i++) {
			num_list.add(i);
		}


		//レスポンス値をセット 6
		//リクエストに入学年度をセット
		req.setAttribute("year_set", entYearSet);
		//リクエストに科目をセット
		req.setAttribute("subject_set", subject_list);
		//リクエストにクラスをセット
		req.setAttribute("class_set", class_list);
		//リクエストに回数をセット
		req.setAttribute("count_set", num_list);
		System.out.println(entYearSet);
		System.out.println(subject_list);
		System.out.println(class_list);
		System.out.println(num_list);

		//JSPへフォワード 7
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);

	}
}
