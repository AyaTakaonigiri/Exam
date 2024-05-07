package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		int entYear=0;
		String entyearStr="";//入力された入学年度
		String subjectCd= "";//入力された科目コード
		String classnumStr="";//入力された在学フラグ
		String countnumStr="";//入力されたテストが何回目
		int countnum=0;
		List<Test> tests = null;

		StudentDao sDao = new StudentDao();

		ClassNumDao cNumDao = new ClassNumDao();
		LocalDate todaysDate = LocalDate.now();//LcalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		SubjectDao subjectDao = new SubjectDao();//科目Daoを初期化
		TestDao testDao = new TestDao();
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメータの取得２
		entyearStr = req.getParameter("f1");
		subjectCd= req.getParameter("f2");
		classnumStr =  req.getParameter("f3");
		countnumStr = req.getParameter("f4");

		//科目コードと学校を指定して科目一覧を1件取得
		Subject subject = subjectDao.get(subjectCd, teacher.getSchool());


		//リクエストに科目をセット
		req.setAttribute("f2", subjectCd);
		//リクエストにクラス番号をセット
		req.setAttribute("f3", classnumStr);
		//リクエストに回数をセット
		if(countnumStr!=null){
			countnum = Integer.parseInt(countnumStr);
		}
		req.setAttribute("f4", countnumStr);
		//リクエストに入学年度をセット
		if(entyearStr!=null) {
			//数値に変換
			entYear = Integer.parseInt(entyearStr);
		}
		req.setAttribute("f1", entyearStr);
		if(subject!=null){
			req.setAttribute("f5", subject);
		}


		//絞り込み
		if (entYear !=0 && !classnumStr.equals("0") && !subjectCd.equals("0") && countnum!=0){
			tests = testDao.filter(entYear, classnumStr,subject,countnum, teacher.getSchool());
			if(tests == null){
				List<Student> students = null;
				students = sDao.filter(teacher.getSchool(),entYear ,classnumStr, true);
			}
		}
		else {
			errors.put("f1", "入学年度、科目、クラス、テストの回数の全てを選択してください。");
			req.setAttribute("errors", errors);
		}

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

		//リクエストに表示する生徒をセットする
		req.setAttribute("tests", tests);
		//JSPへフォワード 7
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);

	}
}
