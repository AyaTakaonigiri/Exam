<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="content">
		<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
		<p style="background-color: #7ebea5; padding: 8px; width: 100%; text-align: center;">変更が完了しました</p>
		<a href="SubjectList.action">科目一覧</a>
	</c:param>
</c:import>