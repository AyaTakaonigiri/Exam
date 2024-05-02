<%-- 教員一覧JSP --%>
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">教員管理</h2>
			<div class="my-2 text-end px-4"></div>

			<%-- 教員一覧をテーブル表示 --%>
			<c:choose>
				<c:when test="${teachers.size()>0}">
					<table class="table table-hover">
						<tr>
							<th>教員ID</th>
							<th>教員名</th>
							<th></th>
						</tr>
						<c:forEach var="teacher" items="${teachers}">
							<tr>
								<td>${teacher.id}</td>
								<td>${teacher.name}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<%-- 教員情報がない場合 --%>
				<c:otherwise>
					<table class="table table-hover">
						<tr>
							<th>教員ID</th>
							<th>教員名</th>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>
