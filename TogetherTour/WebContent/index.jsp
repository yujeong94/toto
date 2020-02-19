.<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>여행동행사이트 투투</title>
	<link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="css/common.css">
</head>

<body>
	<div class="wrapper">
		<!--S:header-->
		<%@ include file="views/common/header.jsp" %>
		<!--E:header-->
		<!--S:contents-->
		<div class="contents">
			<section class="total-search">
				<div>
					<h3>동행 찾기</h3>
					<form>
						<fieldset>
							<legend>국가별 검색</legend>
							<h4>국가별 검색</h4>
							<div>
								<input type="radio"><label>국내</label>
								<input type="radio"><label>해외</label>
							</div>
							<!--국내 선택일때-->
							<div class="domestic">
								<select class="local">
									<option>지역 선택</option>
									<option>전주</option>
									<option>경주</option>
								</select>
								<button>검색</button>
							</div>
							<!--해외선택일때-->
							<div class="overseas">
								<select class="nation">
									<option>국가 선택</option>
									<option>미국</option>
									<option>영국</option>
								</select>
								<select class="local">
									<option>지역 선택</option>
									<option>워싱턴</option>
									<option>뉴욕</option>
								</select>
								<button>검색</button>
							</div>
						</fieldset>
						<fieldset>
							<legend>날짜별 검색</legend>
							<h4>날짜별 검색</h4>
							<div>
								<input type="date"> -
								<input type="date">
								<button>검색</button>
							</div>
						</fieldset>
					</form>
				</div>
			</section>
			<div class="best-rank clear-fix">
				<ol class="best best-trip">
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
				</ol>
				<ol class="best best-follower">
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
				</ol>
				<ol class="best best-guide">
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
					<li><a href="">list</a></li>
				</ol>
			</div>
		</div>
		<!--E:contents-->
		<!--S:footer-->
		<%@ include file="views/common/footer.jsp" %>
		<!--E:footer-->
	</div>
</body>

</html>