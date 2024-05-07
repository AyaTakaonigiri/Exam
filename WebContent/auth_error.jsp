<%-- アクセス権限無しエラーjsp --%>
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
			<p>${error}</p>
			<a href="Menu.action">メニューに戻る</a>
		</section>
	</c:param>
</c:import>
