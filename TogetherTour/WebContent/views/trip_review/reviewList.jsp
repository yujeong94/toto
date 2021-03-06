<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, review.model.vo.*, member.model.vo.*, follow.model.vo.*" %>

<%
	ArrayList<Review> list = (ArrayList<Review>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	/* Member loginUser = (Member)session.getAttribute("loginUser"); */
	
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	String strKind=null;
	
	String search = (String)request.getAttribute("search");
	String menu = (String)request.getAttribute("menu");
	String content = (String)request.getAttribute("content");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
	td{text-align:center; cursor:default;}
	.detailBtn,.profileBtn{cursor:pointer;}
	th{border-bottom: 1px solid; height:30px;}
	/* #search{background:black; color:white;} */
	.titleBar{background:#F2F2F2;}
</style>
</head>
<body>


	<%@ include file="../common/header.jsp" %>
	
	<div class="contents">
		<br>
		<h2>여행 후기 & 팁</h2>
		
		<form action="<%= request.getContextPath() %>/search.rv" method="post" id ="searchForm">
		<hr>
			<div class='searchArea' align='left'>
				<table>
					<tr>
						<td>
						
							<select name ="menu" id="menu">
								<option value ="TITLE">제목</option>
								<option value ="NICK">작성자</option>
								<option value ="LOCATION">여행지</option>
							</select>
							<input type="text" placeholder="검색어를 입력해주세요." name = "content" id="content" size="70">
							<input type="submit" id="search" value= "검색">
						
						</td>
						
					</tr>
				</table>
			</div>
		</form>
	
		
		<hr>
		
		
		
		
		
		<table id = "listArea">
			<tr class="titleBar">
				<th width="100px">No</th>
				<th width="200px">제목</th>
				<!-- <th width="100px">여행지</th> -->
				<th width="50px">분류</th>
				<th width="50px"> 국가</th>
				<th width="50px">지역</th>
				
				
				<th width="100px">작성자</th>
				<th width="50px">추천수</th>
				<th width="100px">조회수</th>
				<th width="100px">작성날짜</th>
			</tr>
			<% if(list.isEmpty()){ %>
			<tr>
				<td colspan="9" align="center">조회된 후기 게시글이 없습니다.</td>
			</tr>
			<% } else{
					for(Review r : list) {
						if(r.getKind() == 1){
							strKind = "국내";
						} else {
							strKind = "해외";
						}	
			%>
			<tr>
				
				<td align="center"><%= r.getrNum() %><input type="hidden" value='<%= r.getrNum()%>'></td>
				<td align="center" class="detailBtn"><%= r.getTitle() %></td>
				<td align="center"><%= strKind %></td>
				<td align="center"><%= r.getCountry() %></td>
				<td align="center"><%= r.getCity() %></td>
				<%-- <td align="center"><%= r.getLocation() %></td> --%>
				<td align="center" class="profileBtn"><%= r.getmId() %></td>
				<td align="center"><%= r.getrPoint() %></td>
				<td align="center"><%= r.getrCount() %></td>
				<td align="center"><%= r.getCreateDate() %></td>
			</tr>
			<%		} 
				}
			%>

		</table>
		
		<div class='insertBtn' align='right'>
		<% if(loginUser != null && loginUser.getmKind() != 2){ %> 
		<button onclick='location.href="views/trip_review/reviewInsertForm.jsp"'>후기 작성하기</button>
		<% } %> 
		</div>
	</div>
	<br><br><br>
	
	<!-- 페이징 -->
		<div class='pagingArea' align='center'>
			<% if (search != null) {%>
				<% if(!list.isEmpty() && maxPage !=1){ %>
				<!-- 맨 처음으로 -->
				<button onclick="location.href='<%= request.getContextPath() %>/search.rv?currentPage=1&menu=<%= menu%>&content=<%=content%>'" id="firstBtn" class="icon-fast-backward">&lt;&lt;</button>
				
				<!-- 이전 페이지로 -->
				<button onclick="location.href='<%= request.getContextPath() %>/search.rv?currentPage=<%= currentPage-1 %>&menu=<%= menu%>&content=<%=content%>'" id="beforeBtn" class="icon-to-start">&lt;</button>
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
						<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/search.rv?currentPage=<%= p %>&menu=<%= menu%>&content=<%=content%>'" ><%= p %></button>
					<% } %>
				<% } %>
				
				<!-- 다음 페이지로 -->
				<button onclick="location.href='<%= request.getContextPath() %>/search.rv?currentPage=<%= currentPage + 1 %>&menu=<%= menu%>&content=<%=content%>'"  id="afterBtn" class="icon-to-end">&gt;</button>
				
				<script>
					if(<%= currentPage %> >= <%= maxPage %>){
						var after = $("#afterBtn");
						after.attr('disabled', 'true');
					}
				</script>
				
				<!-- 맨 끝으로 -->
				<button onclick="location.href='<%= request.getContextPath() %>/search.rv?currentPage=<%= maxPage %>&menu=<%= menu%>&content=<%=content%>'" id="lastBtn" class="icon-fast-forward">&gt;&gt;</button>
				
				<% } %>
				
			<%} else{%>
			
				<% if(!list.isEmpty() && maxPage !=1){ %>
					<!-- 맨 처음으로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=1'" id="firstBtn" class="icon-fast-backward">&lt;&lt;</button>
					
					<!-- 이전 페이지로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= currentPage-1 %>'" id="beforeBtn" class="icon-to-start">&lt;</button>
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
					<button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= currentPage + 1 %>'" id="afterBtn" class="icon-to-end">&gt;</button>
					
					<script>
						if(<%= currentPage %> >= <%= maxPage %>){
							var after = $("#afterBtn");
							after.attr('disabled', 'true');
						}
					</script>
					
					<!-- 맨 끝으로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.rv?currentPage=<%= maxPage %>'" id="lastBtn" class="icon-fast-forward">&gt;&gt;</button>
					
					
				<% } %>
				
			<%} %>
		</div>
		
		
		
		
	<script>
	$(function(){
		<% if(!list.isEmpty()){ %>
		$('.detailBtn').mouseenter(function(){
			$(this).parent().css({'background':'#F6E3CE', 'cursor':'pointer'});
		}).mouseout(function(){
			$(this).parent().css('background', 'none');
		}).click(function(){
			var rNum = $(this).parent().children().children('input').val();
			var loginUser = $('#loginUser').val();
			
			// 로그인 한 사람만 상세보기 이용할 수 있게하기
			<% if(loginUser != null){%>
				<%-- location.href='<%= request.getContextPath() %>/detail.rv?rnum=' + rnum +'&loginUser=' +loginUser ; --%>
				location.href='<%= request.getContextPath() %>/detail.rv?rNum=' + rNum;
				
				
			<% } else{ %>
				alert('회원만 이용할 수 있는 서비스입니다.');
			<% } %>
		});
		<%}%>
	});
	
	$('.profileBtn').click(function(){
		var userNick = $(this).text();
		window.open('views/myPage/memberProfile.jsp?userNick='+userNick,'profileForm','width=500, height=700');
	});
	
	
	</script>	
	
		
	<%@ include file="../common/footer.jsp" %>

</body>
</html>