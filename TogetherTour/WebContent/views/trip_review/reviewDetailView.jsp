
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="review.model.vo.*, java.util.ArrayList, member.model.vo.*, follow.model.vo.*"%>
    
<%
	Review review = (Review)request.getAttribute("review");
	ArrayList<Review> list = new ArrayList<Review>();
	/* Member loginUser = (Member)session.getAttribute("loginUser"); */
	
	ArrayList<Reply> rlist = (ArrayList<Reply>)request.getAttribute("list");
	String gCheck ="";
	if(review.getGuide().equals("Y")){
		gCheck="checked";
	}
	
	String rKind = null;
	if(review != null){
		if(review.getKind() == 1){
			rKind = "국내";
		} else {
			rKind = "해외";
		}
	}
	
	ArrayList<Follow> flist = (ArrayList<Follow>)request.getAttribute("flist");
	
	ArrayList<rAttachment> fileList = (ArrayList<rAttachment>)request.getAttribute("fileList");
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
	.ImgArea{display:inline-block; border:1px dashed rgb(203,203,203); width:200px; height:200px; margin-right:10px;}
	#ReplydeleteBtn{cursor:pointer; background:gray; color:white; text-align:center;}
</style>
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
					<td><%= review.getCreateDate() %> <input type="hidden" id ="rnum" name ="rnum" value="<%= review.getrNum()%>"></td>
				</tr>
				
				<tr>
					<th>작성자</th>
					<td><%= review.getmId() %></td>
				</tr>
				
				
				
				
				
				<tr>
					<th>추천수</th>
					<td><%= review.getrPoint() %> <input type="hidden" id ="rPoint" name ="rPoint" value="<%= review.getrPoint()%>"></td>
				</tr>
				
				
				<tr>
					<th>조회수</th>
					<td><%= review.getrCount() %></td>
					
				</tr>
				
				
				
				<%-- 
				<tr>
					<th>여행지</th>
					<td colspan="2"><%= review.getLocation() %> <input type="hidden" value="<%= review.getLocation() %>" name = "location"></td>
				</tr> --%>
				
				<tr>
					<th><label>여행지</label></th>
					<td>
						[<label class=term><%= rKind %></label>]
						<input type=hidden value="<%= rKind %>" name=kind>
						
						<label class=term><%= review.getCountry() %></label>
						<input type=hidden value="<%= review.getCountry() %>" name=country>-
						
						<label><%= review.getCity() %></label>
						<input type=hidden value="<%= review.getCity() %>" name=city>
					</td>
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
					<textarea rows="20" cols="130" name="content" style="resize:none;" readonly><%= review.getContent() %></textarea>
					</td>
				</tr>
			
				
				<% if(!review.getmId().equals(loginUser.getmId())){ %>
				<tr>
					<th>추천</th>
					<td> 
						<input type="button" id="good" value= "추천하기">
					</td>
				</tr>
				 
				<tr>
					<th>팔로우 신청</th>
					<td>
						<input type="button" id="follow" value= "팔로우하기">
					</td>
				</tr>
				<%} %>
				
				
				<tr>
					<th>사진 </th>
					<td colspan="3">
						<% for(int i = 0; i<fileList.size(); i++){ %>
						<div class="ImgArea" style="overflow:hidden" >
						<img id="detailImg" class="detailImg" src="<%= request.getContextPath() %>/uploadFiles/<%=fileList.get(i).getChangeName() %>" width="200" height="200">
						<input type="hidden" value="<%= fileList.get(i).getChangeName() %>" name="detailImg<%= i %>">
						<input type="hidden" value="<%= fileList.get(i).getfId() %>" name="detailImgId<%= i %>">
						</div>
						<% } %>
					</td>	
				
				</tr>
			</table>
			
			
			
			<div align="center">
			<% if(review.getmId().equals(loginUser.getmId()) || loginUser.getmId().equals("admin")){ %>
				<% if(review.getmId().equals(loginUser.getmId())){ %>
				<input type="submit" id="updateBtn" value="수정">
				<%} %>
				<input type="button" onclick="deleteReview();" id="deleteBtn" value="삭제">
				<% } %>  
			 	<%-- <div onclick="location.href='<%= request.getContextPath() %>/list.rv'" id="menuBtn" >메뉴로</div> 
				<input type="button" onclick="location.href='<%= request.getContextPath() %>/list.rv'" id="menuBtn" value="메뉴로"> --%>
				<input type="button" onclick="back();" id="menuBtn" value="메뉴로">
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
			
			$('#good').click(function(){
				var rnum = $('#rnum').val();
				var rPoint = $('#rPoint').val();
				
				$.ajax({
					url:'<%=request.getContextPath()%>/good.rv',
					type:'post',
					data:{rnum : rnum,rPoint:rPoint},
					success:function(data){
						alert("<%= review.getmId()%>님의 게시물을 추천하셨습니다.");
						$('#good').val('추천됨');
						$('#good').attr("disabled","disabled");
					}
				});
			});
			
			
			$('#follow').click(function(){
				var rnum = $('#rnum').val();
				var mId ='<%= loginUser.getmId()%>';
				var fId = '<%= review.getmId()%>';
				$.ajax({
					url:'<%=request.getContextPath()%>/insert.fo',
					type:'post',
					data:{mId : mId,fId:fId, rnum:rnum},
					success:function(data){
						alert("<%= review.getmId()%>님을 팔로우 하셨습니다.");
						$('#follow').val('팔로우됨');
						$('#follow').attr("disabled","disabled");
					}
				});
			});
			
			
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
			
			<form action="<%= request.getContextPath() %>" id="ReplyForm" method="post">
			<div id="replySelectArea">
				<hr>
				<hr>
				<table id = "replySelectTable">
					
					<% if(rlist.isEmpty()){ %>
					<tr><td colspan = "3">댓글이 없습니다.</td></tr>
					<%} else{ %>
						<% for(int i = 0; i < rlist.size(); i++) { %>
	
							<tr>
								<td width="50px"><%= rlist.get(i).getrWriter() %><input type="hidden" name = "rId" value="<%= rlist.get(i).getrId() %>"></td>
								<td width="300px"><%= rlist.get(i).getrContent() %><input type="hidden" id ="rnum" name ="rnum" value="<%= review.getrNum()%>"></td>
								<td width="100px"><%= rlist.get(i).getCreateDate() %></td>
								<% if(rlist.get(i).getrWriter().equals(loginUser.getmId()) || loginUser.getmId().equals("admin")){ %>
								<td width="10px" id ="Replydelete"><div onclick="deleteReviewReply();" id="ReplydeleteBtn">댓글 삭제</div></td>
								<%} %>
							</tr>
						<%	} %>	
					<%} %>
				</table>
			</div>
			</form>
		</div>
		
		
		<script>
			function deleteReviewReply(){
				var bool = confirm('댓글을 정말로 삭제하시겠습니까?');
				if(bool){
					$('#ReplyForm').attr('action', '<%= request.getContextPath() %>/Rdelete.rv');
					$('#ReplyForm').submit();
				}
			} 
			
				
		</script>
		
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
							var $writerTd = $('<td>').text(data[key].rWriter).css('width','50px');
							var $contentTd = $('<td>').text(data[key].rContent).css('width','300px');
							var $dateTd = $('<td>').text(data[key].createDate).css('width','100px');
							
							
							$tr.append($writerTd);
							$tr.append($contentTd);
							$tr.append($dateTd);
							
							/* if(writer.equals(loginUser.getmId())){
								$tr.append('#Replydelete');
							} */
							
								
							$replyTable.append($tr);
						} 
						$('#replyContent').val('');


						window.location.reload();
					}
				});
			});
		</script>
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>