<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="content">
		<!--選択された情報をSubjectUpdateExecuteActionに送る-->
		<section class="me-4">
			<form action="SubjectUpdateExecute.action" method="post">
				<!--サーブレット側に送るデータを入力する-->
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
				<div class="col-4">
					<p>科目コード</p>
					${code} <input type="hidden" name="cd" value="${code}">
				</div>
				<!-- 変更中に削除された場合のエラーメッセージ -->
				<div style="color: #ffd700;">
					<c:if test="${not empty error}">${error}</c:if>
				</div>
				<br>
				<div class="col-4">
					<p>科目名</p>
					<input type="text" name="name" value="${name}" required>
				</div>
				<br>
				<!--変更ボタン-->
				<button
					style="background-color: #4169e1; border-color: gainsboro; color: white"
					type="submit">変更</button>
				<p></p>
				<a href="SubjectList.action">戻る</a>
			</form>
		</section>
	</c:param>
</c:import>