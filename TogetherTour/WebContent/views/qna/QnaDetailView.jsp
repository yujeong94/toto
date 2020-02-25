<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="qna.model.vo.*, java.util.ArrayList"%>
    
<%
	Qna qna = (Qna)request.getAttribute("qna");
	ArrayList<Qna> list = new ArrayList<Qna>(); 
	
	ArrayList<Reply> rlist = (ArrayList<Reply>)request.getAttribute("list");
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
		<h2>질문 상세보기</h2>
		<hr>
		<form action="<%= request.getContextPath() %>/views/qna/QnaUpdateForm.jsp" id="detailForm" method="post">
			<table>
				<tr>
					<th>제목 <input type="hidden" id ="title" name ="title" value="<%= qna.getTitle()%>"></th>
					<td><%= qna.getTitle() %> <input type="hidden" id ="qnum" name ="qnum" value="<%= qna.getqNum()%>"></td>
				</tr>
				
				<tr>
					<th>작성자</th>
					<td><%= qna.getmId() %> <input type="hidden" id ="mId" name ="mId" value="<%= qna.getmId()%>"></td>
				</tr>
				
				<tr>
					<th>조회수</th>
					<td><%= qna.getqCount() %></td>
				</tr>
				
				<tr>
					<th>작성일자</th>
					<td><%= qna.getCreateDate() %><input type="hidden" id ="createDate" name ="createDate" value="<%= qna.getCreateDate()%>"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td colspan="3">
					<textarea rows="15" cols="60" name="content" style="resize:none;" readonly><%= qna.getContent() %></textarea>
					</td>
				</tr>
							
			</table>
			<div align="center">
			<% if(qna.getmId().equals(loginUser.getmId())){ %>
				<input type="submit" id="updateBtn" value="수정">
				<input type="button" onclick="deleteQna();" id="deleteBtn" value="삭제">
				<% } %>  
			 	<div onclick="location.href='<%= request.getContextPath() %>/list.sh'" id="menuBtn" >메뉴로</div> 
			</div>	
		</form>
		
		<script>
			function deleteQna(){
				var bool = confirm('정말로 삭제하시겠습니까?');
				if(bool){
					$('#detailForm').attr('action', '<%= request.getContextPath() %>/delete.qna');
					$('#detailForm').submit();
				}
			}
			
			
		</script>
		
	</div>
	
	<div class="contents">
	<h2>답변</h2>
	
		<% if(loginUser.getmId().equals("admin")){ %>
		<hr><hr>
			<div class="replyWriterArea"> <!-- 댓글작성부분 -->
				
				<table>
					<tr>
						<td>답변 작성</td>
						<td><textarea rows ="3" cols = "80" id="replyContent" style = "resize:none;"></textarea></td>
						<td><button id ="addReply">답변작성</button></td>
					</tr>
				</table>
				
			</div>
			<%} else{ %>
			<%} %>
			
			<div id="replySelectArea">
				<hr>
				<hr>
				<table id = "replySelectTable">
					
					<% if(rlist.isEmpty()){ %>
					<tr><td colspan = "3">아직 답변이 없습니다.</td></tr>
					<%} else{ %>
						<% for(int i = 0; i < rlist.size(); i++) { %>
	
							<tr>
								<td width="100px"><%= rlist.get(i).getrWriter() %></td>
								<td width="100px"><%= rlist.get(i).getrContent() %></td>
								<td width="100px"><%= rlist.get(i).getCreateDate() %></td>
							</tr>
						<%	} %>	
					<%} %>
				</table>
			</div>
		</div>
		
		
		<script>
			$('#addReply').click(function(){
				var writer = '<%=  loginUser.getmId() %>';
				var qNum = <%= qna.getqNum()%>;
				var content =$('#replyContent').val();
				
				$.ajax({
					url:'<%= request.getContextPath()%>/insertReply.qna',
					type:'post',
					data: {writer: writer, content:content, qNum:qNum},
					success:function(data){
						$replyTable = $('#replySelectTable');
						$replyTable.html("");
						
						for(var key in data){
							var $tr = $('<tr>');
							var $writerTd = $('<td>').text(data[key].rWriter).css('width','100px');
							var $contentTd = $('<td>').text(data[key].rContent).css('width','400px');
							var $dateTd = $('<td>').text(data[key].createDate).css('width','200px');
							
							$tr.append($writerTd);
							$tr.append($contentTd);
							$tr.append($dateTd);
							$replyTable.append($tr);
					
						}
						$('#replyContent').val('');
					}
				});
			});
		</script>
	
	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>