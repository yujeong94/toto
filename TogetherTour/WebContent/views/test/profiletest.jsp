<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필테스트</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
	#sort{float:right;}
	table{border-top: 1px solid;
		  border-bottom: 1px solid;
		  height:300px;
		  margin-bottom:50px;
		  }
	th{border-bottom: 1px solid; height:30px;}
	td{text-align:center;}
	#checkArea{height:100px;}
	#line{margin-bottom:100px;}
	label{vertical-align:middle;}
	#priceLabel{margin-left:30px; margin-right:10px;}
</style>
</head>
<body>
<div class=wrapper>
	<%@ include file="../common/header.jsp" %>
	<div class=contents>
		<table>
			<tr>
				<td onclick="profile();">작성자
				<div class=box>상자</div>
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>

</body>
</html>