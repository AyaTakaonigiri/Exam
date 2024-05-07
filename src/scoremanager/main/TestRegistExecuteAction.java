package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		//student型の全データを取得
		boolean isAttend = true;
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
		List<Student> students = studentDao.filter(teacher.getSchool(), isAttend);
		List<Test> tests = new ArrayList<>();
		//その後番号を抽出してreq.getParameterで中身に入ったものだけ更新

		for(int i=0; i<students.size(); i++){
			if(req.getParameter("point_"+students.get(i).getNo())!=null && req.getParameter("point_"+students.get(i).getNo())!=""){
				//学生番号
				String studentno = req.getParameter("no_"+students.get(i).getNo());
				//点数
				int point = Integer.parseInt(req.getParameter("point_"+students.get(i).getNo()));
				//そのテストの回数
				String num = req.getParameter("num_"+students.get(i).getNo());
				//Subject型が持ってこれないのでコードを持ってくる
				String subjectcd = req.getParameter("subject_"+students.get(i).getNo());
				Subject subject = subjectDao.get(subjectcd, teacher.getSchool());
				//学校コード
				School school = teacher.getSchool();
				//Student型からクラス
				Student student = studentDao.get(studentno);
				Test test = new Test();
				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(Integer.parseInt(num));
				test.setPoint(point);
				test.setClassNum(student.getClassNum());
				tests.add(test);
			}
		}
		//更新する
		TestDao testDao = new TestDao();
		testDao.save(tests);
		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
	}
}
