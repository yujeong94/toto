<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 동행자 리스트 | TogetherTour</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<style>
th,td{border-top:1px solid; border-bottom:1px solid;}
td{border-bottom:1px solid lightgray;}

</style>
</head>
<body>
<div class="wrapper">
	<%@ include file="../common/header.jsp" %>
	<div class=contents>
	<h2 align=center><span>나의 동행자 리스트</span></h2>
	<form action='' method=post id=buddyListForm>
		<table>
			<tr>
				<th>회원명</th>
				<th>닉네임</th>
				<th>국가</th>
				<th>도시</th>
				<th>여행기간</th>
				<th width=100px;>동행인원</th>
				<th width=100px;></th>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>버튼넣을자리</td>
			</tr>
		</table>
	</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
</body>	
</html>