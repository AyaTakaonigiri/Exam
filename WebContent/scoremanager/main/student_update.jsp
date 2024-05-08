<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<!--選択された情報をloginに送る-->
		<form action="StudentUpdateExecute.action" method="post">


			<!--サーブレット側に送るデータを入力する-->
			<p style="background-color: whitesmoke;"><font size="5">学生情報変更</font></p>
			<c:forEach var="s" items="${list}">
				<div class="col-4">
					<p>入学年度</p>
					${entyear}
					<input type="hidden" name="entyear" value="${entyear}">
				</div>
				<p>学生番号</p>
				${no}
				<input type="hidden" name="no" value="${no}">
				<p>氏名</p>
				<input type="text" name="name" value="${name}" required>
				<div class="col-4">
					<label class="form-label" for="student-f2-select">クラス</label>
					<select class="form-select" id="student-f2-select" name="class_num">
						<c:forEach var="num" items="${num}">
							<option value="${num}" ${num eq s.classNum ? 'selected' : ''}>${num}</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div class="col-4">
				在学中
					<input type="checkbox" name="si_attend" value=true
					<c:if test="${s.isAttend()}">checked</c:if>/>
				</div>

				<!--登録ボタン-->
				<button style="background-color:blue;border-color:blue;color:white" type="submit">変更</button>
			</c:forEach>
			<br>
			<a href = "StudentList.action">戻る</a>
		</form>
	</c:param>
</c:import>