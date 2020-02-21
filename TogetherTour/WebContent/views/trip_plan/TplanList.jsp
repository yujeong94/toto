<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, trip_plan.model.vo.*" %>
<%
	ArrayList<Tplan> list = (ArrayList<Tplan>)request.getAttribute("list");
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
<title>여행 리스트 | 투투</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/trip/trip_plan_list.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<!--S:header-->
		<%@ include file="../common/header.jsp" %>
		<!--E:header-->
		<div class="contents">
			<h2><span>여행 리스트</span></h2>
			<form>
				<fieldset>
					<legend>국가 및 여행날짜 검색</legend>
					<table class="first">
						<caption>정렬 순서, 여행 국가별, 여행 날짜별 검색</caption>
						<col>
						<col>
						<tbody>
							<tr>
								<th>정렬 순서</th>
								<td>
									<button class="is-act">최근 출발일</button>
									<button>최신 등록순</button>
								</td>
							</tr>
							<tr>
								<th>여행 국가</th>
								<td>
									<select>
										<option>국내</option>
										<option>해외</option>
									</select>
									<select>
										<option>---국가---</option>
										<option>대한민국</option>
										<option>프랑스</option>
										<option>영국</option>
										<option>싱가폴</option>
									</select>
									<select>
										<option>---지역---</option>
										<option>제주도</option>
										<option>경주</option>
										<option>전주</option>
										<option>속초</option>
									</select>

								</td>
							</tr>
							<tr>
								<th>여행 날짜</th>
								<td>
									<input type="date"> ~ <input type="date">
								</td>
							</tr>
							<tr>
								<td colspan="2"><button class="confirm">검색</button></td>
							</tr>
						</tbody>
					</table>
				</fieldset>

			</form>
			<table class="second">
				<caption>여행 리스트</caption>
				<col class="first">
				<col class="second">
				<col class="third">
				<col class="fourth">
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
						<td colspan="5">조회된 리스트가 없습니다.</td>
					</tr>
					<% } else{ 
							for(Tplan t : list){
					%>
					<tr>
						<td>
							<strong><%= t.getTitle() %></strong>
							<%= t.getStartDate() %> ~ <%= t.getEndDate() %>(<%= t.getDay() %>Days)<br>
							<%= t.getContent() %>
						</td>
						<td><%= t.getmId() %></td>
						<td><%= t.getCreateDate() %></td>
						<td><%= t.getCountry() %><br><%= t.getCity() %></td>
						<td><%= t.gettCount() %></td>
					</tr>
					<% 		}
						}
					%>
					<tr>
						<td colspan="5">
						
						<!-- 페이징  -->
						<% if(!list.isEmpty()){ %>
						<!-- 맨 처음으로 -->
						<button onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=1'">&lt;&lt;</button>
						
						<!-- 이전 페이지로 -->
						<button onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=<%= currentPage-1 %>'" id="beforeBtn">&lt;</button>
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
								<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=<%= p %>'"><%= p %></button>
							<% } %>
						<% } %>
						
						<!-- 다음 페이지로 -->
						<button onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=<%= currentPage + 1 %>'" id="afterBtn">&gt;</button>
						<script>
							if(<%= currentPage %> >= <%= maxPage %>){
								var after = $("#afterBtn");
								after.attr('disabled', 'true');
							}
						</script>
						
						<!-- 맨 끝으로 -->
						<button onclick="location.href='<%= request.getContextPath() %>/list.trip?currentPage=<%= maxPage %>'">&gt;&gt;</button>
						
						<% } %>
						
						</td>
					</tr>
					<tr>
						<td colspan="5"><button class="confirm" onclick='location.href="views/trip_plan/boardInsertForm.jsp"'>일정 등록하기</button></td>
					</tr>
				</tbody>
			</table>

		</div>

		<!--S:footer-->
		<%@ include file="../common/footer.jsp" %>
		<!--E:footer-->
	</div>
</body>
</html>