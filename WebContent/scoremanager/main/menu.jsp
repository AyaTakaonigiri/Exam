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

		<div style="display:flex">
			<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; font-size: 25px; background-color: #bbdbf3;">
				<a href="StudentList.action">学生管理</a>
			</div>

			<div class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; font-size: 20px; background-color: #a1d8e6;">
				<div style="flex-wrap: wrap">
					<p>成績管理</p>
					<p><a href="TestRegist.action">成績登録</a></p>
					<p><a href="TestList.action">成績参照</a></p>
				</div>
			</div>
		</div>
		<br>
		<div style="display: flex">
			<div
				class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; font-size: 25px; background-color: #a2c2e6;">
				<a href="SubjectList.action">科目管理</a>
			</div>

			<div
				class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
				style="height: 10rem; font-size: 25px; background-color: #bbe2f1;">
				<a href="TeacherList.action">教員管理</a>
			</div>
		</div>

	</c:param>
</c:import>