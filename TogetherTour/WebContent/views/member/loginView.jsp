<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" %>
<%
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
	table{margin-left:30px; height:300px;}
	#loginForm{width: 500px; margin:auto; margin-bottom:100px; padding:20px}
	td{height:10px; margin-left:50px;}
	.loginArea{width:600px; height:600px; 
	           margin:auto;}
	#btnArea{margin-top:10px; font-size:13px;}
	#loginBtn{width:80px; margin-bottom:20px; background:#F7BE81; border: 0px; box-shadow:none; color:white;}
	#loginBtn,#enrollBtn,.find{cursor:pointer;}
	.inputStyle{border: 0px; box-shadow:none; border-bottom:1px solid black;}
</style>
</head>
<body>
<div class="wrapper">
	<%@ include file="../common/header.jsp" %>
	<div class=contents>
	<h2 align=center><span>Login</span></h2>
	<div class=loginArea>
	<form action="<%= request.getContextPath() %>/login.me" method=post id=loginForm onsubmit="return validate();">
		<table>
			<tr>
				<th><label>ID</label></th>
				<td><input type=text name=loginUserId id=loginUserId size=30px class=inputStyle></td>
			</tr>
			<tr>
				<th><label>Password</label></th>
				<td><input type=password name=loginUserPwd id=loginUserPwd size=30px class=inputStyle></td>
			</tr>
		</table>
		<div id=btnArea align=center>
		<input type=submit id=loginBtn value=로그인><br>
		<span onclick="location.href='<%= request.getContextPath() %>/views/member/joinForm.jsp'" id=enrollBtn>회원가입</span><br>
		<span onclick="findID();" class=find>아이디찾기</span>/
		<span onclick="findPwd();" class=find>비밀번호찾기</span>
		</div>
	</form>
	</div>
	</div>
    <%@ include file="../common/footer.jsp" %>
</div>

<script>
	// 아이디,비밀번호 필수입력 유효성 검사
	function validate(){
		if($('#loginUserId').val().trim().length == 0){
			alert('아이디를 입력해주세요.');
			$(this).focus();
			return false;
		} 
		if($('#loginUserPwd').val().trim().length == 0){
			alert('비밀번호를 입력해주세요.');
			$(this).focus();
			return false;
		}
		return true;
	}
	
	// 로그인 되는지 안되는지 알림창
	var msg = "<%= msg %>";
	
	$(function(){
		if(msg != "null"){
			alert(msg);
		}
	});
	
	// 아이디 찾기 새창
	function findID(){
		window.open("<%= request.getContextPath() %>/views/member/FindID.jsp","findID","width=700, height=500");
	}
	
	// 비밀번호 찾기 새창
	function findPwd(){
		window.open("<%= request.getContextPath() %>/views/member/FindPwd.jsp","findPwd","width=700, height=500");
	}
</script>
</body>
</html>