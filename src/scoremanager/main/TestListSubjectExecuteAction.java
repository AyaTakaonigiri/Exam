package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entYearStr = "";
		String classNum = "";
		String sub = "";
		int entYear = 0;
		Student student = new Student();
		Subject subject = new Subject();
		TestListSubjectDao testSubDao = new TestListSubjectDao();
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
//		StudentDao sDao = new StudentDao();
//		Map<String, String> errors = new HashMap<>();//エラーメッセージ

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
		//studentをセット
		student.setNo(student.getNo());
		student.setName(student.getName());
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(student.isAttend());
		student.setSchool(student.getSchool());

		subject.setCd(subject.getCd());
		subject.setName(sub);

		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		List<TestListSubject> sublist = testSubDao.filter(student);


		//レスポンス値をセット６
		request.setAttribute("sublist", sublist);
		request.setAttribute("list", list);


		//JSPへフォワード７
		request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);


	}

}

