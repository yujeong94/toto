<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, buddy.model.vo.*"%>
<%
	ArrayList<buddyBoard> list = (ArrayList<buddyBoard>) request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");

	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage(); // 현재페이지 
	int maxPage = pi.getMaxPage(); //
	int startPage = pi.getStartPage(); // 처음페이지
	int endPage = pi.getEndPage(); // 마지막페이지

	System.out.println(list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행자 리스트 | 투투</title>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/index.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/common_sub.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/buddy/buddyListView.css">

<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>

</head>
<body> 
	<%@ include file="../common/header.jsp"%>
	<br><br><br>
	<hr>
	
	<div class="contents">
		<div id="BuddyList">
			<h2>동행자 리스트</h2>
		</div>
		<div id="atag">
			<a>여행자들의 동행게시글을 조회할 수 있습니다.</a>
		</div>
		<br>
		<form id="search">
			<select name=분류>
				<option>작성자</option>
				<option>국가</option>
				<option>지역</option>
				<option>날짜</option>
				<option>전체</option>
			</select> <input type="text" placeholder="검색어를입력하세요">&nbsp;&nbsp;&nbsp;<input
				type="button" value="조회">
		</form>
		<br>
		<br>
		<table id="listArea">
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>국가</th>
				<th>도시</th>
				<th>여행날짜</th>
				<th>모집인원</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성한 날짜</th>
			</tr>
			<%
				if (list.isEmpty()) {
			%>
			<tr>
				<td colspan="9">조회된 리스트가 없습니다.</td>
			</tr>
			<%
				} else {

					for (buddyBoard b : list) {
			%>
			<tr>
				<td><%=b.getBnum()%><input type="hidden" value='<%= b.getBnum() %>'></td>
				<td><%=b.getTitle()%></td>
				<td><%=b.getCountry()%></td>
				<td><%=b.getCity()%></td>
				<td><%=b.getStart_date()%></td>
				<td><%=b.getHead_cnt()%></td>
				<td><%=b.getnick()%></td>
				<td><%=b.getBcount()%></td>
				<td><%=b.getCreate_date()%></td>
			</tr>
			<%
				}
				}
			%>
		</table>
		<br>
		<br>
		<br>
		<!-- 페이징 -->
		<div class='pagingArea' align="center">
			<% if(!list.isEmpty()){ %>
			<!-- 맨 처음으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.buddy?currentPage=1'">&lt;&lt;</button>
	
			<!-- 이전 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.buddy?currentPage=<%= currentPage-1 %>'" id="beforeBtn">&lt;</button>
			<script>
				if(<%= currentPage %> <= 1){
					var before = $('#beforeBtn');
					before.attr('disabled', 'true');
				}
			</script>
			
			
			<!-- 10개의 페이지 목록 -->
			<% for(int p = startPage; p <= endPage; p++){ %>
				<% if(p == currentPage){%>
					<button id="choosen" disabled><%= p %></button>
				<% } else{ %>
					<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.buddy?currentPage=<%= p %>'"><%= p %></button>
				<% } %>
			<% } %>
			
			<!-- 다음 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.buddy?currentPage=<%= currentPage + 1 %>'" id="afterBtn">&gt;</button>
			<script>
				if(<%= currentPage %> >= <%= maxPage %>){
					var after = $("#afterBtn");
					after.attr('disabled', 'true');
				}
			</script>			
			
			<!-- 맨 끝으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.buddy?currentPage=<%= maxPage %>'">&gt;&gt;</button>
			
				
			<% } %>
			<%-- <div class='searchArea' align='right'>
				<% if(loginUser != null){ %>
				<button onclick='location.href="views/board/boardInsertForm.jsp"'>작성하기</button>
				<% } %>
			</div> --%>
		</div>
	</div>
		
		
		
		<%-- 			<div class='searchArea' align='right'>
				<% if(loginUser != null){ %>
				<button onclick='location.href="views/board/boardInsertForm.jsp"'>작성하기</button>
				<% } %>
			</div>
		--%>
			<script>
				$(function(){
					$('#listArea td').mouseenter(function(){
						$(this).parent().css({'background':'darkgray', 'cursor':'pointer'});
							}).mouseout(function(){
									$(this).parent().css('background', 'none');
								}).click(function(){
									var bnum = $(this).parent().children().children('input').val();
									location.href='<%= request.getContextPath() %>/detail.buddy?bnum=' + bnum;
								// 로그인 한 사람만 상세보기 이용할 수 있게 하기
					<%--		<% if(loginUser != null){ %>
									location.href='<%= request.getContextPath() %>/detail.bo?bid=' + bid;
							<% } else{ %>
									alert('회원만 이용할 수 있는 서비스입니다.');
							<% } %> --%>
							});
				});
		</script> 
		<br>
		<br>
		<br>
		<br>
		<br>
		<%@ include file="../common/footer.jsp"%>
		<br> <br>
	</div>

</body>
</html>