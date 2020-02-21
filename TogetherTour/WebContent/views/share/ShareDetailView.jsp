<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="share.model.vo.*, java.util.*"%>
<%
	Share share = (Share)request.getAttribute("share");
	ArrayList<Sattachment> fileList = (ArrayList<Sattachment>)request.getAttribute("fileList");
	Sattachment titleImg = fileList.get(0);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나눔 게시판 확인하기</title>
	<link rel="stylesheet" type="text/css" href="../../css/share/share_board.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<div class="header">
			<%-- <%@ include file="<%= request.getContextPath() %>/jsp/header.jsp" %> --%>
		</div>
		<div class="contents">
			<div id="pageTitle">
				<h1 id="pageName">나눔 게시판</h1>&nbsp;여행하면서 서로 남은 물건들을 공유할 수 있는 곳입니다.
			</div>
			<hr>
			<div>
				<h2 id="detailTitle">나눔 게시글 상세조회</h2>
				<form id="detailForm" action="<%= request.getContextPath() %>/views/share/ShareUpdateForm.jsp" methid="post">
					<table id="detailArea">
						<tr>
							<th id="detailList">게시글 번호</th>
							<td colspan="4"><input type="hidden" name="bid" value="<%= request.getParameter("mid") %>"></td>
						</tr>
						<tr>
							<th id="detailList">게시글 제목</th>
							<td colspan="4"><input type="hidden" value="<% %>" id="title" class="wrtInput"></td>
						</tr>
						<tr>
							<th id="detailList">물품 종류</th>
							<td colspan="4">
								<select id="kind" class="wrtInput">
									<option value="none" disabled selected>물품 종류 선택</option>
									<option value="USIM">유심(USIM)</option>
									<option value="traffic">교통권</option>
									<option value="ticket">티켓(ticket)</option>
									<option value="necess">생필품(necessity)</option>
									<option value="money">돈(money)</option>
									<option value="etc">기타(..etc)</option>
								</select>
							</td>
						</tr>
						<tr>
							<th id="wrtList">내용</th>
							<td colspan="4">
								<textarea id="content"></textarea>
							</td>
						</tr>
						<tr>
							<th rowspan="2" id="wrtList">첨부파일</th>
							<td colspan="4">
								<input type="file" value="파일 업로드" id="file" accept=".gif, .jpg, .jpeg, .png, .pdf" multiple><br>
								<p id="fileText">파일은 5개까지만 업로드 가능하며, .jpg, .jpeg .png, .gif, .pdf 파일만 업로드 가능합니다.</p>
							</td>
						</tr>
					</table><br>
					<div id="wrtButton">
						<input type="submit" id="button" class="wrt" value="수정하기">
						<input type="reset"  id="button" class="wrt" onclick="" value="취소하기">
					</div><hr>
				</form>
			</div>
		</div>
		<div class="footer">
			<%-- <%@ include file="../../jsp/footer.jsp" %> --%>
		</div>
	</div>
</body>
</html>