<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행 일정 등록 | 투투</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/trip/trip_plan_list.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	
	<style>
		
	
	</style>
</head>
<body>
<div class="wrapper">
	<!--S:header-->
	<%@ include file="../common/header.jsp" %>
	<!--E:header-->
	<div class="contents">
		<h2><span>여행 스케쥴 만들기</span></h2>
		<form>
			<fieldset>
				<legend>여행 스케쥴 만들기</legend>
				<table class="first">
					<caption>여행 일정 등록</caption>
					<col>
					<col>
					<tbody>
						<tr>
							<th>제목</th>
							<td>
								<input type="text" name="title">
							</td>
						</tr>
						<tr>
							<th>출발일</th>
							<td>
								<input type="date" name="start_date"><br>
								출발일이 정해지지 않았다면 대략적인 날짜를 표시해 주세요.(추후 변경 가능)
							</td>
						</tr>
						<tr>
							<th>여행기간</th>
							<td>
								<input type="number" min="1" name="day"><br>
								여행 기간이 정해지지 않았다면 대략적인 기간을 입력해 주세요.(추후 변경 가능)
							</td>
						</tr>
						<tr>
							<th>여행지역</th>
							<td>
								<select name="kind">
									<option value="1">국내</option>
									<option value="2">해외</option>
								</select>
								<select name="country">
									<option>---국가---</option>
									<option>대한민국</option>
									<option>프랑스</option>
									<option>영국</option>
									<option>싱가폴</option>
								</select>
								<select name="city">
									<option>---지역---</option>
									<option>제주도</option>
									<option>경주</option>
									<option>전주</option>
									<option>속초</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
				<hr>
				<div class="memo">
					<div class="memo-box memo1">
						<button class="btn is-act">1일차</button>	<br>
						<textarea class="memo-insert1" placeholder="1일차 메모" rows="5" cols="50" name="memo1"></textarea>
					</div>
				
					<div class="memo-box memo2">
						<button>2일차</button><br>	
						<textarea class="memo-insert2" placeholder="2일차 메모" rows="5" cols="50" name="memo2"></textarea>
					</div>
					<div class="memo-box memo3">
						<button>3일차</button><br>
						<textarea class="memo-insert3" placeholder="3일차 메모" rows="5" cols="50" name="memo3"></textarea>
					</div>
					<!-- <button class="mConfirm-btn">메모저장</button> -->
				</div>
				<!-- <div class="memo-view1">
					<div></div>
				
				<br clear="both">
				</div> -->
				<hr>
				<div class="confirm-box">
					<button class="confirm" onclick="location.href='<%= request.getContextPath() %>/insert.trip'">등록하기</button>
				</div>	
			</fieldset>
		
		</form>
	</div>
	<!--S:footer-->
		<%@ include file="../common/footer.jsp" %>
		<!--E:footer-->
	</div>
</body>
</html>