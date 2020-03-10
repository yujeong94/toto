<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, buddy.model.vo.*"%>
<%
	ArrayList<buddyBoard> list = (ArrayList<buddyBoard>)request.getAttribute("list");
	PageInfo pi = (PageInfo) request.getAttribute("pi");
	
	 
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage(); // 현재페이지 
	int maxPage = pi.getMaxPage(); //
	int startPage = pi.getStartPage(); // 처음페이지
	int endPage = pi.getEndPage(); // 마지막페이지
	
	if(!list.isEmpty()){
		listCount = pi.getListCount();
		currentPage = pi.getCurrentPage();
		maxPage = pi.getMaxPage();
		startPage = pi.getStartPage();
		endPage = pi.getEndPage();
	}
 
	// 검색엔진
	String search = (String)request.getAttribute("search");
	String menu = (String)request.getAttribute("menu");
	String content = (String)request.getAttribute("content"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행자 리스트 | 투투</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common_sub.css">

<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<style>

#listBtn {padding : 9px 20px; border : none; background: #999999;
		  color: #fff; box-sizing: border-box; display: inline-block;
		  align-items: flex-start; letter-spacing: normal; word-spacing: normal;
		  text-rendering: auto; cursor: pointer; vertical-align: middle;
		  font: inherit; margin-top:20px; float:right;}
		  
table {border-top: 1px solid; border-bottom: 1px solid; margin-bottom:50px;}
	   
th {border-bottom: 1px solid; height:30px;}
	
td {text-align:center; cursor:default;}

.detailBtn {cursor:pointer;}

.profileBtn {cursor:pointer;}

#checkArea{height:100px;}

#line{margin-bottom:100px;}
	
label{vertical-align:middle;}
	
#priceLabel{margin-left:30px; margin-right:10px;}	    



</style>

</head>
<body> 
<div class="wrqpper">
	<%@ include file="../common/header.jsp"%>	
	<div class="contents">
		<div id="BuddyList">
			<h2><span>동행자 리스트</span></h2>
		</div>

		<form action="<%= request.getContextPath() %>/search.buddy" method="post" id ="searchForm">
			<div class='searchArea' align='left'>
				<table>
					<tr>
						<td>
							<select name ="menu" id="menu">
								<option value ="TITLE">제목</option>
								<option value ="NICK">작성자</option>
								<option value ="LOCATION">여행지</option>
							</select>
							<input type="text" placeholder="검색어를 입력해주세요." name = "content" id="content">
							<input type="submit" id="search" value= "검색">
						</td>
					</tr>
				</table>
			</div>
		</form>
		<table id="listArea">
			<tr>
				<th>No.</th>
				<th>제목</th>
				<th>국가</th>
				<th>도시</th>
				<th>동행시작일</th>
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
				<td class="detailBtn"><%=b.getTitle()%></td>
				<td><%=b.getCountry()%></td>
				<td><%=b.getCity()%></td>
				<td><%=b.getStart_date()%></td>
				<td><%=b.getHead_cnt()%></td>
				<td class="profileBtn"><%=b.getNick()%></td>
				<td><%=b.getBcount()%></td>
				<td><%=b.getCreate_date()%></td>
			</tr>
			
			<%
				}
				}
			%>
		</table>
		
		<!-- 페이징 -->
		<div class='pagingArea' align="center">
			<% if(!list.isEmpty()){ %>
			<!-- 맨 처음으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.buddy?currentPage=1'" id="firstBtn" class="icon-fast-backward"></button>
	
			<!-- 이전 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.buddy?currentPage=<%= currentPage-1 %>'" id="beforeBtn" class="icon-to-start"></button>
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
			<button onclick="location.href='<%= request.getContextPath() %>/list.buddy?currentPage=<%= currentPage + 1 %>'" id="afterBtn" class="icon-to-end"></button>
			<script>
				if(<%= currentPage %> >= <%= maxPage %>){
					var after = $("#afterBtn");
					after.attr('disabled', 'true');
				}
			</script>			
			
			<!-- 맨 끝으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.buddy?currentPage=<%= maxPage %>'" id="lastBtn" class="icon-fast-forward"></button>
			<% } %>
		
		</div>
		
		<br>
		<div class='insertBtn' align='center'>
			<% if(loginUser != null){ %> 
			<button onclick='location.href="views/buddy/buddyInsertForm.jsp"'>동행 등록하기</button>
			<% } %> 
		</div>
		
			
	</div>
		<script>
			$(function(){
				$('#listArea td').mouseenter(function(){
					$(this).parent().css({'background':'#F6E3CE', 'cursor':'pointer'});
						}).mouseout(function(){
								$(this).parent().css('background', 'none');
							});
				
				$('.detailBtn').click(function(){
					var bnum = $(this).parent().children().children('input').val();
						
						// 로그인 한 사람만 상세보기 이용할 수 있게 하기
						<% if(loginUser != null){ %>
							location.href='<%= request.getContextPath() %>/detail.buddy?bnum=' + bnum;
						<% } else{ %>
							alert('회원만 이용할 수 있는 서비스입니다.');
							location.href="<%=request.getContextPath()%>/views/member/loginView.jsp" ;
						<% } %>  
						
					});

				// 닉네임 클릭시 프로필보기
				$('.profileBtn').click(function(){
					var userNick = $(this).text();
						window.open('views/myPage/memberProfile.jsp?userNick='+userNick,'profileForm','width=500, height=700');
				});	
			});
		</script> 
	
		<%@ include file="../common/footer.jsp"%>
	</div>

</body>
</html>