<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, review.model.vo.*, member.model.vo.*" %>

<%
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	Member loginUser = (Member)session.getAttribute("loginUser");
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
	<div class="contents">
		<br>
		<h2>여행 후기 & 팁</h2>
		<hr>
		
		<div class='searchArea' align='right'>
			  <% if(loginUser != null){ %> 
			<button onclick='location.href="views/trip_review/reviewInsertForm.jsp"'>후기 작성하기</button>
			  <% } %>  
		</div>
		
		<table id = "listArea">
			<tr>
				<th width="100px">게시판 번호</th>
				<th width="200px">제목</th>
				<th width="100px">여행지</th>
				<th width="100px">작성자</th>
				<th width="50px">추천수</th>
				<th width="100px">조회수</th>
				<th width="100px">작성날짜</th>
				
			</tr>
			<% if(list.isEmpty()){ %>
			<tr>
				<td colspan="8">조회된 리스트가 없습니다.</td>
			</tr>
			<% } else{
					for(Review r : list) {
			%>
			<tr>
				<td align="center"><%= r.getrNum() %><input type="hidden" value='<%= r.getrNum()%>'></td>
				<td align="center"><%= r.getTitle() %></td>
				<td align="center"><%= r.getLocation() %></td>
				<td align="center"><%= r.getmId() %></td>
				<td align="center"><%= r.getrPoint() %></td>
				<td align="center"><%= r.getrCount() %></td>
				<td align="center"><%= r.getCreateDate() %></td>
			</tr>
			<%		} 
				}
			%>

		</table>
	</div>
	<br><br><br>
	
	<!-- 페이징 -->
		<div class='pagingArea' align='center'>
			<% if(!list.isEmpty()){ %>
			<!-- 맨 처음으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=1'">&lt;&lt;</button>
			
			<!-- 이전 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= currentPage-1 %>'" id="beforeBtn">&lt;</button>
			<script>
				if(<%= currentPage %> <= 1){
					var before = $('#beforeBtn');
					before.attr('disabled', 'true');
				}
			</script>
			
			<!-- 10개의 페이지 목록 -->
			<% for(int p = startPage; p <= endPage; p++){ %>
				<% if(p == currentPage){ %>
					<button id="choosen" disabled><%= p %></button>
				<% } else{ %>
					<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= p %>'"><%= p %></button>
				<% } %>
			<% } %>
			
			<!-- 다음 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= currentPage + 1 %>'" id="afterBtn">&gt;</button>
			
			<script>
				if(<%= currentPage %> >= <%= maxPage %>){
					var after = $("#afterBtn");
					after.attr('disabled', 'true');
				}
			</script>
			
			<!-- 맨 끝으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= maxPage %>'">&gt;&gt;</button>
			
			<% } %>

		</div>
		
	<script>
	$(function(){
		$('#listArea td').mouseenter(function(){
			$(this).parent().css({'background':'darkgray', 'cursor':'pointer'});
		}).mouseout(function(){
			$(this).parent().css('background', 'none');
		}).click(function(){
			var rnum = $(this).parent().children().children('input').val();
			
			// 로그인 한 사람만 상세보기 이용할 수 있게하기
			<% if(loginUser != null){%>
				location.href='<%= request.getContextPath() %>/detail.rv?rnum=' + rnum;
			<% } else{ %>
				alert('회원만 이용할 수 있는 서비스입니다.');
			<% } %>
		});
	});
	</script>	
		
		
	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>