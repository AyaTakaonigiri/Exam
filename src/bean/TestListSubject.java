package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {

	//変数の設定
	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	 private Map<Integer, Integer> points = new HashMap<>();

	//ゲッター・セッター
	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Map<Integer, Integer> getPoints() {
		return points;
	}

	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}

	//pointsから得点を取得するゲッター
	public String getPoint() {
		String point = "";
		for (int key : points.keySet()) {
			 point = Integer.toString(key);
		}
		return point;
	}

	//回数と得点をpointsに追加するセッター
	public void putPoint(int key, int value) {
		 points.put(key, value);
	}

}