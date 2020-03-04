<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="share.model.vo.*, java.util.*"%>
<%
	Share share = (Share)request.getAttribute("share") ;
	ArrayList<Sattachment> fileList = (ArrayList<Sattachment>)request.getAttribute("fileList") ;
	ArrayList<sReply> rList = (ArrayList<sReply>)request.getAttribute("rList") ;
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">  
	<title>나눔 게시판 조회하기</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common_sub.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/share/share_board.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<%@ include file="../common/header.jsp"%>
		<div class="contents">
			<h2><span>나눔 게시판 상세조회</span></h2><hr>
			<form id="detailForm" action="<%=request.getContextPath()%>/views/share/ShareUpdateView.jsp" method="post">
				<table id="detailArea">
					<tr>
						<th id="detailList">게시글 번호</th>
						<td colspan="4">
							<input type="text" name="sbNum" value="<%=share.getSbNum()%>" style="width:100%;" readonly>
						</td>
					</tr>
					<tr>
						<th id="detailList">게시글 제목</th>
						<td colspan="4">
							<input type="text" name="title" value="<%=share.getTitle()%>" style="width:100%; font-weight:bold;" readonly>
						</td>
					</tr>
					<tr>
						<th id="detailList">물품 종류</th>
						<td colspan="4">
							<input type="text" name="category" value="<%=share.getCategory()%>" style="width:100%; font-weight:bold;" readonly>
						</td>
					</tr>
					<tr>
						<th id="detailList">작성자 닉네임</th>
						<td colspan="4">
							<input type="text" name="nick" value="<%=share.getWriter()%>" style="width:100%; font-weight:bold;" readonly>
						</td>
					</tr>
					<tr>
						<th id="detailList">카카오톡 주소</th>
						<td colspan="4">
							<input type="text" name="kakao" value="<%=share.getKakao()%>" style="width:100%; font-weight:bold;" readonly>
						</td>
					</tr>
					<tr>
						<th id="detailList">거래 상태</th>
						<td colspan="4">
							<input type="text" name="stName" value="<%=share.getStName()%>" style="width:100%; font-weight:bold;" readonly>
						</td>
					</tr>
					<tr>
						<th id="wrtList">내용</th>
						<td colspan="4">
							<textarea name="content" style="width:100%; height:300px; font-weight:bold; resize:none;" readonly><%=share.getContent()%></textarea>
						</td>
					</tr>
					<tr>
						<th rowspan="2" id="wrtList">사진</th>
						<td colspan="4">
							<% if(fileList.size() == 0) {%>
								<input type="text" value="작성자가 업로드한 사진이 없습니다." style="width:100%; font-weight:bold;" readonly>
							<% } else {%>
								<% for(int i=0; i<fileList.size(); i++) {%>
									<div style="width:<%=100/fileList.size()%>%;">
										<img src="<%=request.getContextPath()%>/uploadFiles/<%=fileList.get(i).getChangeName()%>" id="detailImage<%=i%>">
										<input type="hidden" value="<%=fileList.get(i).getChangeName()%>" name="detailImg<%=i%>">
										<input type="hidden" value="<%=fileList.get(i).getfId()%>" name="detailImgId<%=i%>"><br>
									</div>
								<% }%>
							<% }%>
						</td>
					</tr>
				</table><br>
				<div align="center">
					<% if(loginUser.getNickName().equals(share.getWriter())) { %>
						<input type="submit" id="button" class="wrt" value="수정하기">
						<input type="button" onclick="deleteShare();" id="button" class="wrt" value="삭제하기">
					<% } %>
					<input type="button" id="button" class="wrt" onclick="location.href='<%=request.getContextPath()%>/list.share'" value="돌아가기">
				</div><br><hr>
			</form>
			<div class=replyArea>
				<div class=replyWriterArea> <!-- 댓글 작성 부분 -->
					<table>
						<tr>
							<td>댓글 작성</td>
							<td><textarea rows=3 cols=80 id=replyContent style="resize:none;"></textarea></td>
							<td><button id=addReply>댓글등록</button></td>
						</tr>
					</table><hr>
				</div>
			</div>
			<div id=replySelectArea> <!-- 댓글 조회부분 -->
				<table id=replySelectTable>
					<% if(rList.isEmpty()){ %>
						<tr>
							<td colspan=3>댓글이 없습니다.</td>
						</tr>
					<% } else { %>
						<% for(int i = 0; i < rList.size(); i++) { %>
							<tr>
								<td width=100px><%= rList.get(i).getWriter() %></td>
								<td width=400px><%= rList.get(i).getContent() %></td>
								<td width=200px><%= rList.get(i).getCreateDate() %></td>
							</tr>
						<% } %>
					<% } %>
				</table><hr>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<script>
		function deleteShare() {
			var bool = confirm('정말로 삭제하시겠습니까?') ;
			if(bool) {
				$('#detailForm').attr('action', 'delete.share');
				$('#detailForm').submit() ;
			} else {
				alert('취소하셨습니다.') ;
			}
		}
		
		$('#addReply').click(function() {
			var writer ='<%= loginUser.getmId() %>' ;
			var sbNum = <%= share.getSbNum() %> ;
			var content = $('#replyContent').val() ;
			if(!content) {
				alert('내용을 입력하세요.') ;
				$('#replyContent').focus() ;
			} else {
				$.ajax({
					url: '<%= request.getContextPath() %>/insertReply.share',
				 	type: 'post',
				 	data: {writer:writer, content:content, sbNum:sbNum},
				 	success: function(data) {
				 		$replyTable = $('#replySelectTable') ;
				 		$replyTable.html("") ;
				 		
				 		for(var key in data) {
				 			var $tr = $('<tr>') ;
				 			var $writerTd = $('<td>').text(data[key].rWriter).css('width','100px') ;
				 			var $contentTd = $('<td>').text(data[key].rContent).css('width','400px') ;
				 			var $dateTd = $('<td>').text(data[key].createDate).css('width','200px') ;
				 			
				 			$tr.append($writerTd) ;
				 			$tr.append($contentTd) ;
				 			$tr.append($dateTd) ;
				 			$replyTable.append($tr) ;
				 		}
				 		$('#replyContent').val('') ; },
				 	error: function(data) {
				 		alert('Ajax ERROR') ;
				 		alert('writer : '+writer) ;
				 		alert('content : '+content) ;
				 		alert('sbNum : '+sbNum) ; },
				 	complete: function(data) {
				 		alert('Ajax COMPLETE') ;
				 		alert('writer : '+writer) ;
				 		alert('content : '+content) ;
				 		alert('sbNum : '+sbNum) ; }
				}) ;
			}
		}) ;
	</script>
</body>
</html>