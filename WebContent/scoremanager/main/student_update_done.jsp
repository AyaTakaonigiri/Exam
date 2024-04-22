<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="content">
		<p>学生情報変更</p>
		<p>変更が完了しました</p>
		<a href="StudentList.action">学生一覧</a>
	</c:param>
</c:import>