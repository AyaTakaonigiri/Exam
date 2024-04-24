package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		String cd = "";
		String name = "";
		School school = new School();
		Subject subject = new Subject();
		SubjectDao subDao = new SubjectDao();

//		List<Subject> subjects = new ArrayList<>();
		List<String> cdErrors = new ArrayList<>();//エラーメッセージ
		List<String> nameErrors = new ArrayList<>();//エラーメッセージ

		//リクエストパラメータ―の取得 2;
		cd = request.getParameter("cd");//科目コード
		name = request.getParameter("name");//科目名
		school = teacher.getSchool();//学校コード

		if(cd != null && name != null) {
			 // 科目コードが3文字でない場合
            if (cd.length() != 3) {
                cdErrors.add("科目コードは3文字で入力してください");
                request.setAttribute("cderrors",cdErrors);
                request.getRequestDispatcher("SubjectCreate.action").forward(request, response);
                return; // 登録を中断
            }
			//同じ科目コードが存在する場合
			Subject findSub = subDao.get(cd, school);
			if(findSub != null){
				cdErrors.add("科目コードが重複しています");
				request.setAttribute("cderrors", cdErrors);
                request.getRequestDispatcher("SubjectCreate.action").forward(request, response);
                return; //登録を中断
			}
			subject.setCd(cd);//科目コード
			subject.setName(name);//科目名
			subject.setSchool(school);//学校コード
			subDao.save(subject);
		}
		else if(cd == null && name != null) { //科目コードが指定されていない場合
			cdErrors.add("科目コードを指定してください");
			request.setAttribute("cderrors", cdErrors);
			request.getRequestDispatcher("SubjectCreate.action").forward(request, response);
		}
		else if(cd != null && name == null){ //科目名が指定されていない場合
			nameErrors.add("科目名を指定してください");
			request.setAttribute("nameerrors", nameErrors);
			request.getRequestDispatcher("SubjectCreate.action").forward(request, response);
		}
		else {
			cdErrors.add("科目コードを指定してください");
			nameErrors.add("科目名を指定してください");
			request.setAttribute("cderrors", cdErrors);
			request.setAttribute("nameerrors", nameErrors);
			request.getRequestDispatcher("SubjectCreate.action").forward(request, response);
		}

		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//subjects = subDao.save(subject);

		//レスポンス値をセット 6
		//なし
		//フォワード 7
		//JSPへフォワード 7
		request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);

	}

}