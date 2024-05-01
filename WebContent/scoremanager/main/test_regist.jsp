<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="content">
		<form action="TestRegist.action" method="get">
			<div class="row border mx-2 mb-3 py-2 align-items-center rounded" id="filter">
				<div class="col-4">
					<label class="form-label" for="student-f1-select">入学年度</label>
					<select class="form-select" id="student-f1-select" name="f1" required>
						<option value="0">----------</option>
						<c:forEach var="year" items="${year_set}">
							<option value="${year}"<c:if test="${year==f1}">selected</c:if>>${year}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-4">
					<label class="form-label" for="student-f1-select">科目</label>
					<select class="form-select" id="student-f1-select" name="f2" required>
						<option value="0">----------</option>
						<c:forEach var="num" items="${subject_set}">
							<option value="${num.getCd()}"<c:if test="${num.getCd()==f2}">selected</c:if>>${num.getName()}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-4">
					<label class="form-label" for="student-f2-select">クラス</label>
					<select class="form-select" id="student-f2-select" name="f3">
						<option value="0">----------</option>
						<c:forEach var="num" items="${class_set}">
							<option value="${num}"<c:if test="${num==f3}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-4">
					<label class="form-label" for="student-f1-select">回数</label>
					<select class="form-select" id="student-f2-select" name="f4">
						<option value="0">----------</option>
						<c:forEach var="num" items="${count_set}">
							<option value="${num}"<c:if test="${num==f4}">selected</c:if>>${num}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-2 tect-center">
					<button class="btn btn-secondary" id="filter-button">検索</button>
				</div>

				<div class="mt-2 text-warning">${errors.get("f1")}</div>
			</div>
		</form>

		<c:choose>
			<c:when test="${tests.size()>0}">
				<form action="TestRegistExecute.action" method="get">
					<div>科目:${f5}(${f4}回)</div>
					<table class="table table-hover">

						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>

						</tr>

						<c:forEach var="tests" items="${tests}">
							<c:if test="${tests.student().isAttend()}">
								<tr>
									<td>${tests.student().getEntYear()}</td>
									<td>${tests.classNum}</td>
									<td>${tests.student().getNo()}</td>
									<td>${tests.student().getName()}</td>
									<td><input type="text" name="point_${tests.student().getNo()}" value="${tests.point}"></td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
					<div class="col-2 tect-center">
						<button class="btn btn-secondary" id="filter-button">登録して終了</button>
					</div>
				</form>
			</c:when>
		</c:choose>
	</c:param>
</c:import>