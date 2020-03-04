<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<nav>
		<table class="myPage-menu">
			<tr>
				<td><a href="<%= request.getContextPath() %>/viewSelf.myPage" class="myPage-item">내 정보 보기</a></td>
				<td><a href="<%= request.getContextPath() %>/list.myBuddy" class="myPage-item">함께한 동행자</a></td>
				<td><a id="followList" class="myPage-item">팔로우한 작성자</a></td>
			</tr>
		</table>
	</nav>
	<script>
		function follow() {
			
			
		}
	</script>
</body>
</html>