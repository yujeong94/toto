<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*"%>
<%
	Member member = (Member)request.getAttribute("loginUser") ;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/common_sub.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/myPage/myPage.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>

<body>
	<div class="wrapper">
		<!--S:header-->
		<%@ include file="../common/header.jsp"%>
		<!--E:header-->
		<!--S:contents-->
		<div class="contents">
			<h2><span>마이페이지</span></h2>
			<p style="margin-top:-85px; margin-bottom:30px; font-size:2rem; font-weight:bold; text-align:center;">내 정보 수정하기</p><hr>
			<%@ include file="../myPage/MyPageMenu.jsp"%><hr><br><br>
			<div id="myInfoArea">
				<script>
					// 사진 등록 버튼
					$(function(){
// 						$('#ImgBtn').hide() ;
						$('#Btn').click(function(){
							$('#ImgBtn').click() ;	
						}) ;
					}) ;
					
					// 사진 미리보기
					function LoadImg(input) {
						if (input.files && input.files[0]) {
							var reader = new FileReader() ;
							reader.onload = function (e) {
								$('#profile').attr('src', e.target.result) ; }
							reader.readAsDataURL(input.files[0]) ;
						}
					}
				</script>
				<form action="<%= request.getContextPath() %>/update.myPage" method=post id=joinForm onsubmit="return validate();" encType="multipart/form-data">
					<div id="profileArea" style="border:1px solid black;" onclick="">
						<img id="profile" src="">
					</div>
					<div style="text-align:center;">
						<input type=file id=ImgBtn name=ImgBtn onchange="LoadImg(this)">
					</div><br><br><br>
					<table id="contentArea">
						<tr>
							<th>아이디</th>
							<td colspan="4"><input type=text name=joinUserId id=joinUserId style="width:100%; background-color:#E6E6E6;" value="<%=loginUser.getmId()%>" readonly></td>
						</tr>
						<tr>
							<th rowspan="2">닉네임</th>
							<td colspan="4"><input type=text name=nickName id=nickName style="width:100%;" value="<%=loginUser.getNickName()%>" required></td> </tr> <tr>
							<td colspan="4"><span id=checkNick>(영문소문자 또는 한글, 2자~12자)</span></td>
						</tr>
						<tr>
							<th>이름</th>
							<td colspan="4"><input type=text name=userName id=userName style="width:100%;" value="<%=loginUser.getUserName()%>" required></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td colspan="4"><input type=email name=email id=email style="width:100%;" value="<%=loginUser.getEmail()%>" required></td>
						</tr>
						<tr>
							<th>성별</th>
							<td colspan="4">
								<script>
									$(function() {
										var gender = "<%=loginUser.getGender()%>" ;
										console.log("[Gender] : ["+gender+"]") ;
										if(gender == "M")
											$("#gender-M").prop('checked', true) ;
										else
											$("#gender-F").prop('checked', true) ;
									}) ;
								</script>
								<input type=radio id="gender-M" name=gender value=M class=gender placeholder="남자"><label>남자</label>
								<input type=radio id="gender-F" name=gender value=F class=gender placeholder="여자"><label>여자</label>
							</td>
						</tr>
						<tr>
							<th>생년월일</th>
							<td colspan="4"><input type=date name=age value="<%=loginUser.getAge()%> %>" style="width:100%;" required></td>
						</tr>
						<tr>
							<th>회원구분</th>
							<td colspan="4">
								<script>
									$(function() {
										var mKind = <%=loginUser.getmKind()%> ;
										console.log("[mKind] : ["+mKind+"]") ;
										if(mKind == 1)
											$("#general").prop('checked', true) ;
										else
											$("#guide").prop('checked', true) ;
									}) ;
								</script>
								<input type="radio" id="general" name=userType value="general"><label>일반</label>
								<input type="radio" id="guide" name=userType value="guide"><label>가이드</label>
							</td>
						</tr>
						<tr>
							<td colspan="5">
								<button id="updateBtn" onclick="submitCheck();">수정하기</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!--E:contents-->
		<!--S:footer-->
		<%@ include file="../common/footer.jsp"%>
		<!--E:footer-->
	</div>
	<script>
		function detailGrade() {
			window.open("detailGradeView.jsp", "checkNickForm", "width=500, height=300") ;
		}
		
		$('#nickName').change(function() {
			var userNick = $('#nickName').val() ;
			var reg2 = /^[a-z가-힣].{1,11}$/ ;
			// --------- 닉네임 중복확인
			if(reg2.test(userNick) == false) {
				$('#checkNick').text('알맞은 닉네임을 입력하세요.') ;
			} else if(userNick.trim().length != 0 && reg2.test(userNick)) {
				$.ajax({
					url: "<%= request.getContextPath() %>/NickCheck.me",
					type: 'post',
					data: {userNick: userNick},
					success: function(data) {
						if(data == 'success') {
							$('#checkNick').text("사용가능한 닉네임입니다.") ;
							isUsable = true ;
							isNickChecked = true ;
						} else {
							$('#checkNick').text("이미 사용중인 닉네임입니다.") ;
							isUsable = false ;
							isNickChecked = false ;
							$('#nickName').focus() ;
						}
					}
				});
			}
		}) ;
		
		function submitCheck() {
			if($('#nickName').val().trim().length == 0) {
				alert("닉네임을 입력해주세요.");
				$(this).focus();
				return false;
			} else if($('#userName').val().trim().length == 0) {
				alert("이름을 입력해주세요.");
				$(this).focus();
				return false;
			} else if(!isUsable || !isIdChecked || !isNickChecked) {
				console.log(isUable) ;
				console.log(isIdChecked) ;
				console.log(isNickChecked) ;
				alert('중복확인을 해주세요.') ;
			} else {
				location.href='<%=request.getContextPath()%>/update.myPage' ;
			}
		}
		
		$(function() {
			if(<%=loginUser.getmKind()%>==2) {
				$('#followList').css('display','none') ;
			} else {
				$('#followList').click(function() {
					location.href = "<%= request.getContextPath() %>/list.follow" ;
				}) ;
			}
		}) ;
	</script>
</body>

</html>