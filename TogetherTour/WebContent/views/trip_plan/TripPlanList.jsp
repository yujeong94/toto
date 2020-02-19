<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						<th>여행구분</th>
						<th>여행국가</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><figure><img src="http://placehold.it/50x50">KH</figure><br>
							<strong>내 여행</strong>
							2020 1월 15일 목요일 ~ 2020 1월 20일 토요일 (3 Days)<br>
							혼자여행합니다<br>
							[식도락]
						</td>
						<td>홀로여행</td>
						<td>제주도</td>
						<td>2</td>
					</tr>
					<tr>
						<td colspan="4"><button class="confirm">일정 등록하기</button></td>
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