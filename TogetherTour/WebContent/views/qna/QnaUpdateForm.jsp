<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "qna.model.vo.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div class="contents">
		<h2>질문하기 수정</h2>
		<form action = "<%= request.getContextPath()%>/update.qna" method="post">
			<table>
				<tr>
					<th>제목 </th>
					<td><input type="text" name="title" value="<%= request.getParameter("title") %>"></td>
				</tr>
				<tr>
					<th>작성자<input type="hidden" name ="qnum" value="<%=request.getParameter("qnum") %>"></th>
					<td><%= request.getParameter("mId") %><input type="hidden" name="mId" value="<%= request.getParameter("mId") %>"></td>
				</tr>
				
				<tr>
					<th>작성일자</th>
					<td><%= request.getParameter("createDate") %><input type="hidden" name="createDate" value="<%= request.getParameter("createDate") %>"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea rows="15" cols="60" name="content" style="resize:none;"><%= request.getParameter("content") %></textarea>
					</td>
				</tr>
			
			</table>
			<br>
			<div align="center">
				<button type="submit" id="updateBtn">수정</button>
				<div id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/list.sh'">취소</div>
			</div>
	</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>