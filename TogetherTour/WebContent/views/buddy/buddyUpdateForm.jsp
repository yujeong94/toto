<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("UTF-8");
String title = request.getParameter("title");
String kind = request.getParameter("kind");
String country = request.getParameter("country");
String city = request.getParameter("city");
String theme = request.getParameter("theme");
String kakao = request.getParameter("kakao");
String gender = request.getParameter("gender");
int group_age = Integer.parseInt(request.getParameter("group_age"));
/* 
String[] selected = new String[2];

if(kind.equals("국내")){
	selected[0] = "selected";
	selected[1] = "";
} else {
	selected[0] = "";
	selected[1] = "selected";
}	 
 */

 
 // theme 
String[] checkedTheme = new String[6];
if(!theme.equals("")){
	String[] themeArr = theme.split(",");
	
	for(int i = 0; i < themeArr.length; i++){
		switch(themeArr[i]){
		case "휴식": checkedTheme[0] = "checked"; break;
		case "관광": checkedTheme[1] = "checked"; break;
		case "쇼핑": checkedTheme[2] = "checked"; break;
		case "식도락": checkedTheme[3] = "checked"; break;
		case "관람": checkedTheme[4] = "checked"; break;
		case "기타": checkedTheme[5] = "checked"; break;
		}
	}
} 


// gender
String[] checkedGender = new String[3];
if(!theme.equals("")){
	String[] genderArr = gender.split(",");
	
	for(int i = 0; i < genderArr.length; i++){
		switch(genderArr[i]){
		case "남자": checkedGender[0] = "checked"; break;
		case "여자": checkedGender[1] = "checked"; break;
		case "상관없음" : checkedGender[2] = "checked"; break;
		}
	}
} 

// group_age
/* int[] checkedGroup_age = new int[5];
/* if(!group_age.equals("")){
	String[] group_ageArr = group_age.split(",");
	
	for(int i = 0; i < group_ageArr.length; i++){
		switch(group_ageArr[i]){
		case "남자": checkedGender[0] = "checked"; break;
		case "여자": checkedGender[1] = "checked"; break;
		case "상관없음" : checkedGender[2] = "checked"; break;
		}
	}
}
 */ 
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행자수정 | 투투</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common_sub.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/buddy/buddyInsertForm.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<style>
	    
#cancleBtn{
	 	padding : 9px 20px;
		border : none;
	    background: #999999;
	    color: #fff;
	    box-sizing: border-box;
	    display: inline-block;
	    align-items: flex-start;
	    letter-spacing: normal;
	    word-spacing: normal;
	    text-rendering: auto;
	    cursor: pointer;
	    vertical-align: middle;
	    font: inherit;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<br><br><br>
	<hr>
	<div class="contents">
		<h2><span>동행자찾기 수정</span></h2>
			 <form action="<%=request.getContextPath()%>/update.buddy" method="post" id=buddyUpdateForm>
				<!-- 등록폼 테이블 -->
				<table>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" size="80" name="title" value=<%=request.getParameter("title") %>>
							<%System.out.println("수정폼의 title 은 null인가? : " + title); %>  <!-- OK 잘들어옴 -->
							<input type=hidden name="bnum" value="<%= request.getParameter("bnum") %>">
						</td>
					</tr>
					<%-- <tr>
						<th><label>여행지역</label></th>
						<td>
							<select name=kind id=kindList>
								<option>분류</option>
								<option value="국내" <%= selected[0] %>>국내</option>
								<option value="해외" <%= selected[1] %>>해외</option>
							</select>
							<select name=country id=countryList>
								<option><%= country %></option>
								<!-- <option>국가</option> -->
							</select>
							<select name=city id=cityList>
								<option><%= city %></option>
								<!-- <option>도시</option> -->
							</select>
						</td>
					</tr>  --%>
					<tr>
						<th>동행 날짜</th>
						<td>
							<input type="date" placeholder="시작날짜" name="start_date" value=<%=request.getParameter("start_date") %>> &nbsp; ~ &nbsp;
							<input type="date" placeholder="종료날짜" name="end_date"  value=<%=request.getParameter("end_date") %>>
						</td>
					</tr>
					<tr>
						<th>여행테마</th>
						<td>
							<input type="radio" name="theme" value="휴식"  <%= checkedTheme[0] %> onClick="this.form.etc.disabled=true">휴식
							<input type="radio" name="theme" value="관광"  <%= checkedTheme[1] %> onClick="this.form.etc.disabled=true">관광
							<input type="radio" name="theme" value="쇼핑"  <%= checkedTheme[2] %> onClick="this.form.etc.disabled=true">쇼핑
							<input type="radio" name="theme" value="식도락" <%= checkedTheme[3] %> onClick="this.form.etc.disabled=true">식도락
							<input type="radio" name="theme" value="관광"  <%= checkedTheme[4] %> onClick="this.form.etc.disabled=true">공연관람
							<input type="radio" name="theme" value="기타"  <%= checkedTheme[5] %> onClick="this.form.etc.disabled=false">기타
							<input type="text" name="etc" placeholder="입력해주세요.">
							
						</td>
					</tr>
					<tr>
						<th rowspan="3">동행자 모집</th>
						<td>모집인원&nbsp; &nbsp;&nbsp;
							<input type="number" name="head_cnt" style="width: 70px;"  value="<%=request.getParameter("head_cnt") %>"> 명
						</td>
					</tr>
					<tr>
						<td>모집성별&nbsp;&nbsp;&nbsp;
							<input type="radio" name="gender" value="남자" <%= checkedGender[0] %>>남자
							<input type="radio" name="gender" value="여자" <%= checkedGender[1] %>>여자 
							<input type="radio" name="gender" value="상관없음" <%= checkedGender[2] %>>상관없음		
						</td>
					</tr>
					<tr>
						<td>모집연령대 &nbsp;
							<input type="radio" name="group_age"  value="<%=request.getParameter("group_age") %>" > 10대 
							<input type="radio" name="group_age"  value="<%=request.getParameter("group_age") %>" > 20대 이상
							<input type="radio" name="group_age"  value="<%=request.getParameter("group_age") %>" > 30대 이상
							<input type="radio" name="group_age"  value="<%=request.getParameter("group_age") %>" > 40대 이상
							<input type="radio" name="group_age"  value="<%=request.getParameter("group_age") %>" > 상관없음
						</td>
					</tr>
					<tr>
						<th><label>오픈카카오톡주소</label></th>
						<td><input type=text size=81px name="kakao" value="<%= kakao%>"></td>
						
					</tr>
					<tr>
						<th>여행소개</th>
						<td>
							<textarea rows="8" cols="80" style="resize: none;" overflow="scroll" name="content" value="<%=request.getParameter("content")%>"></textarea>
						</td>
					</tr>
				</table>
			<br> <br> <br>
			<div id=b_insert align="center">
				<button type="submit" id="insertBtn">수정하기</button>
				<div onclick='location.href="javascript:history.go(-2);"' id=cancleBtn>취소</div>
				
			</div>
		</form>	 
	</div>
	<script>
	$('td').click(function(){
            var bid = $(this).parent().children().children('input').val();
            
            // 로그인 한 사람만 상세보기 이용할 수 있게하기
            <% if(loginUser != null){%>
               location.href='<%= request.getContextPath() %>/detail.bo?bid=' + bid;
            <% } else{ %>
               alert('회원만 이용할 수 있는 서비스입니다.');
            <% } %>
         });
		<%@ include file="../common/footer.jsp"%>
		
	</script>

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


