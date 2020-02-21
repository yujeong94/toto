<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="review.model.vo.Review"%>
<%
	request.setCharacterEncoding("UTF-8");
	String guide = request.getParameter("guide");
	String gCheck ="";
	
	if(guide.equals("Y")){
		gCheck="checked";
	}
	
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
		<h2>여행 후기 & 팁 수정</h2>
			<form action = "<%= request.getContextPath()%>/update.rv" method="post">
				<table>
					<tr>
						<th>여행지역<input type="hidden" name ="rnum" value="<%= request.getParameter("rnum")%>"></th>
						<td><input type="text" name = "location" value = "<%=request.getParameter("location")%>"></td>
					</tr>
					
					<tr>
						<th>가이드 동행여부</th>
						<td><input type="checkbox" name = "guide" value = "guide" <%= gCheck%>>(동행시 체크)</td>
					</tr>
					
					
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="<%= request.getParameter("title") %>"></td>
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
					<div id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/list.rv'">취소</div>
				</div>
		</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>