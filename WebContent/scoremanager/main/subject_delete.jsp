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

		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
			<!--選択された情報をSubjectDeleteExecuteActionに送る-->
			<form action="SubjectDeleteExecute.action" method="post">
				<!--削除の確認-->
				<p style="background-color: whitesmoke;">
					<font size="5">学生情報削除</font>
				</p>
				<div class="col-4">
					<label class="form-label">
						<p>「${name}(${cd})」を削除してもよろしいですか</p>
					</label>
				</div>
				<!--削除ボタン-->
				<button
					style="background-color: #dc143c; border-color: #dc143c; color: white"
					type="submit" name="削除">削除</button>
			</form>
			<br>
			<a href="SubjectList.action">戻る</a>
		</section>
	</c:param>
</c:import>