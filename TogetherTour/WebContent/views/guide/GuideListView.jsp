<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, guide.model.vo.*" %>
<% 
	ArrayList<Gboard> list = (ArrayList<Gboard>)request.getAttribute("list");

	PageInfo pi = (PageInfo)request.getAttribute("pi");
	String msg = (String)request.getAttribute("msg");
	
	int listCount = 1;
	int currentPage = 1;
	int maxPage = 1;
	int startPage = 1;
	int endPage = 1;
	if(!list.isEmpty()){
		listCount = pi.getListCount();
		currentPage = pi.getCurrentPage();
		maxPage = pi.getMaxPage();
		startPage = pi.getStartPage();
		endPage = pi.getEndPage();
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가이드 리스트 | TogetherTour</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
	#sort{float:right;}
	table{border-top: 1px solid;
		  border-bottom: 1px solid;
		  height:300px;
		  margin-bottom:50px;
		  }
	th{border-bottom: 1px solid; height:30px;}
	td{text-align:center;}
	#checkArea{height:100px;}
	#line{margin-bottom:100px;}
	label{vertical-align:middle;}
	#priceLabel{margin-left:30px; margin-right:10px;}
</style>
</head>
<body>
<div class=wrapper>
	<%@ include file="../common/header.jsp" %>
	<div class=contents>
		<h2><span>한국인 가이드 리스트</span></h2>
		<hr id=line>
		<div class=area id=checkArea>
		<select name=kind class=selectList>
			<option>분류</option>
			<option>국내</option>
			<option>해외</option>
		</select>
		<select name=country class=selectList>
			<option>국가</option>
			<option>한국</option>
			<option>미국</option>
			<option>중국</option>
			<option>일본</option>
			<option>프랑스</option>
			<option>이탈리아</option>
			<option>영국</option>
			<option>호주</option>
			<option>러시아</option>
		</select>
		<select name=city class=selectList>
		    <option>도시</option>
			<option>제주도</option>
			<option>뉴욕</option>
			<option>상해</option>
			<option>도쿄</option>
			<option>파리</option>
			<option>로마</option>
			<option>런던</option>
			<option>시드니</option>
			<option>모스크바</option>
		</select>
		<label id=priceLabel>예상가격</label>
		<select name=minPrice class=selectList>
			<option>최소가격</option>
			<option>0</option>
			<option>50000</option>
			<option>100000</option>
			<option>150000</option>
		</select>
		<select name=maxPrice class=selectList>
			<option>최대가격</option>
			<option>50000</option>
			<option>100000</option>
			<option>150000</option>
			<option>200000</option>
		</select> &nbsp;
		<button type=submit>조회</button>
		</div>
		<select id=sort>
			<option>별점순</option>
			<option>가격내림차순</option>
			<option>가격오름차순</option>
		</select>
		<br clear=both>
		<br>
		<table id=listView>
		<tr>
			<th>No.</th>
			<th width=200px>제목</th>
			<th>가이드닉네임</th>
			<th>분류</th>
			<th>국가</th>
			<th>지역</th>
			<th>여행시작날짜</th>
			<th>여행종료날짜</th>
			<th>가격</th>
			<th>작성일</th>
			<th width=50px>조회수</th>
		</tr>
		<% if(list.isEmpty()){ %>
		<tr>
			<td colspan=11>조회된 리스트가 없습니다.</td>
		</tr>
		<% } else {  
			for(Gboard gb : list) {
				String strKind = null;
				if(gb.getKind() == 1){
					strKind = "국내";
				} else {
					strKind = "해외";
				}
		%> 
		<tr>
			<td><%= gb.getGbNum() %><input type=hidden value='<%= gb.getGbNum() %>'></td>
			<td><%= gb.getgTitle() %></td>
			<td><%= gb.getgWriter() %></td>
			<td><%= strKind %></td>
			<td><%= gb.getCountry() %></td>
			<td><%= gb.getCity() %></td>
			<td><%= gb.getStartDate() %></td>
			<td><%= gb.getEndDate() %></td>
			<td><%= gb.getPrice() %></td>
			<td><%= gb.getEnrollDate() %></td>
			<td><%= gb.getgCount() %></td>
		</tr>
		<% 	} 
		   } %>
		</table>
		<div class='pagingArea' align='center'>
			<% if(!list.isEmpty()){ %>
			<!-- 맨 처음으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.guide?currentPage=1'" id=firstBtn>&lt;&lt;</button>
			
			<!-- 이전 페이지 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.guide?currentPage=<%= currentPage-1 %>'" id=beforeBtn>&lt;</button>
			<script>
				if(<%= currentPage %> <= 1){
					var first = $('#firstBtn');
					var before = $('#beforeBtn');
					first.attr('disabled','true');
					before.attr('disabled','true'); // 맨처음페이지일경우 클릭안되도록 
				}
			</script>
			
			<!-- 10개의 페이지 목록 -->
			<% for(int p = startPage; p <= endPage; p++){ %>
				<% if(p == currentPage){ %>
					<button id=choosen disabled><%= p %></button>
				<% } else { %>
					<button id=numBtn onclick="location.href='<%= request.getContextPath() %>/list.guide?currentPage=<%= p %>'"><%= p %></button>
				<% }
				}%>
				
			<!-- 다음 페이지 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.guide?currentPage=<%= currentPage + 1%>'" id=afterBtn>&gt;</button>
			
			<!-- 맨 끝으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/list.guide?currentPage=<%= maxPage %>'" id=lastBtn>&gt;&gt;</button>
			<% } %>
			<script>
				if(<%= currentPage %> >= <%= maxPage %>){
					var after = $('#afterBtn');
					var last = $('#lastBtn');
					after.attr('disabled','true');
					last.attr('disabled','true');
				}
			</script>
			
		</div> 
		<% if(loginUser != null && loginUser.getmKind() == 2) { %>
		<div class=BtnArea align=right>
			<button onclick="location.href='<%= request.getContextPath() %>/views/guide/GuideWrite.jsp'">가이드 등록</button>
		</div>
		<% } %>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
<script>
	var msg = '<%= msg %>';
	
	$(function(){
		if(msg != 'null'){
			alert(msg);
		} 
	});
	
	$(function(){
		<% if(!list.isEmpty()){ %>
			$('#listView td').mouseenter(function(){
				$(this).parent().css({'background':'gray','cursor':'pointer'});
			}).mouseout(function(){
				$(this).parent().css('background','none');
			}).click(function(){
				var gbNum = $(this).parent().children().children('input').val();
				
				// 로그인 한 사람만 상세보기 이용할 수 있게하기
				if('<%= loginUser %>' != 'null'){
					location.href='<%= request.getContextPath() %>/detail.guide?gbNum=' + gbNum;
				} else {
					alert('회원만 이용할 수 있는 서비스입니다.');
				}
			});
		<% } %>
	});
</script>
</body>
</html>