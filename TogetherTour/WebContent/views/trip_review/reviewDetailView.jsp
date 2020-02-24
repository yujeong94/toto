<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="review.model.vo.*, java.util.ArrayList, member.model.vo.*"%>
    
<%
	Review review = (Review)request.getAttribute("review");
	ArrayList<Review> list = new ArrayList<Review>();
	Member loginUser = (Member)session.getAttribute("loginUser");
	
	ArrayList<Reply> rlist = (ArrayList<Reply>)request.getAttribute("list");
	String gCheck ="";
	if(review.getGuide().equals("Y")){
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
		<h2>여행 후기 & 팁 상세보기</h2>
		<hr>
		
		<form action="<%= request.getContextPath() %>/views/trip_review/reviewUpdateForm.jsp" id="detailForm" method="post">
		
			<table>
				<tr>
					<th>작성일자</th>
					<td><%= review.getCreateDate() %> <input type="hidden" name ="rnum" value="<%= review.getrNum()%>"></td>
				</tr>
				
				<tr>
					<th>조회수</th>
					<td><%= review.getrCount() %></td>
					
				</tr>
				
				<tr>
					<th>평점</th>
					<td><%= review.getrPoint() %>/5.0</td>
				</tr>
				
				
				
				<tr>
					<th>여행지</th>
					<td colspan="2"><%= review.getLocation() %> <input type="hidden" value="<%= review.getLocation() %>" name = "location"></td>
				</tr>
				
				<tr>
					<th>여행기간 </th>
					<td><%= review.getStartDate()%> - <%= review.getLastDate() %></td>
				</tr>
				
				<tr>
					<th>가이드 동행여부</th>
					<td>
					<%-- <input type="checkbox" disabled <%= gCheck%>  name="guide" value="guide"><label>(동행시 체크)</label> --%>
					<input type ="hidden" name ="guide" value="<%= review.getGuide() %>"><%= review.getGuide() %>
					</td>
				
					
				</tr>
				
				<tr>
					<th>제목</th>
					<td colspan="3"><%= review.getTitle() %> <input type="hidden" value="<%= review.getTitle()%>" name ="title"> </td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
					<textarea rows="15" cols="60" name="content" style="resize:none;" readonly><%= review.getContent() %></textarea>
					</td>
				</tr>
				
				<tr>
					<th>첨부 파일</th>
					<td><input type="file" value="파일 업로드" id="file" accept=".gif, .jpg, .jpeg, .png" multiple><br> *지원 유형:gif, .jpg, .jpeg, .png</td>
					
					
				</tr>
				
				<% if(!review.getmId().equals(loginUser.getmId())){ %>
				<tr>
					<th>추천</th>
					<td> 
						좋아요<input type="checkbox" name = "rpoint" value="rpoint"> 
					</td>
				</tr>
				 
				<tr>
					<th>팔로우 신청</th>
					<td><button type="submit" >팔로우하기</button></td>
				</tr>
				<%} %>
			</table>
			
			<div align="center">
			<% if(review.getmId().equals(loginUser.getmId())){ %>
				<input type="submit" id="updateBtn" value="수정">
				<input type="button" onclick="deleteReview();" id="deleteBtn" value="삭제">
				<% } %>  
			 	<div onclick="location.href='<%= request.getContextPath() %>/list.rv'" id="menuBtn" >메뉴로</div> 
				<%-- <input type="button" onclick="location.href='<%= request.getContextPath() %>/list.rv'" id="menuBtn" value="메뉴로">
				<input type="button" onclick="back();" id="menuBtn" value="메뉴로"> --%>
			</div>	
		</form>	
		
		<script>
			function deleteReview(){
				var bool = confirm('정말로 삭제하시겠습니까?');
				if(bool){
					$('#detailForm').attr('action', '<%= request.getContextPath() %>/delete.rv');
					$('#detailForm').submit();
				}
			}
			
			function back(){
				$('#detailForm').attr('action', '<%= request.getContextPath() %>/list.rv');
				$('#detailForm').submit();
			}
			
		</script>
	
	</div>
	

	
	<div class="contents">
	<h2>댓글</h2>
	<hr><hr>
			<div class="replyWriterArea"> <!-- 댓글작성부분 -->
				<table>
					<tr>
						<td>댓글 작성</td>
						<td><textarea rows ="3" cols = "80" id="replyContent" style = "resize:none;"></textarea></td>
						<td><button id ="addReply">댓글등록</button></td>
					</tr>
				</table>
			</div>
			
			<div id="replySelectArea">
				<hr>
				<hr>
				<table id = "replySelectTable">
					
					<% if(rlist.isEmpty()){ %>
					<tr><td colspan = "3">댓글이 없습니다.</td></tr>
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
				var rNum = <%= review.getrNum()%>;
				var content =$('#replyContent').val();
				
				$.ajax({
					url:'<%= request.getContextPath()%>/insertReply.rv',
					type:'post',
					data: {writer: writer, content:content, rNum:rNum},
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