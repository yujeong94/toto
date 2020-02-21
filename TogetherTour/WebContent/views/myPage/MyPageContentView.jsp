<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common_sub.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/myPage/myPage.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>

<body>
	<div class="wrapper">
		<!--S:header-->
		<%@ include file="../common/header.jsp" %>
		<!--E:header-->
		<!--S:contents-->
		<div class="contents">
			<h2><span>마이페이지</span></h2>
			<p style="margin-top:-85px; margin-bottom:30px; font-size:2rem; font-weight:bold; text-align:center;">내 정보 보기</p><hr>
			<%@ include file="../myPage/MyPageMenu.jsp" %><hr><br><br>
			<div id="myInfoArea">
				<div id="profileArea" onclick="">
					<img id="profile" src="../../images/defautProfileImage.png">
				</div><br>
				<table id="contentArea">
					<tr>
						<th>닉네임(NickName)</th>
						<td colspan="4">
						<!--<input id="nickName" class="myContent" type="text" value="< %= loginUser.getNickName() %>" readonly>-->
							<input id="nickName" class="myContent" type="text" value="< %= loginUser.getNickName() %>" readonly>
						</td>
					</tr>
					<tr>
						<th>회원구분(Kind)</th>
						<td colspan="4">
						<!--<input id="mKind" class="myContent" type="text" value="< %= loginUser.getmKind() %>" readonly>-->
							<input id="mKind" class="myContent" type="text" value="< %= loginUser.getmKind() %>" readonly>
						</td>
					</tr>
					<tr>
						<th>아이디(ID)</th>
						<td colspan="4">
						<!--<input id="userId" class="myContent" type="text" value="< %= loginUser.getmId() %>" readonly>-->
							<input id="userId" class="myContent" type="text" value="< %= loginUser.getmId() %>" readonly>
						</td>
					</tr>
					<tr>
						<th>이름(Name)</th>
						<td colspan="4">
						<!--<input id="userName" class="myContent" type="text" value="< %= loginUser.getUserName() %>" readonly>-->
							<input id="userName" class="myContent" type="text" value="< %= loginUser.getUserName() %>" readonly>
						</td>
					</tr>
					<tr>
						<th>이메일(Email)</th>
						<td colspan="4">
						<!--<input id="email" class="myContent" type="text" value="< %= loginUser.getEmail() %>" readonly>-->
							<input id="email" class="myContent" type="text" value="< %= loginUser.getEmail() %>" readonly>
						</td>
					</tr>
					<tr>
						<th>성별(Gender)</th>
						<td colspan="4">
						<!--<input id="gender" class="myContent" type="text" value="< %= loginUser.getGender() %>" readonly>-->
							<input id="gender" class="myContent" type="text" value="< %= loginUser.getGender() %>" readonly>
						</td>
					</tr>
					<tr>
						<th>나이(Age)</th>
						<td colspan="4">
						<!--<input id="age" class="myContent" type="text" value="< %= loginUser.getAge() %>" readonly>-->
							<input id="age" class="myContent" type="text" value="< %= loginUser.getAge() %>" readonly>
						</td>
					</tr>
					<tr>
						<th>나이(Age)</th>
						<td colspan="4">
						<!--<input id="age" class="myContent" type="text" value="< %= loginUser.getAge() %>" readonly>-->
							<input id="age" class="myContent" type="text" value="< %= loginUser.getAge() %>" readonly>
						</td>
					</tr>
					<tr>
						<td colspan="5"><button id="updateBtn" onclick="href=''">수정하기</button></td>
					</tr>
				</table><br><br><br><hr><br><br><br><br><br><br><br>
				<p style="margin-top:-85px; margin-bottom:30px; font-size:2rem; font-weight:bold; text-align:center;">나의 평점</p>
				<table id="contentArea">
					<tr>
						<td colspan="4">
<!--							< % int count = 0 ; %><br> -->
<!-- 							< % for(int i=0; i< loginUser.getGrade(); i++) {%><br> -->
<!-- 							<span id="fullStar" class="star">★</span> -->
<!-- 							< % count++ ; } %><br> -->
<!-- 							< % for(int i=5; i> count; i--) { %><br> -->
<!-- 							<span id="emptyStar" class="star">☆</span><br> -->
<!-- 							< % } %><br> -->
								<span id="fullStar"  class="star aa">★</span>
								<span id="fullStar"  class="star aa">★</span>
								<span id="fullStar"  class="star aa">★</span>
								<span id="emptyStar" class="star aa">☆</span>
								<span id="emptyStar" class="star aa">☆</span>&nbsp;&nbsp;
								<span id="gradeNum"  class="grade aa bb">&nbsp;&nbsp;3&nbsp;/&nbsp;5&nbsp;&nbsp;</span><br>
								<input type="button" id="detailGrade" value="상세 평가 보기" onclick="detailGrade();">
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--E:contents-->
		<!--S:footer-->
		<%@ include file="../common/footer.jsp" %>
		<!--E:footer-->
	</div>
	<script>
		function detailGrade() {
			window.open("detailGradeView.jsp", "checkNickForm", "width=500, height=300") ;
		}
	</script>
</body>

</html>