<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나눔 게시판 수정하기</title>
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
				<h2 id="wrtTitle">나눔 게시글 작성</h2>
				<form id="wrtForm" enctype="multipart/form-data">
					<table id="wrtArea">
						<tr>
							<th id="wrtList">게시글 제목</th>
							<td colspan="4"><input type="text" placeholder="제목 입력" id="title" class="wrtInput"></td>
						</tr>
						<tr>
							<th id="wrtList">물품 종류</th>
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
								<p id="fileText">파일은 .jpg, .jpeg .png, .gif, .pdf 파일만 업로드 가능합니다.</p>
							</td>
						</tr>
					</table><br>
					<div id="wrtButton">
						<input type="submit" id="button" class="wrt" value="수정하기">
						<input type="reset"  id="button" class="wrt" onclick="" value="취소하기">
					</div><hr>
				</form>
				<script>
					
				</script>
			</div>
		</div>
		<div class="footer">
			<%-- <%@ include file="../../jsp/footer.jsp" %> --%>
		</div>
	</div>
</body>
</html>