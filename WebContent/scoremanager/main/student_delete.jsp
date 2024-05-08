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
		<form action="StudentDeleteExecute.action" method="post">
			<!--サーブレット側に送るデータを入力する-->
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報削除</h2>
			<div class="col-4">
				入学年度<br>
				${entyear}<br><br>
				学生番号<br>
				${no}<br><br>
				<input type="hidden" name="no" value="${no}">
				氏名<br>
				${name}<br><br>
				クラス<br>
				${class_num}<br><br>
				この学生を削除します。

			</div>
			<!--削除ボタン-->
			<button style="background-color:blue;border-color:blue;color:white" type="submit" name="end">削除する</button>
		</form>
		<br>
		<a href = "StudentList.action">戻る</a>
	</c:param>
</c:import>