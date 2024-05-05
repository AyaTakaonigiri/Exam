<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="content">
		<p>成績管理</p>
		<p>登録が完了しました</p>
		<a href="TestRegist.action">戻る</a>
		<a href="TestList.action">成績参照</a>
	</c:param>
</c:import>