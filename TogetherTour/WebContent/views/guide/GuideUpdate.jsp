<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	int kind = Integer.parseInt(request.getParameter("kind"));
	String[] selected = new String[2];
	if(kind == 1){
		selected[0] = "selected";
	} else {
		selected[1] = "selected";
	}
	
	String kakao = request.getParameter("kakao");
	
	String gKaKao = null; 
	if(kakao == null){
		gKaKao = "";
	} else {
		gKaKao = kakao;
	}
	// 국가랑 지역도 구분해야함
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가이드 수정 | TogetherTour</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
 	table{border-top:1px solid; margin-top:100px;}
 	td,th{border-bottom:1px solid;}
 	#gUpdateForm{margin-bottom:100px;}
 	#introduce{text-align:center; margin-top:50px;}
 	label{vertical-align:middle;}
 	.term{margin-right:10px;}
 	#term2{margin-left:10px; margin-right:10px;}
 	#gBtnArea{margin-top:40px;}
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
	#gBtn{margin-right:20px;}
</style>
</head>
<body>
<div class='wrapper'>
	<%@ include file="../common/header.jsp" %>
	<div class='contents'>
		<h2><span>가이드 수정</span></h2>
		<form action="<%= request.getContextPath() %>/update.guide" method=post id=gUpdateForm>
			<table id=gUpdate>
				<tr>
					<th width=200px><label>제목</label></th>
					<td> 
						<input type=text value="<%= request.getParameter("gTitle") %>" name=gTitle>
						<input type=hidden value="<%= request.getParameter("gbNum") %>" name=gbNum>
					</td>
				</tr>
				<tr>
					<th><label>활동장소</label></th>
					<td>
						<select name=kind id=kind>
							<option value=1 <%= selected[0] %>>국내</option>
							<option value=2 <%= selected[1] %>>해외</option>
						</select>
						<select name=country id=country>
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
					<th>여행기간</th>
					<td>
						<label class=term>여행시작일</label>
						<input type=date value="<%= request.getParameter("gStart") %>" name=gStart>
						
						<label id=term2>~</label>
						
						<label class=term>여행종료일</label>
						<input type=date value="<%= request.getParameter("gEnd") %>" name=gEnd>
					</td>
				</tr>
				<tr>
					<th><label>가격</label></th>
					<td>
					<input type=number value="<%= request.getParameter("price") %>" name=price> 원
					</td>
				</tr>
				<tr>
					<th><label>오픈카카오톡주소</label></th>
					<td><input type=text value="<%= gKaKao %>" name=kakao></td>
				</tr>
			</table>
			<div id=introduce>
				<p style="font-size:12pt;">여행소개</p><br>
				<textarea rows=50 cols=140 style="resize:none;" name=gContents>
					<%= request.getParameter("gContents") %>
				</textarea>
			</div>
			<div id=gBtnArea align=center>
				<button type=submit id=gBtn>수정완료</button>
				<div onclick='location.href="javascript:history.go(-1);"' id=cancleBtn>취소</div>
			</div>
		</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
</body>
</html>