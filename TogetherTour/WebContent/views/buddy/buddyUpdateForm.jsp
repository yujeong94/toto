<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행자수정 | 투투</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common_sub.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/buddy/buddyInsertForm.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<br><br><br>
	<hr>
	<div class="contents">
		<h2><span>동행자 수정</span></h2>
			<form action="<%=request.getContextPath()%>/update.buddy" method="post" id=buddyUpdateForm>
				<!-- 등록폼 테이블 -->
				<table>
					<tr>
						<th>제목</th>
						<td><input type="text" size="80" name="title" value=<%=request.getParameter("title") %>></td>
					</tr>
				<!-- 	<tr>
						<th>여행기간</th>
						<td>
						<input type="date" size="10" name="start_date" placeholder="년/월/일" value="2020-02-21" min="2020-02-21">
						&nbsp; ~ &nbsp;
						<input type="date" size="10" name="end_date" placeholder="년/월/일">
						</td>
					</tr> -->
					<tr>
						<th>여행지역</th>
						<td>
							<select name=kind id=kindList>
								<option>분류</option>
							</select>
							<select name=country id=countryList>
								<option>국가</option>
							</select>
							<select name=city id=cityList>
								<option>도시</option>
							</select>
						</td>
					</tr>
					<!-- name값으로 서블릿에 불러가서.. -->
					<tr>
						<th>동행 날짜</th>
						<td>
							<input type="date" placeholder="시작날짜" name="start_date" value=<%=request.getParameter("start_date") %>>&nbsp; ~ &nbsp;
							<input type="date" placeholder="종료날짜" name="end_date" value=<%=request.getParameter("end_date") %>>
						</td>
					</tr>
					<tr>
						<th>여행테마</th>
						<td>
							<input type="radio" name="theme" value=<%=request.getParameter() %> onClick="this.form.textbox.disabled=true">휴식
							<input type="radio" name="theme" value="tour" onClick="this.form.textbox.disabled=true">관광
							<input type="radio" name="theme" value="shopping" onClick="this.form.textbox.disabled=true">쇼핑
							<input type="radio" name="theme" value="food" onClick="this.form.textbox.disabled=true">식도락
							<input type="radio" name="theme" value="art" onClick="this.form.textbox.disabled=true">공연관람
							<input type="radio" name="theme" value="etc" onClick="this.form.etc.disabled=false">기타
							<input type="text" name="etc" placeholder="입력해주세요."><!-- style="background:#E5E5E5" -->
							<!-- 원하는것-> 기타 활성화 되면 텍스트박스 컬러 그레이>화이트로 변경, 기타를 한번밖에 체크가 안됌;; -->
						</td>
					</tr>
					<tr>
						<th rowspan="3">동행자 모집</th>
						<td>모집인원&nbsp; &nbsp;&nbsp;
							<input type="number" name="head_cnt" style="width: 70px;"> 명
						</td>
					</tr>
					<tr>
						<td>모집성별&nbsp;&nbsp;&nbsp;
							<input type="radio" name="gender" value="남자">남자
							<input type="radio" name="gender" value="여자">여자 
							<input type="radio" name="gender" value="상관없음">상관없음		
						</td>
					</tr>
					<tr>
						<td>모집연령대 &nbsp;
							<input type="radio" name="group_age" value="10"> 10대 
							<input type="radio" name="group_age" value="20"> 20대 이상
							<input type="radio" name="group_age" value="30"> 30대 이상
							<input type="radio" name="group_age" value="40"> 40대 이상
							<input type="radio" name="group_age" value="00"> 상관없음
						</td>
					</tr>
					
					<tr>
						<th>여행소개</th>
						<td>
							<textarea rows="8" cols="80" style="resize: none;" overflow="scroll" name="content"></textarea>
						</td>
					</tr>
				</table>
			<br> <br> <br>
			<div id=b_insert align="center">
				<button type="submit" id="insertBtn">등록하기</button>
				<button onclick="location.href='<%=request.getContextPath()%>/list.buddy'">취소</button>
			</div>
		</form>	
	</div>
		<script>
			$(function(){
				$('#listArea td').mouseenter(function(){
					$(this).parent().css({'background':'darkgray', 'cursor':'pointer'});
				}).mouseout(function(){
					$(this).parent().css('background', 'none');
				}).click(function(){
					var bid = $(this).parent().children().children('input').val();
						
					// 로그인 한 사람만 상세보기 이용할 수 있게 하기
					<%if (loginUser != null) {%>
						location.href='<%=request.getContextPath()%>/insert.buddy?mid=' + mid;
					<%} else {%>
						alert('회원만 이용할 수 있는 서비스입니다.');
					<%}%>
				});
			});
		</script>
		<br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br> <br><br> <br> <br> <br>
		<%@ include file="../common/footer.jsp"%>
		<br> <br>


	<!-- LOCATION SCRIPT  -->
	<script>
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


