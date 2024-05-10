<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--taglibディレクティブの記述-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .box {
        border: whitesmoke 3px solid; /*境界線の指定*/

    }
    .box p {
        margin: 0;
        padding: 0;
    }
</style>

<c:import url="/common/base.jsp">



	<c:param name="title">
		得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>






	<c:param name="content">
		<div class="box">
			<div style="text-align:center">
				<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">ログイン</h2>
				<c:if test="${not empty errors}">
		            <c:forEach var="error" items="${errors}">
		                ${error}
		            </c:forEach>
			    </c:if>
			</div>
			<!--選択された情報をloginExcuteActionに送る-->
			<form action="LoginExecute.action" method="post">
				<!--サーブレット側に送るデータを入力する-->
				<div style="text-align:center">
					<div style="text-align:center">
						ID
					</div>
					<input type="text" name="id" placeholder="半角でご入力ください" required><br>
					<div style="text-align:center">
						PASSWORD
					</div>
					<input type="password" id="password" name="password" placeholder="20文字以内の半角英数字でご入力ください" required><br>
					<input type="checkbox" id="showPassword"> パスワードを表示する
				</div>
				<br>
				<!--ログインボタン-->
				<div style="text-align:center">
					<button style="background-color:blue;border-color:blue;color:white" type="submit" name="login">ログイン</button>
				</div>
			</form>
			<script>
		        const passwordInput = document.getElementById('password');
		        const showPasswordCheckbox = document.getElementById('showPassword');

		        showPasswordCheckbox.addEventListener('change', () => {
		            if (showPasswordCheckbox.checked) {
		                passwordInput.type = 'text';
		            } else {
		                passwordInput.type = 'password';
		            }
		        });
	    	</script>
	    </div>
	</c:param>
</c:import>