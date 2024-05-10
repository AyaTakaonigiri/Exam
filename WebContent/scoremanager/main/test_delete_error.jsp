<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	<c:param name="content">
		<p>点数が未入力かデータベースに情報がありません。</p>
		<a href="TestRegist.action">成績登録</a>
		<a href="TestList.action">成績参照</a>
	</c:param>
</c:import>