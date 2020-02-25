<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, qna.model.vo.*,member.model.vo.* "%>
<%
	ArrayList<Qna> list = (ArrayList<Qna>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
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
<title>Q&amp;A 리스트 | 투투</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/trip/trip_plan_list.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
		<div class="contents">
			<h2><span>Q &amp; A</span></h2>
			
			<div class='insertArea' align='right'>
			  <% if(loginUser != null){ %> 
				<button onclick='location.href="views/qna/QnaInsertForm.jsp"'>질문하기</button>
			  <% } %>  
			</div>
			
			<table id="listArea">
				<tr>	
					<th width="100px">글번호</th>
					<th width="200px">글제목</th>
					<th width="100px">작성자</th>
					<th width="100px">조회수</th>
					<th width="100px">작성일</th>
				</tr>
				<% if(list.isEmpty()){ %>
				<tr>
					<td colspan="5">존재하는 공지사항이 없습니다.</td>
				</tr>
				<% } else {  
					for(Qna q : list) { %>
						
						<tr>
							<td align="center"><%= q.getqNum() %><input type="hidden" value='<%= q.getqNum()%>'></td>
							<td align="center"><%= q.getTitle() %></td>
							<td align="center"><%= q.getmId() %></td>
							<td align="center"><%= q.getqCount() %></td>
							<td align="center"><%= q.getCreateDate() %></td>						
						</tr>
					
					<% } 
						
				} %>
			</table>
		</div>	
			
			<br>
		<!-- 페이징 -->
		<div class='pagingArea' align='center'>	
			<% if(!list.isEmpty()){ %>
					<!-- 맨 처음으로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.sh?currentPage=1'">&lt;&lt;</button>
					
					<!-- 이전 페이지로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.sh?currentPage=<%= currentPage-1 %>'" id="beforeBtn">&lt;</button>
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
							<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.sh?currentPage=<%= p %>'"><%= p %></button>
						<% } %>
					<% } %>
					
					<!-- 다음 페이지로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.sh?currentPage=<%= currentPage + 1 %>'" id="afterBtn">&gt;</button>
					
					<script>
						if(<%= currentPage %> >= <%= maxPage %>){
							var after = $("#afterBtn");
							after.attr('disabled', 'true');
						}
					</script>
					
					<!-- 맨 끝으로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.sh?currentPage=<%= maxPage %>'">&gt;&gt;</button>
					
				<% } %>	
			
		</div>
		<script>
		$(function(){
			$('#listArea td').mouseenter(function(){
				$(this).parent().css({'background':'darkgray', 'cursor':'pointer'});
			}).mouseout(function(){
				$(this).parent().css('background', 'none');
			}).click(function(){
				var qnum = $(this).parent().children().children('input').val();
				var loginUser = $('#loginUser').val();
				
				// 로그인 한 사람만 상세보기 이용할 수 있게하기
				<% if(loginUser != null){%>
					location.href='<%= request.getContextPath() %>/detail.qna?qnum=' + qnum;
					
				<% } else{ %>
					alert('회원만 이용할 수 있는 서비스입니다.');
				<% } %>
			});
		});
		
	</script>	
				
			
			
			
			
		

		<!--S:footer-->
		<%@ include file="../common/footer.jsp" %>
		<!--E:footer-->
</body>
</html>