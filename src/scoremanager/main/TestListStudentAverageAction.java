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
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentAverageAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String no = "";
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subDao = new SubjectDao();
        List<Subject> subjects = null;
        Student student = new Student();
        TestListStudentDao testStuDao = new TestListStudentDao();
        StudentDao sDao = new StudentDao();
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータの取得
        no = request.getParameter("f4");

        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
        List<String> clist = cNumDao.filter(teacher.getSchool());

        // ログインユーザーの学校コードを基に科目の一覧を取得
        subjects = subDao.filter(teacher.getSchool());

        // リストを初期化
        List<Integer> entYearSet = new ArrayList<>();
        // 10年前から1年後まで年をリストに追加
        for (int i = year - 10; i < year + 11; i++) {
            entYearSet.add(i);
        }
        // studentをセット
        student.setNo(no);

        // 各科目ごとの平均点を格納するマップを初期化
        Map<String, Double> averageMap = new HashMap<>();

        // 各科目ごとにテストデータを取得し、平均点を計算
        for (Subject subject : subjects) {
            List<TestListStudent> subjectTests = testStuDao.filterBySubject(student, subject.getCd());

            // テストスコアの合計とテスト数を初期化
            double totalScore = 0;
            int testCount = 0;

            // 合計点を計算
            for (TestListStudent test : subjectTests) {
                totalScore += test.getPoint();
                testCount++;
            }

            // 科目の平均点を計算
            double averageScore = testCount > 0 ? totalScore / testCount : 0;
            averageMap.put(subject.getCd(), averageScore);
        }

        // 平均点をリクエスト属性に設定
        request.setAttribute("averageMap", averageMap);

        // 他の属性を設定
        request.setAttribute("stulist", testStuDao.filter(student));
        request.setAttribute("student", sDao.get(no));
        request.setAttribute("f4", no);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", clist);
        request.setAttribute("subject_set", subjects);

        // JSPへフォワード
        request.getRequestDispatcher("test_list_student_average.jsp").forward(request, response);
    }
}
