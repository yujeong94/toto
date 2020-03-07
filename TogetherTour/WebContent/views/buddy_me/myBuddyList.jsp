<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,buddy_me.model.vo.Buddy"%>
<% 
	ArrayList<Buddy> list = (ArrayList<Buddy>)request.getAttribute("mybuddyList");
%>
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
td{border-bottom:1px solid lightgray; text-align:center;}
	.gradeBtn{color:#FF8000; font-weight:bold; cursor:pointer;}

</style>
</head>
<body>
<div class="wrapper">
	<%@ include file="../common/header.jsp" %>
	<div class=contents>
	<h2 align=center><span>나의 동행자 리스트</span></h2>
	<form method=post id=buddyListForm>
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
			<% if(list.isEmpty()) { %>
			<tr>
				<td colspan=7>조회된 리스트가 없습니다.</td>
			</tr>
			<% } else { %>
				<% for(Buddy b : list) { %>
					<% if(!loginUser.getmId().equals(b.getmId())) { %>
						<tr>
							<td><%= b.getName() %><input type=hidden value='<%= b.getmId() %>' class=buddyId></td>
							<td class=nick><%= b.getNick() %></td>
							<td><%= b.getCountry() %></td>
							<td><%= b.getCity() %></td>
							<td><%= b.getStart_date() %> - <%= b.getEnd_date() %></td>
							<td><%= b.getHeadCnt() %></td>
							<td class=gradeBtn>평점주기</td>
						</tr>
					<% } %>
				<% } %>
			<% } %>
		</table>
	</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
<script>
	
	$('.gradeBtn').click(function(){
		var gradeMid = $(this).parent().children().children('.buddyId').val();
		var gradeNick = $(this).parent().children('.nick').text();
		window.open("views/buddy_me/buddyGrade.jsp?gradeNick="+gradeNick+"&gradeMid="+gradeMid,"buddyGradeFrom","width=700px, height=800px");
	});

</script>
</body>	
</html>