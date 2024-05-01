package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		//student型の全データを取得
		boolean isAttend = true;
		StudentDao studentDao = new StudentDao();
		List<Student> students = studentDao.filter(teacher.getSchool(), isAttend);
		//その後番号を抽出してreq.getParameterで中身に入ったものだけ更新
		for(int i=0; i<students.size(); i++){
			System.out.println(req.getParameter("point_"+students.get(i).getNo()));
		}
	}

}
