<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, guide.model.vo.*" %>
<% 
	ArrayList<Gboard> list = (ArrayList<Gboard>)request.getAttribute("list");
	String strKind = null; // kind string으로 바꿔서 담을 변수
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
	table{border-top: 1px solid;
		  border-bottom: 1px solid;
		  margin-bottom:50px;
		  }
	th{border-bottom: 1px solid; height:30px;}
	td{text-align:center; cursor:default;}
	.detailBtn,.profileBtn{cursor:pointer;}
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
		
		<form action="<%= request.getContextPath() %>/search.guide" method="post" id ="searchForm">
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
		
		<form id=listViewForm>
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
					if(gb.getKind() == 1){
						strKind = "국내";
					} else {
						strKind = "해외";
					}
			%> 
			<tr class=listTr>
				<td><%= gb.getGbNum() %><input type=hidden value='<%= gb.getGbNum() %>'></td>
				<td class=detailBtn><%= gb.getgTitle() %></td>
				<td class=profileBtn><%= gb.getgWriter() %></td>
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
		</form>
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
				$(this).parent().css({'background':'lightgray'});
			}).mouseout(function(){
				$(this).parent().css('background','none');
			});
			
			$('.detailBtn').click(function(){
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
	
	$('.profileBtn').click(function(){
		var userNick = $(this).text();
		window.open('views/myPage/memberProfile.jsp?userNick='+userNick,'profileForm','width=500, height=700');
	});
	
	// 국가도시목록
	$(function(){
		$.ajax({
			url: '<%= request.getContextPath() %>/list.loca',
		 	type: 'post',
		 	success: function(data){
		 		$selectKind = $('#kindList');
				$selectCountry = $('#countryList');
				$selectCity = $('#cityList');
				for(var i in data[0]){
					var $option = $('<option>');
					$option.val(data[0][i]);
					var con = null;
					if(data[0][i] == 1){
						con = "국내";
					} else {
						con = "해외";
					}
					$option.text(con);
					$selectKind.append($option);
				}
				
				// county변경
				$('#kindList').change(function(){
					var kindSel = $('#kindList option:selected').text();
					if(kindSel == "국내"){
						$('#countryList').find('option').remove();
						var $option = $('<option>');
						$option.val('국가');
						$option.text('국가');
						$selectCountry.append($option);
						var $option = $('<option>');
						$option.val('한국');
						$option.text('한국');
						$selectCountry.append($option);
					} else if(kindSel == "해외"){
						$('#countryList').find('option').remove();
						var $option = $('<option>');
						$option.val('국가');
						$option.text('국가');
						$selectCountry.append($option);
						for(var i in data[1]){
							var $option = $('<option>');
							$option.val(data[1][i]);
							$option.text(data[1][i]);
							$selectCountry.append($option);
						}
					} else {
						$('#countryList').find('option').remove();
						var $option = $('<option>');
						$option.text('국가');
						$selectCountry.append($option);
					}
				});
					// 해외city변경
					$('#countryList').change(function(){ 
						var countrySel = $('#countryList option:selected').text();
						$('#cityList').find('option').remove();
						for(var i in data[2]){
							if(data[2][i].country == countrySel){
								var $option = $('<option>');
								$option.val(data[2][i].city);
								$option.text(data[2][i].city);
								$selectCity.append($option);
							}
						}
						$('#kindList').change(function(){
							$('#cityList').find('option').remove();
							var $option = $('<option>');
							$option.val('도시');
							$option.text('도시');
							$selectCity.append($option);
						});
					});
		 	},
		 	error: function(data){
		 		console.log('error');
		 	}
		});
	});
	
</script>
</body>
</html>