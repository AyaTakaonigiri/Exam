<%-- 学生平均参照JSP --%>
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績一覧（学生）</h2>
		<div
				style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333; border-radius: 10px; border-color: #696969;">
				<!-- 検索フォーム、 -->
				<form action="TestListSubjectExecute.action" method="get">
					<div class="row vorder mx-3 mb-3 py-2 align-itemscenter rounded"
						id="filter">
						<!-- ここから科目別参照フォーム -->
						<div class="col-md-3 col-sm-2">
							<p>科目情報</p>
						</div>
						<div class="col-md-2 col-sm-3">
							<label class="form-label" for="test-f1-select">入学年度</label> <select
								class="form-select" id="test-f1-select" name="f1">
								<option value="0">----</option>
								<c:forEach var="year" items="${ent_year_set}">
									<!-- 現在のyearと選択されていたf1が一致していた場合selectedを記述 -->
									<option value="${year}"
										<c:if test="${year==f1}">selected</c:if>>${year}</option>
								</c:forEach>
							</select>
						</div>

						<div class="col-md-2 col-sm-3">
							<label class="form-label" for="test-f2-select">クラス</label> <select
								class="form-select" id="test-f2-select" name="f2">
								<option value="0">----</option>
								<c:forEach var="num" items="${class_num_set}">
									<%-- 現在のnumと選択されていたf2が一致していた場合selectedを記述 --%>
									<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
								</c:forEach>
							</select>
						</div>

						<div class="col-md-3 col-sm-3">
							<label class="form-label" for="test-f3-select">科目</label> <select
								class="form-select" id="test-f3-select" name="f3">
								<option value="0">----</option>
								<c:forEach var="subject" items="${subject_set}">
									<%-- 現在のsubjectと選択されていたf3が一致していた場合selectedを記述 --%>
									<option value="${subject.name}"
										<c:if test="${subject.name==f3}">selected</c:if>>${subject.name}</option>
								</c:forEach>
							</select>
						</div>
						<br>
						<br class="br-sp">
						<div class="col-md-2 col-sm-2 text-center">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
						<br>
						<!-- 条件が三つ指定されてない場合のエラーメッセージ -->
						<div style="color: #ffd700;">
							<c:if test="${not empty error}">${error}</c:if>
						</div>
					</div>
				</form>
				<!-- ここまで科目別参照フォーム -->

				<div style="border: 1px solid; border-color: #c0c0c0;"></div>

				<!-- 検索フォーム、 -->
				<form action="TestListStudentExecute.action" method="get">
					<div class="row vorder mx-3 mb-3 py-2 align-itemscenter rounded"
						id="filter">
						<!-- ここから学生番号別参照フォーム -->
						<div class="col-md-3 col-sm-3">
							<p>学生情報</p>
						</div>
						<div class="col-md-5 col-sm-5">
							<p>学生番号</p>
							<input type="text" name="f4" value="${f4}">
						</div>
						<br>
						<br class="br-sp">
						<div class="col-md-2 col-sm-2 text-center">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
						<!-- ここまで学生番号別参照フォーム -->
					</div>
				</form>
			</div>

			<div>氏名:${student.name}(${student.no})</div>
			<div class="text-align: left;">
				<a href="TestListStudentExecute.action?f4=${f4}">成績一覧へ</a>
			</div>
			<c:choose>
				<c:when test="${stulist.size()>0}">
					<table class="table table-hover">
						<tr>
							<th>科目名</th>
							<th>科目コード</th>
							<th>平均</th>
						</tr>
						<c:forEach var="subject" items="${subject_set}">
							<c:set var="average" value="${averageMap[subject.cd]}" />
							<tr>
								<td>${subject.name}</td>
								<td>${subject.cd}</td>
								<td><c:out value="${average}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>成績情報が存在しませんでした</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>