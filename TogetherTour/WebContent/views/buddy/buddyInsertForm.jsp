<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행자등록 | 투투</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/index.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/common_sub.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/buddy/buddyInsertForm.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
</head>

<body>
	<%@ include file="../common/header.jsp"%>
	<br>
	<br>
	<br> 
	<hr>
	<div class="contents">
		<div id="insertbuddy">
			<form action="<%=request.getContextPath()%>/insert.buddy"
				method="post">
				<h2>동행자 등록</h2>
				<a>양식에 맞게 등록 후 원하는 조건의 동행자를 구할 수 있으며여행지/여행날짜 기준으로 여행지를 검색할 수
					있습니다.</a><br> <br>

		<input type="button" value="여행중"> <input type="button"
			value="여행계획중"> <br> <br>

		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" size="40"></td>
			</tr>
			<tr>
				<th>여행기간</th>
				<td><input type="date" size="10" placeholder="년/월/일" value="2020-02-21" min="2020-02-21						">
					&nbsp; ~ &nbsp;
					<input type="date" size="10" placeholder="년/월/일">
				</td>
			</tr>
			<tr>
				<th>여행지역</th>
				<td>
						<select id="select_type">
							<option>국내/해외</option>
							<option>국내</option>
							<option>해외</option>
						</select>
						<select id="select_menu" style="width:160px;">
							<option>지역선택</option>
						</select>
		
						<script
							src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
						<script type="text/javascript">
							var n_korea = [ "지역 선택", "서울", "경기도", "강원도", "충청남도",
									"충청북도", "전라남도", "전라북도", "경상남도", "경상북도", "제주도" ];
							var n_abroad = [ "지역선택", "과테말라", "그리스", "그린란드", "나이지리아",
									"남아프리카공화국", "네팔", "노르웨이", "뉴질랜드", "대만", "덴마크",
									"도미니카공화국", "독일", "동티모르", "라오스", "라트비아", "러시아",
									"레바논", "루마니아", "리비아", "마다가스카르", "마카오", "말레이시아",
									"멕시코 ", "모로코", "몰디브", "몽골", "미국", "미얀마", "바레인",
									"바베이도스", "방그라데시", "베네수엘라", "베트남", "벨기에", "벨라루스",
									"보츠나와", "볼리비아", "브라질", "브루나이", "사우디아라비아", "세네갈",
									"수단", "수리남", "스리랑카", "스웨덴", "스페인", "시리아", "싱가포르",
									"아랍에미리트", "아르헨티나", "아이슬란드", "아일랜드", "아프가니스탄",
									"알레스카", "알제리", "앙골라", "에콰도르", "에티오피아", "영국", "예멘",
									"오만", "오스트레일리아", "요르단", "우루과이", "우주베키스탄", "우즈베키스탄",
									"우크라이나", "이라크", "이란", "이스라엘", "이집트", "이탈리아", "인도",
									"인도네시아", "일본", "중국", "체코", "칠레", "카자흐스탄", "카타르",
									"캄보디아", "캐나다", "케냐", "콜롬비아", "콩고민주공화국", "쿠바",
									"쿠웨이트", "타이", "탄자니아", "터키", "파나마", "파라과이", "파키스탄",
									"파푸아뉴기니", "팔라우", "페루", "포르투갈", "폴란드", "프랑스", "핀란드",
									"필리핀", "홍콩" ];
							var area = [ [ "지역 선택" ], n_korea, n_abroad ];
		
							function createTag(index) {
								var result = "";
								area[index].forEach(function(item) {
									result += "<option>" + item + "</option>";
								});
								return result;
							}
		
							function chgOptions() {
								var selected_index = $("#select_type option").index(
										$("#select_type option:selected"));
								$("#select_menu").html(createTag(selected_index));
							}
		
							$("#select_type").on("change", function() {
								chgOptions();
							});
						</script>
			</td>	
			</tr>
			
			<tr>
				<th>여행테마</th>
				<td>
					<input type="radio" name="radiobutton" value="rest" onClick="this.form.textbox.disabled=true">휴식
					<inputtype="radio" name="radiobutton" value="tour" onClick="this.form.textbox.disabled=true" >관광
					<input type="radio" name="radiobutton" value="shopping" onClick="this.form.textbox.disabled=true" >쇼핑
					<input type="radio" name="radiobutton" value="food" onClick="this.form.textbox.disabled=true" >식도락
					<input type="radio" name="radiobutton" value="art" onClick="this.form.textbox.disabled=true" >공연관람
					<input type="radio" name="radiobutton" value="etc onClick="this.form.textbox.disabled=false" onClick="form.textbox.changeBg(this.value)">기타
					<input type="text" name="textbox" placeholder="" ><!-- style="background:#E5E5E5" -->
					<!-- 원하는것-> 기타 활성화 되면 텍스트박스 컬러 그레이>화이트로 변경, 기타를 한번밖에 체크가 안됌;; -->					
					<script>
						function changeBg(color){
							 document.bgColor = color;
						}
					</script>
				</td>
			</tr>

			<tr>
				<th rowspan="3">동행자 모집</th>
				<td>모집인원&nbsp; &nbsp;&nbsp;<input type="number"
					style="width: 70px;"> 명
				</td>
			</tr>

			<tr>
				<td>모집성별&nbsp;&nbsp;&nbsp;<input type="radio" name="gender">남자
					<input type="radio" name="gender">여자 <input type="radio"
					name="gender">상관없음
				</td>
			</tr>

			<tr>
				<td>모집연령대 &nbsp; <input type="radio" name="group"> 10대
					<input type="radio" name="group"> 20대 이상 <input
					type="radio" name="group"> 30대 이상 <input type="radio"
					name="group"> 40대 이상 <input type="radio" name="group">
					상관없음

				</td>
			</tr>

			<!-- <tr>
						<th>동행참가</th>
						<td><input type="radio">전체참가 <input type="radio">부분참가</td>	
					</tr> -->
			<tr>
				<th>동행 날짜</th>
				<td><input type="date" placeholder="시작날짜">&nbsp; ~
					&nbsp;<input type="date" placeholder="종료날짜"></td>
			</tr>


			<tr>
				<th>여행소개</th>
				<td><textarea rows="8" cols="80" style="resize: none;"
						overflow="scroll"></textarea></td>
			</tr>
		</table>
		</div>
		<br> <br> <br>
		<div align="center">
			<button type="submit" id="insertBtn">등록하기</button>
			<button onclick="location.href='<%=request.getContextPath()%>/list.buddy'">취소</button>
		</div>


		<%-- 	<script>
					$(function(){
						$('#listArea td').mouseenter(function(){
							$(this).parent().css({'background':'darkgray', 'cursor':'pointer'});
						}).mouseout(function(){
							$(this).parent().css('background', 'none');
						}).click(function(){
							var bid = $(this).parent().children().children('input').val();
							
							// 로그인 한 사람만 상세보기 이용할 수 있게 하기
							<% if(loginUser != null){ %>
								location.href='<%= request.getContextPath() %>/insert.buddy?mid=' + mid;
							<% } else{ %>
								alert('회원만 이용할 수 있는 서비스입니다.');
							<% } %>
						});
					});
				</script> --%>

		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br>
		<%@ include file="../common/footer.jsp"%>
		<br> <br>
	</div>
</body>
</html>


<!-- 동행자등록 폼2 -->
<!-- <div class="wrapper">
	<div id="enrollBuddy">
	<h2>동행자 등록</h2>
	<a>enrollBuddy는 여행 계획중인 유저가 등록하는 게시글로서 에상기간과 출발일을 추가로 기재할 수있습니다.</a><br><br>
	</div>
	<input type="button" value="여행중"> <input type="button" value="여행계획중">
	<br><br>
	<form>
		<table>
		
			<tr>
				<th>닉네임</th>
				<td><input type="text"></td>	
			</tr>
	
			<tr>
				<th>예상기간</th>
				<td><input type="date" size="10" placeholder="년/월/일"> &nbsp; ~ &nbsp; <input type="date" size="10" placeholder="년/월/일"> </td>	
			</tr>
			<tr>
				<th>출발일</th>
				<td><input type="date" size="10" placeholder="년/월/일"></td>	
			</tr>			
			<tr>
				<th>여행지역</th>
				<td>
					<select name="category1">
						<option value="select">국내/해외</option>
						<option value="kor">국내</option>
						<option value="abr">해외</option>
					</select>
					<select name="category2">
						<option value="loca">지역</option>
						<option value="seoul">서울</option>
						<option value="gyeonggi">경기도</option>
						<option value="gangwon">강원도</option>
						<option value="chungchung">충청도</option>
						<option value="jeonra">전라도</option>
						<option value="gyeongsang">경상도</option>
						<option value="jeju">제주도</option>
					</select>
					<select name="category3"> 국가가 너무많아서..우선...대륙으로.. 그리고 자바스크립트로 국내선택하면 국내지역선택하고 해외선택하면 해외 박스 나오도록...
						<option value="loca">지역</option>
						<option value="asia">아시아</option>
						<option value="south america">남미</option>
						<option value="north america">북미</option>
						<option value="europe">유럽</option>
						<option value="oceania">오세아니아</option>
						<option value="africa">아프리카</option>
					</select>	
			</tr>
			
			<tr>
				<th>여행도시</th>
				<td><input type="text" size="60" placeholder="런던,바르셀로나,리스본, 등 여러 도시 입력시 컴마로 구분"></td>			
			</tr>
			
			<tr>
				<th>여행테마</th>
				<td>
					<input type="checkbox" name="rest">휴식
					<input type="checkbox" name="tour">관광	
					<input type="checkbox" name="shopping">쇼핑	
					<input type="checkbox" name="food">식도락
					<input type="checkbox" name="concert">공연관람
					<input type="checkbox" name="etc">기타 <input type="text"  placeholder="내용을 입력하여 주세요."> 
				</td>
			</tr>
			
			<tr>
				<th rowspan="3">동행자 모집</th>
				<td>모집인원&nbsp; <input type="number" style="width:40px"> 명</td> 		
			</tr>
			
			<tr>
				<td>모집성별&nbsp;<input type="radio" name="male">남자 <input type="radio" name="female">여자 <input type="radio" name="both">상관없음</td>
			</tr>
			
			<tr>
				<td>모집나이 &nbsp;<input type="number" style="width:40px"> ~ <input type="number" style="width:40px"> 세</td>  		
			</tr>
			
			<tr>
				<th>동행참가</th>
				<td><input type="radio">전체참가 <input type="radio">부분참가</td>	
			</tr>
			<tr>
				<th>동행 날짜</th>
				<td><input type="date" placeholder="시작날짜">&nbsp; ~ &nbsp;<input type="date" placeholder="종료날짜"> </td>	
			</tr>
			
			
			<tr>
				<th>여행소개</th>
				<td><textarea rows="8" cols="80" style="resize: none;" overflow="scroll"></textarea></td>
			</tr>
		</table>	
	<br>	
	<br>
	<br>
	<span class="btn">
	<input type="button" value="수정하기" class="editBtn">		
	<input type="button" value="등록하기" class="enrollBtn">
	</span>
</div> -->