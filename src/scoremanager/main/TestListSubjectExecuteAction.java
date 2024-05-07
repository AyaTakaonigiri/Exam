package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		LocalDate todaysDate = LocalDate.now();//LcalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Dao
		SubjectDao subDao = new SubjectDao();//科目Dao
		List<Subject> subjects = null;//科目のリスト（空）
		List<TestListSubject> sublist = null;//テスト結果のリスト（空）

		String entYearStr = null;
		String classNum = null;
		String sub = null;
		int entYear = 0;
		Student student = new Student();
		Subject subject = new Subject();
		TestListSubjectDao testSubDao = new TestListSubjectDao();

		//リクエストパラメータの取得２
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		sub =  request.getParameter("f3");

		////場所を変えた
		//ビジネスロジック４
		if(entYearStr != null) {
			//数値に変換
			entYear = Integer.parseInt(entYearStr);
		}

		//DBからデータ取得３
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> clist = cNumDao.filter(teacher.getSchool());

		//ログインユーザーの学校コードを基に科目の一覧を取得
		subjects = subDao.filter(teacher.getSchool());

		//リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		//10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}

		//studentをセット
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		//科目をセット
		subject.setName(sub);
		//科目参照リスト
		if (entYear != 0 && !classNum.equals("0")&& !sub.equals("0")) {
			sublist = testSubDao.filter(entYear, classNum, subject, teacher.getSchool());
		} else {
			//nullの場合はエラーメッセージをセット
			request.setAttribute("error", "入学年度とクラスと科目を選択してください");
			request.getRequestDispatcher("TestList.action").forward(request, response);
		}

		//レスポンス値をセット６
		//フォーム用＞リクエストに入学年度をセット
		request.setAttribute("ent_year_set", entYearSet);
		//フォーム用＞リクエストにクラス番号をセット
		request.setAttribute("class_num_set", clist);
		//フォーム用＞リクエストに科目をセット
		request.setAttribute("subject_set", subjects);

		request.setAttribute("f1", entYearStr);
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", sub);
		//科目参照リスト
		request.setAttribute("sublist", sublist);
		//科目リスト
		request.setAttribute("subject", subject);

		//JSPへフォワード７
		request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);


	}

}

