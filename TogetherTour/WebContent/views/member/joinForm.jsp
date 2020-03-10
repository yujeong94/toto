<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>

<style>
	table{border:1px solid black; margin-top:100px;}
 	th{width:200px; text-align:right;}
 	#userImgArea{border: 1px solid black; border-radius:70%;
 				 width:150px; height:150px; margin:0 auto; margin-top:100px;
 				 overflow:hidden;}
 	#enrollArea{margin-top:20px;}
 	#enrollBtn{cursor:pointer;}
 	#joinNav{color:red;}
</style>
</head>
<body>
<div class="wrapper">
	<%@ include file="../common/header.jsp" %>
	<div class=contents>
	<h2><span>회원가입</span></h2>
	<nav id=joinNav>*모든 항목을 입력해주세요.</nav>
	<hr>
	<script>
		// 사진 등록 버튼
		$(function(){
			$('#ImgBtn').hide();
			$('#Btn').click(function(){
				$('#ImgBtn').click();	
			});
		});
		// 사진 미리보기
		function LoadImg(input) {
			 if (input.files && input.files[0]) {
			  var reader = new FileReader();
			  
			  reader.onload = function (e) {
			   $('#userImg').attr('src', e.target.result);  
			  }
			  
			  reader.readAsDataURL(input.files[0]);
			  }
			}
	</script>
	
	<div class=joinArea>
	<form action="<%= request.getContextPath() %>/insert.me" method=post id=joinForm onsubmit="return validate();" encType="multipart/form-data">
		<!-- 프로필 사진 표시 -->
		<div id=userImgArea>
			<img id=userImg>
		</div>
		<br>
		<!-- 프로필 사진 등록 버튼 -->
		<div id=fileArea align=center>
			<button type=button id=Btn>사진등록</button>
			<input type=file id=ImgBtn name=ImgBtn onchange="LoadImg(this)" required>
		</div>
		<table>
			<tr>
				<th>아이디 </th>
				<td><input type=text name=joinUserId id=joinUserId size=25px required></td>
				<td width=450px><span id=checkId>(영문소문자로 시작하는 영문소문자/숫자 조합, 4~12자)</span></td>
			</tr>
			<tr>
				<th>닉네임 </th>
				<td><input type=text name=nickName id=nickName size=25px required></td>
				<td><span id=checkNick>(영문소문자 또는 한글, 2자~12자)</span></td>
			</tr>
			<tr>
				<th>이름 </th>
				<td><input type=text name=userName id=userName size=25px required></td>
				<td></td>
			</tr>
			<tr>
				<th>비밀번호 </th>
				<td><input type=password name=joinUserPwd id=joinUserPwd size=30px required></td>
				<td><span id=checkPwd>(영문 대소문자/숫자/특수문자 각 1가지 이상의 조합, 8자~16자)</span></td>			
			</tr>
			<tr>
				<th>비밀번호 확인 </th>
				<td><input type=password name=pwd2 id=pwd2 size=30px required></td>
				<td><span id=checkPwd2></span></td>
			</tr>
			<tr>
				<th>이메일 </th>
				<td><input type=email name=email id=email size=30px required></td>
				<td></td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type=radio name=gender value=M class=gender checked> 남자
					<input type=radio name=gender value=F class=gender> 여자
				</td>
				<td></td>
			</tr>
			<tr>
				<th>생년월일 </th>
				<td><input type=date name=age required></td>
				<td></td>
			</tr>
			<tr>
				<th>회원구분 </th>
				<td>
					<input type=radio name=userType value="general" checked> 일반 
					<input type=radio name=userType value="guide"> 가이드 
				</td>
				<td></td>
			</tr>
		</table>
		
		<div id=enrollArea align=center>
		<input type=submit id=enrollBtn value=가입하기>
		</div>
	</form>
	</div>
	</div>
    <%@ include file="../common/footer.jsp" %>
</div>

<script>
	
	// 아이디, 닉네임 중복 확인
	var isIdUsable = false;// 중복이 되었다, 되지 않았다 
	var isNickUsable = false;
	// 아이디 중복 확인
	var isIdChecked = false; // 아이디 중복체크를 한 적이 있는지 , 중복체크하고나서 값을 다시 바꾸면 다시 중복체크할수있도록
	// 닉네임 중복 확인 
	var isNickChecked = false;
	
	$('#joinUserId,#nickName').on('change paste keyup', function(){
	//                값이 바꼈거나 붙여넣기했거나 키보드로 다시 썼을때
		isIdChecked = false;
		isNickChecked = false;
	});
	
	// 아이디,닉네임 조건 체크 
	var useId = false;
	var useNick = false;
	// 아이디 중복확인
	$('#joinUserId,#nickName').change(function(){
		var userId = $('#joinUserId').val();
		var reg = /^[a-z]+[0-9]+[a-z0-9]*$/;
		
		if(reg.test(userId) == false || userId.length < 4 || userId.length > 12) {
			$('#checkId').text('알맞은 아이디를 입력하세요.');
			useId = false;
		} else if(userId.trim().length != 0 && reg.test(userId)){
			useId = true;
			$.ajax({
				url: "<%= request.getContextPath() %>/idCheck.me",
				type: 'post',
				data: {userId: userId},
				success: function(data){
					if(data == 'success'){
						$('#checkId').text("사용가능한 아이디입니다.");
						isIdUsable = true;
						isIdChecked = true;
					} else {
						$('#checkId').text("이미 사용중인 아이디입니다.");
						isIdUsable = false;
						isIdChecked = false;
					}
				}
			});
		}
		
		var userNick = $('#nickName').val();
		var reg2 = /^[a-z가-힣]{2,12}$/;
		// --------- 닉네임 중복확인
		if(reg2.test(userNick) == false){
			$('#checkNick').text('알맞은 닉네임을 입력하세요.');
			useNick = false;
		}else if(userNick.trim().length != 0 && reg2.test(userNick)){
			useNick = true;
			$.ajax({
				url: "<%= request.getContextPath() %>/NickCheck.me",
				type: 'post',
				data: {userNick: userNick},
				success: function(data){
					if(data == 'success'){
						$('#checkNick').text("사용가능한 닉네임입니다.");
						isNickUsable = true;
						isNickChecked = true;
					} else {
						$('#checkNick').text("이미 사용중인 닉네임입니다.");
						isNickUsable = false;
						isNickChecked = false;
					}
				}
			});
		}
	});
	
	var pwdUsable = false;
	var pwdUsable2 = false;
	
	var pwdChecked = false;
	var pwd2Checked = false;
	
	$('#joinUserPwd,#pwd2').on('change paste keyup', function(){
		//                값이 바꼈거나 붙여넣기했거나 키보드로 다시 썼을때
			pwdChecked = false;
			pwd2Checked = false;
	});
	
	$(function(){
		$('#joinUserPwd').blur(function(){
			var input = $(this).val();
			var reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
			if(reg.test(input) == false){
				$('#checkPwd').text('알맞은 비밀번호를 입력하세요.' + $('#checkPwd').val());
				pwdUsable = false;
				pwdChecked = false;
			} else {
				$('#checkPwd').text('사용 가능한 비밀번호입니다.');
				pwdUsable = true;
				pwdChecked = true;
			}
		});
		$('#pwd2').keyup(function(){
			var pwd1 = $('#joinUserPwd').val();
			var pwd2 = $(this).val();
			if(pwd1 != pwd2) {
				$('#checkPwd2').text('비밀번호가 일치하지 않습니다.');
				pwdUsable2 = false;
				pwd2Checked = false;
			} else {
				$('#checkPwd2').text('비밀번호가 일치합니다.');
				pwdUsable2 = true;
				pwd2Checked = true;
			}
		});
	});
	
	// 유효성 검사
	function validate(){
		if($('#joinUserId').val().trim().length == 0) {
			alert("아이디를 입력해주세요.");
			$(this).focus();
			return false;
		}
		if($('#nickName').val().trim().length == 0) {
			alert("닉네임을 입력해주세요.");
			$(this).focus();
			return false;
		}
		if($('#userName').val().trim().length == 0) {
			alert("이름을 입력해주세요.");
			$(this).focus();
			return false;
		}
		if($('#joinUserPwd').val().trim().length == 0) {
			alert("비밀번호를 입력해주세요.");
			$(this).focus();
			return false;
		}
		if($('#pwd2').val().trim().length == 0) {
			alert("비밀번호확인을 입력해주세요.");
			$(this).focus();
			return false;
		}
		
		if(pwdUsable == false){
			alert("알맞은 비밀번호를 입력해주세요.");
			$('#joinUserPwd').focus();
			return false;
		}
		
		if(pwdUsable2 == false){
			alert("비밀번호가 일치하지 않습니다.");
			$('#pwd2').focus();
			return false;
		}
		
		if(useId == false){
			alert("알맞은 아이디를 입력해주세요");
			$('#joinUserId').focus();
			return false;
		}
		
		if(useNick == false){
			alert("알맞은 닉네임을 입력해주세요");
			$('#nickName').focus();
			return false;
		}
		
		if(isIdUsable == false || isIdChecked == false){
			alert('아이디 중복확인을 해주세요.');
			$('#joinUserId').focus();
			return false;
		}
		
		if(isNickUsable == false || isNickChecked == false){
			alert('닉네임 중복확인을 해주세요.');
			$('#nickName').focus();
			return false;
		}
	
		return true;
	}
	
	var msg = "<%= msg %>";
	$(function(){
		$('#enrollBtn').click(function(){
			if(msg != "null") {
				alert(msg);
			}
		});
	});
	
	$(function(){
		$('#enrollBtn').click(function(){
			if($('#ImgBtn').val()== ''){
		         alert('사진을 첨부해주세요');
		    }
		});
	});
</script>
</body>
</html>