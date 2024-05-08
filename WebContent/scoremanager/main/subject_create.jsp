<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<!--選択された情報をSubjectCreateExecuteActionに送る-->
		<form action="SubjectCreateExecute.action" method="post">
			<section class="me-4">
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
				<!--サーブレット側に送るデータを入力する-->
				<div class="col-4">
					<p>科目コード</p>
					<input type="text" name="cd" placeholder="科目コードを入力してください" required><br>
					<div style="color: #ffd700;">
						<c:if test="${not empty cderrors}">
							<c:forEach var="cdE" items="${cderrors}">${cdE}</c:forEach>
						</c:if>
					</div>
					<br>
				</div>
				<div class="col-4">
					<p>科目名</p>
					<input type="text" name="name" placeholder="科目名を入力してください" required><br>
					<div style="color: #ffd700;">
						<c:if test="${not empty nameerrors}">
							<c:forEach var="nameE" items="${nameerrors}">${nameE}</c:forEach>
						</c:if>
					</div>
					<br> <br>
				</div>

				<!--登録ボタン-->
				<button
					style="background-color: #4169e1; border-color: gainsboro; color: white"
					type="submit">登録</button>
			</section>
		</form>
		<br>
		<a href="SubjectList.action">戻る</a>
	</c:param>
</c:import>