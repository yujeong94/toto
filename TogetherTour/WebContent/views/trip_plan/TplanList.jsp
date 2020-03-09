<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList, trip_plan.model.vo.*" %>
<%
	ArrayList<Tplan> list = (ArrayList<Tplan>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
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
	
	String tKind = null; // kind string으로 바꿔서 담을 변수
	String msg = (String)request.getAttribute("msg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행 리스트 | TogetherTour</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/trip/trip_common.css">

	<style>

		td{cursor:default;}
		.detailBtn,.profileBtn{cursor:pointer;}
		
		.searchArea {
			border-top : 1px solid;
			margin-bottom : 50px;
		}
		
		.searchArea th, .searchArea td{
			border-bottom : 1px solid #cbcbcb;
		}
		
		.searchArea tr:last-child th, .searchArea tr:last-child td{
			border-bottom-color : #000;
		}
		
		.searchBtnArea {
			text-align : center;
			padding : 50px 0;
			margin-bottom : 50px;
		}
		.listArea {
			margin-bottom:50px;
		}
		.listArea .create_date {
			width : 100px;
		}
		.listArea .writer, .listArea .country {
			width : 200px;
		}
		.listArea .count {
			width : 100px;
		}
		
		.insertBtnArea {
			padding : 50px 0;
		}
		
		.tableArea tr:first-of-type th {
			border-bottom : 1px solid #000;
		}
		.tableArea tr:last-of-type td {
			border-bottom : 1px solid #000;
		}
		.tableArea tbody td:nth-of-type(2), .tableArea tbody td:nth-of-type(3), 
		.tableArea tbody td:nth-of-type(4), .tableArea tbody td:nth-of-type(5) {
			text-align : center;
		
		}
	</style>
	
</head>
<body>
	<div class="wrapper">
		<!--S:header-->
		<%@ include file="../common/header.jsp" %>
		<!--E:header-->
		<div class="contents">
			<h2><span>여행 리스트</span></h2>
			<form action="<%= request.getContextPath() %>/search.trip" method="post" id ="searchForm">
				<fieldset>
					<legend>국가 및 여행날짜 검색</legend>
					<div class="searchArea">
						<table>
							<tr>
								<td style="text-align : center;">
								
									<select name ="menu" id="menu">
										<!-- <option value ="tStart">최근 출발일</option>
										<option value ="tCreate">최신등록순</option> -->
										<option value ="title">제목</option>
										<option value ="nick">작성자</option>
										<option value ="location">여행지</option>
									</select>
									<input type="text" placeholder="검색어를 입력해주세요." name = "content" id="content" size="70">
									<input type="submit" id="search" value= "검색">
								
								</td>
								
							</tr>
						</table>
					</div>
				</fieldset>
			</form>
			<table class="listArea tableArea">
				<caption>여행 리스트</caption>
				<col class="title">
				<col class="writer">
				<col class="create_date">
				<col class="country">
				<col class="count">
				<thead>
					<tr>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>여행국가</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<% if(list.isEmpty()){ %>
					<tr>
						<td colspan="5" style="text-align : center;">조회된 리스트가 없습니다.</td>
					</tr>
					<% } else{ 
							for(Tplan t : list){
								if(t.getKind() == 1){
									tKind = "국내";
								} else {
									tKind = "해외";
								}
					%>
					<tr>
						<td class="detailBtn"><input type="hidden" value='<%= t.gettPnum() %>'>
							<strong><%= t.getTitle() %></strong><br>
							<%= t.getStartDate() %> ~ <%= t.getEndDate() %> (<%= t.getDay() %>Days)
						</td>
						<td class="profileBtn"><%= t.getmId() %></td>
						<td><%= t.getCreateDate() %></td>
						<td><%= t.getCountry() %><br><%= t.getCity() %></td>
						<td><%= t.gettCount() %></td>
					</tr>
					<% 		}
						}
					%>
				</tbody>
			</table>
			<div class='pagingArea' align='center'>
				<!-- 페이징  -->
				<% if(!list.isEmpty()){ %>
					<!-- 맨 처음으로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=1'" id="firstBtn" class="icon-fast-backward"></button>
					
					<!-- 이전 페이지로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=<%= currentPage-1 %>'" id="beforeBtn" class="icon-to-start"></button>
					<script>
						if(<%= currentPage %> <= 1){
							var first = $('#firstBtn');
							var before = $('#beforeBtn');
							first.attr('disabled','true');
							before.attr('disabled', 'true');
						}
					</script>
					
					<!-- 10개의 페이지 목록 -->
					<% for(int p = startPage; p <= endPage; p++){ %>
						<% if(p == currentPage){ %>
							<button id="choosen" disabled><%= p %></button>
						<% } else{ %>
							<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=<%= p %>'"><%= p %></button>
						<% } %>
					<% } %>
					
					<!-- 다음 페이지로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=<%= currentPage + 1 %>'" id="afterBtn" class="icon-to-end"></button>
					
					<!-- 맨 끝으로 -->
					<button onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=<%= maxPage %>'" id="lastBtn" class="icon-fast-forward"></button>
				<% } %>
				<script>
					if(<%= currentPage %> >= <%= maxPage %>){
						var after = $("#afterBtn");
						var last = $('#lastBtn');
						after.attr('disabled', 'true');
						last.attr('disabled','true');
					}
				</script>
			</div>
			<div class='insertBtnArea' align='center'>
			<% if(loginUser != null){ %>
				<button class="confirm" onclick='location.href="views/trip_plan/TplanWrite.jsp"'>일정 등록하기</button>
			<% } %>
			</div>
		</div>

		<!--S:footer-->
		<%@ include file="../common/footer.jsp" %>
		<!-- E:footer -->
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
			$('.listArea td').mouseenter(function(){
				$(this).parent().css({'background':'#F6E3CE'}); /* #F6E3CE */
			}).mouseout(function(){
				$(this).parent().css('background','none');
			});
			
			
			$('.detailBtn').click(function(){
				var tPnum = $(this).parent().children().children('input').val();
				
				// 로그인 한 사람만 상세보기 이용할 수 있게하기
				if('<%= loginUser %>' != 'null'){
					location.href='<%= request.getContextPath() %>/detail.trip?tPnum=' + tPnum;
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