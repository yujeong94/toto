<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 아이디 찾기 정보
	String userName = (String)request.getAttribute("userName");
	String resultID = (String)request.getAttribute("resultID");
	
	// 비밀번호 찾기 정보
	String userFindId =(String)request.getAttribute("userFindId");
	String findEmail = (String)request.getAttribute("findEmail");
	
	// 존재하지 않을때 안내메시지
	String idmsg = (String)request.getAttribute("idmsg");
	String pwdmsg = (String)request.getAttribute("pwdmsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>find Result</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<style>
	body {background:lightyellow; font-size:15pt;}
	h2 span {
		display : inline-block;
		border-bottom : 3px solid #333f50;
		padding-bottom : 10px;
		margin-bottom: 30px;
		margin-top: 20px;
	}
	resultArea {border: 1px solid black;}
	#resultBtn {
		padding : 8px 12px;
		border : 1px solid #cbcbcb;
		background : #fff;
		color : #000;
		box-shadow : inset 1px 1px 3px #e6e6e6;
		vertical-align: middle;
		font-weight : 700;
		font-size : 13pt;
		width: 80px; height:40px;
	}
	input {width:70%; height:30px;}
</style>
</head>
<body>
<div class="wrapper">
	<div class=contents>
	<h2 align=center><span></span></h2>
		<div class=resultArea>
			<p>
				<!-- 아이디 찾기 결과 화면 -->
				<% if(userName != null && resultID != null) { %>
					<%= userName %>회원님의 아이디는 <%= resultID %> 입니다. 
				<% } else if(idmsg != null){%>
					<%= idmsg %>
				<% } %>
				
				<!-- 비밀번호 찾기 결과 화면 -->
				<% if(userFindId != null && findEmail != null) { %>
					<%= userFindId %>회원님의 임시비밀번호를 <%= findEmail %>로 보냈습니다.
				<% } else if(pwdmsg != null){%>
					<%= pwdmsg %>
				<% } %>
			</p>
			
		</div>
		<div class=resultBtnArea align=center>
			<input type=submit id=resultBtn value="확인">
		</div>
	</div>
</div>
<script>
	$(function(){
		$('#resultBtn').click(function(){
			window.close();
		});
	});
</script>
</body>
</html>