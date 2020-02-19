<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가이드 상세 | TogetherTour</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
 	#img{text-align:center;}
 	table{border-top:1px solid; margin-top:100px;}
 	td,th{border-bottom:1px solid;}
 	button{float: right; margin-top:30px;}
 	#introduce{text-align:center; margin-top:50px;}
 	h2{margin-top:100px;}
 	#gInsertForm{margin-bottom:100px;}
</style>
</head>
<body>
<div class='wrapper'>
	<%@ include file="../common/header.jsp" %>
	<div class='contents'>
		<h2>가이드 상세</h2>
		<form action="<%= request.getContextPath() %>/update.guide" id=gInsertForm>
			<table id=gWrite>
				<tr>
				<th width=200px><label>가이드명</label></th>
				<td><input type=text name=gName id=gName></td>
				</tr>
				<tr>
				<th><label>활동장소</label></th>
				<td>
				<select name=cate id=cate>
					<option>국내</option>
					<option>해외</option>
				</select>
				<select name=nation id=nation>
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
				<select name=city id=city>
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
				</td>
				</tr>
				<tr>
					<th><label>여행테마</label></th>
					<td>
					<input type=checkbox name=tema value=relax><label>휴식</label>
					<input type=checkbox name=tema value=tour><label>관광</label>
					<input type=checkbox name=tema value=food><label>식도락</label>
					<input type=checkbox name=tema value=shopping><label>쇼핑</label>
					<input type=checkbox name=tema value=performing/arts><label>공연/예술</label>
					<input type=checkbox name=tema value=etc><label>기타 : </label><input type=text>
					</td>
				</tr>
				<tr>
					<th><label>가격</label></th>
					<td><input type=text name=price id=price></td>
				</tr>
				<tr>
					<th><label>성별</label></th>
					<td>
					<input type=radio name=gender value=M><label>남자</label>
					<input type=radio name=gender value=F><label>여자</label>
					</td>
				</tr>
				<tr>
					<th><label>연락처</label></th>
					<td>
					<select name=Phone id=Phone>
						<option>국가번호</option>
						<option>+82</option>
					</select>&nbsp;&nbsp;
					<input type=text name=phone class=phone size=5> - 
					<input type=text name=phone class=phone size=5> - <input type=text name=phone class=phone size=5>
					</td>
				</tr>
				<tr>
					<th><label>오픈카카오톡주소</label></th>
					<td><a href=https://open.kakao.com/o/s5T7IsXb>클릭</a></td>
				</tr>
			</table>
			<div id=introduce>
				<p>여행소개</p><br>
				<textarea rows=20 cols=140 style='resize:none;'>내용입력</textarea>
			</div>
			<button type=submit id=gBtn>수정</button><br>
		</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
</body>
</html>