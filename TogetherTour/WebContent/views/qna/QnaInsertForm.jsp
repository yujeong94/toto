<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&amp;A 등록 | 투투</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/trip/trip_plan_list.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
		<!--S:header-->
		<%@ include file="../common/header.jsp" %>
		<!--E:header-->
		<div class="contents">
			<h2><span>Q &amp; A</span></h2>
			
			<form action="<%= request.getContextPath() %>/insert.no" method="post" id ="insertForm">
				<table>
					<tr>
						<th>제목</th>
						<td colspan="3"><input type="text" size="58" name="title"></td>
					</tr>
					
					<tr>
						<th>작성자</th>
						<td>
							<%= loginUser.getNickName() %>
						</td>
						
					</tr>
		
					<tr>
						<th>내용</th>
						<td colspan="3">
						<textarea rows="15" cols="60" name="content" style="resize:none;"></textarea>
						</td>
					</tr>
				</table>
				
				<br>
				
				<div align="center">
					<button type="submit" id="insertNoBtn">등록</button>
					<!-- <div onclick="location.href='javascript:history.go(-1);'" id="cancelBtn">취소</div> -->
					<input type="button" onclick="back();" id="canCelBtn" value="취소">
				</div>
			</form>
			
			<script>
				function back(){
					$('#insertForm').attr('action', '<%= request.getContextPath() %>/list.rv');
					$('#insertForm').submit();
				}
			</script>
			
			
		</div>

		<!--S:footer-->
		<%@ include file="../common/footer.jsp" %>
		<!--E:footer-->
</body>
</html>