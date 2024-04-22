package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String no = req.getParameter("no");//学生番号
		StudentDao sDao = new StudentDao();
		ClassNumDao cnDao = new ClassNumDao();
		Student student = sDao.get(no);
		student.setEntYear(student.getEntYear());//入学年度
		student.setNo(no);//学生番号
		student.setName(student.getName());//氏名
		student.setClassNum(student.getClassNum());//クラス
		List<String> classlist = cnDao.filter(student.getSchool());//学校を指定してクラスを全取得
		List<Student> list = new ArrayList<Student>();
		Student p=new Student();
		p.setNo(student.getNo());
		p.setNo(student.getName());
		p.setEntYear(student.getEntYear());
		p.setClassNum(student.getClassNum());
		p.setAttend(student.isAttend());
		p.setSchool(student.getSchool());
		p.setAuthenticated(student.isAuthenticated());
		list.add(p);


		req.setAttribute("entyear", student.getEntYear());
		req.setAttribute("no", student.getNo());
		req.setAttribute("name", student.getName());
		req.setAttribute("num", classlist);
		req.setAttribute("attend", student.isAttend());
		req.setAttribute("list", list);
		//JSPへフォワード 7
		req.getRequestDispatcher("student_update.jsp").forward(req, res);
	}
}
