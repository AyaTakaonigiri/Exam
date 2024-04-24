<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="content">
		<form action="StudentList.action" method="get">
			<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
				<div class="col-4">
					<label class="form-label" for="student-f1-select">入学年度</label>
					<select class="form-select" id="student-f1-select" name="ent_year" required>
						<option value="0">----------</option>
						<c:forEach var="year" items="${year_set}">
							<option value="${year}">${year}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mt-2 text-warning">${errors.get("f1")}</div>

				<div class="col-4">
					<label class="form-label" for="student-f1-select">科目</label>
					<select class="form-select" id="student-f1-select" name="subject" required>
						<option value="0">----------</option>
						<c:forEach var="num" items="${subject_set}">
							<option value="${num.getName()}">${num.getName()}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-4">
					<label class="form-label" for="student-f2-select">クラス</label>
					<select class="form-select" id="student-f2-select" name="class_num">
						<option value="0">----------</option>
						<c:forEach var="num" items="${class_set}">
							<option value="${num}">${num}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-4">
					<label class="form-label" for="student-f1-select">回数</label>
					<select class="form-select" id="student-f2-select" name="num">
						<option value="0">----------</option>
						<c:forEach var="num" items="${count_set}">
							<option value="${num}">${num}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-2 tect-center">
					<button class="btn btn-secondary" id="filter-button">絞込み</button>
				</div>

				<div class="mt-2 text-warning">${errors.get("f1")}</div>
			</div>
		</form>
		<c:choose>
			<c:when test="${subject_set.size()>0}">
				<div>検索結果:${subject_set.size()}件</div>
				<table class="table table-hover">
					<tr>
						<th>入学年度</th>
						<th>学生番号</th>
						<th>氏名</th>
						<th>クラス</th>
						<th class="text-center">在学中</th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach var="student" items="${students}">
						<tr>
							<td>${student.entYear}</td>
							<td>${student.no}</td>
							<td>${student.name}</td>
							<td>${student.classNum}</td>
							<td class="text-center">
								<%-- 在学フラグが立っている場合「〇」それ以外は「✕」を表示 --%>
								<c:choose>

									<c:when test="${student.isAttend()}">
										〇
									</c:when>
									<c:otherwise>
										✕
									</c:otherwise>
								</c:choose>
							</td>
								<td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
								<td><a href="StudentDelete.action?no=${student.no}">削除</a></td>
							</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<div>学生情報が存在しませんでした</div>
			</c:otherwise>
		</c:choose>







		<a href = "StudentList.action">戻る</a>
	</c:param>
</c:import>