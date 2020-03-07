<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,buddy_me.model.vo.Report"%>
<%
	ArrayList<Report> list = (ArrayList<Report>)request.getAttribute("report");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고자 리스트 | ToToAdmin</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<style>
th,td{border-top:1px solid; border-bottom:1px solid;}
td{border-bottom:1px solid lightgray; text-align:center;}
	.deleteBtn{color:#FF8000; font-weight:bold; cursor:pointer;}

</style>
</head>
<body>
<div class="wrapper">
	<%@ include file="../common/header.jsp" %>
	<div class=contents>
	<h2 align=center><span>나의 동행자 리스트</span></h2>
	<form method=post id=reportListForm>
		<table>
			<tr>
				<th>No</th>
				<th>신고된 회원아이디</th>
				<th>신고한 회원아이디</th>
				<th>신고사유번호</th>
				<th>신고내용</th>
				<th>처리상태</th>
				<td></td>
			</tr>
			<% if(list.isEmpty()) { %>
			<tr>
				<td colspan=6>조회된 리스트가 없습니다.</td>
			</tr>
			<% } else { %>
				<% for(Report r : list) { %>
						<tr>
							<td>r.getRkey()</td>
							<td>r.getRmid()</td>
							<td>r.getMid()</td>
							<td>r.getRsNum()</td>
							<td>r.getReason()</td>
							<td>r.getStatus()</td>
							<td class=deleteBtn>강제탈퇴</td>
						</tr>
				<% } %>
			<% } %>
		</table>
	</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
<script>
	
	$('.deleteBtn').click(function(){
		confirm("정말 탈퇴시키겠습니까?");
	});

</script>
</body>	
</html>