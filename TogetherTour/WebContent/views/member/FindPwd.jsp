<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
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
	table {
	border-collapse: collapse;
	margin-left: 120px;
	margin-bottom: 50px;
	width:70%;
	}
	th, td{
		padding: 15px;
		vertical-align: middle;
		height:70px;
	}
	th {
		font-weight : 700;
	}
	#findPwdBtn {
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
	<h2 align=center><span>Password 찾기</span></h2>
	<form action='<%= request.getContextPath() %>/findPwd.me' method=post id=findPwdForm>
		<table>
			<tr>
				<th>아이디  </th>
				<td><input type=text name=userFindId id=userFindId></td>
			</tr>
			<tr>
				<th>이메일  </th>
				<td><input type=email name=findEmail id=findEmail></td>
			</tr>
		</table>
		<div class=findBtnArea align=center>
			<input type=submit id=findPwdBtn value="확인">
		</div>
	</form>
	</div>
</div>
</body>	
</body>
</html>