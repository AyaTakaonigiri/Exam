<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="content">
		<!--選択された情報をloginに送る-->
		<form action="StudentCreateExecute.action" method="post">
			<!--サーブレット側に送るデータを入力する-->
			<p style="background-color: whitesmoke;"><font size="5">学生情報登録</font></p>
			<div class="col-4">
				<label class="form-label" for="student-f1-select">入学年度</label>
				<select class="form-select" id="student-f1-select" name="ent_year" required>
					<option value="0">----------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<%-- 現在のyearと選択されていたf1が一致していた場合selectedを記述 --%>
						<option value="${year}">${year}</option>
					</c:forEach>
				</select>
			</div>
			<div class="mt-2 text-warning">${errors.get("f1")}</div>

			<p>学生番号<input type="text" name="no" placeholder="学生番号を入力してください" required></p>

			<div class="mt-2 text-warning">${errors.get("f2")}</div>

			<p>氏名<input type="text" name="name" placeholder="氏名を入力してください" required></p>
			<div class="col-4">
				<label class="form-label" for="student-f2-select">クラス</label>
				<select class="form-select" id="student-f2-select" name="class_num">
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}">${num}</option>
					</c:forEach>
				</select>
			</div>
			<br>
			<!--登録ボタン-->
			<button style="background-color:darkgray;border-color:gainsboro;color:white" type="submit" name="end">登録して終了</button>
		</form>
		<br>
		<a href = "StudentList.action">戻る</a>
	</c:param>
</c:import>