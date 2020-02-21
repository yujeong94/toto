<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" import="member.model.vo.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/myPage/myPage.css">
</head>
<body>
	<nav>
		<table class="myPage-menu">
			<tr>
				<td><a href="<%= request.getContextPath() %>/viewSelf.myPage" class="myPage-item">내 정보 보기</a></td>
				<td><a href="" class="myPage-item">함께한 동행자</a></td>
				<td><a href="" class="myPage-item">팔로우한 작성자</a></td>
			</tr>
		</table>
	</nav>
</body>
</html>