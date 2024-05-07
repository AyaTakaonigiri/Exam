package bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
	private String no;
	private String name;
	private int entYear;
	private String classNum;
	private boolean isAttend;
	private School school;
	private boolean isAuthenticated;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public boolean isAttend() {
		return isAttend;
	}
	public void setAttend(boolean isAttend) {
		this.isAttend = isAttend;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

	public int getSchoolYear() {
//		LocalDateインスタンスを取得
		LocalDate todaysDate = LocalDate.now();
//		現在の月と年を取得
		int month = todaysDate.getMonthValue();
		int year = todaysDate.getYear();
//		現在の月が１月から３月までの場合
		if (1 <= month && month <= 3) {
//			現在の年を1減らす
			year--;
		}
//		現在の年と入学年度から算出した現在の学年を返却
		return year - entYear + 1;
	}
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
}
