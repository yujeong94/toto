<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="guide.model.vo.*,java.util.ArrayList,java.util.Date" %>
<%
	Gboard gboard = (Gboard)request.getAttribute("gboard");
	String gKind = null;
	if(gboard != null){
		if(gboard.getKind() == 1){
			gKind = "국내";
		} else {
			gKind = "해외";
		}
	}
	ArrayList<gReply> list = (ArrayList<gReply>)request.getAttribute("rList");

	//게시글 수정 알림창
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가이드 상세 | TogetherTour</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
 	table{border-top:1px solid; margin-top:100px;}
 	td,th{border-bottom:1px solid;}
 	#gDetailForm{margin-bottom:100px;}
 	#introduce{text-align:center; margin-top:50px;}
 	label{vertical-align:middle;}
 	.fixDetail{font-weight:bold; margin-right:50px;}
 	.term{margin-right:30px; vertical-aling:middle;}
 	#term2{margin-left:10px; margin-right:10px;}
 	#gBtnArea{margin-top:40px;}
 	#listBtn{
	 	padding : 9px 20px;
		border : none;
	    background: #999999;
	    color: #fff;
	    box-sizing: border-box;
	    display: inline-block;
	    align-items: flex-start;
	    letter-spacing: normal;
	    word-spacing: normal;
	    text-rendering: auto;
	    cursor: pointer;
	    vertical-align: middle;
	    font: inherit;
	    margin-top:20px;
	    float:right;
	}
	#gBtn{margin-right:10px;}
	#replySelectTable td{text-align:center;}
	
</style>
</head>
<body>
<div class='wrapper'>
	<%@ include file="../common/header.jsp" %>
	<div class='contents'>
		<h2>가이드 상세</h2>
		<form action="<%= request.getContextPath() %>/views/guide/GuideUpdate.jsp" id=gDetailForm method=post>
			<table id=gDetail>
				<tr>
					<th width=200px><label>제목</label></th>
					<td colspan=3><%= gboard.getgTitle() %>
						<input type=hidden value="<%= gboard.getgTitle() %>" name=gTitle>
						<input type=hidden value="<%= gboard.getGbNum() %>" name=gbNum>
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><%= gboard.getgWriter() %></td>
					<td><span class=fixDetail>조회수 </span><%= gboard.getgCount() %></td>
					<td><span class=fixDetail>작성일 </span><%= gboard.getEnrollDate() %></td>
				</tr>
				<tr>
					<th><label>활동장소</label></th>
					<td>
						<label class=term><%= gKind %></label>
						<input type=hidden value="<%= gKind %>" name=kind>
					</td>
					<td>	
						<label class=term><%= gboard.getCountry() %></label>
						<input type=hidden value="<%= gboard.getCountry() %>" name=country>
					</td>
					<td>	
						<label><%= gboard.getCity() %></label>
						<input type=hidden value="<%= gboard.getCity() %>" name=city>
					</td>
				</tr>
				<tr>
					<th>여행기간</th>
					<td>
						<label class=term>여행시작일</label><%= gboard.getStartDate() %>
						<input type=hidden value="<%= gboard.getStartDate() %>" name=gStart>
					</td>
					<td><label id=term2>~</label></td>
					<td>	
						<label class=term>여행종료일</label><%= gboard.getEndDate() %>
						<input type=hidden value="<%= gboard.getEndDate() %>" name=gEnd>
					</td>
				</tr>
				<tr>
					<th><label>가격</label></th>
					<td colspan=3>
					<%= gboard.getPrice() %>
					<input type=hidden value="<%= gboard.getPrice() %>" name=price> 원
					</td>
				</tr>
				<tr>
					<th><label>오픈카카오톡주소</label></th>
					<td colspan=3>
					<% if(gboard.getKakao() != null) { %>
							<a href='<%= gboard.getKakao() %>'><%= gboard.getKakao() %></a>
							<input type=hidden value="<%= gboard.getKakao() %>" name=kakao>
					<% } else { %>
							<label>없음</label>
					<% } %>
					</td>
				</tr>
			</table>
			<div id=introduce>
				<p style="font-size:12pt;">여행소개</p><br>
				<textarea rows=50 cols=140 style="resize:none;" name=gContents readonly>
				<%= gboard.getgContent() %></textarea>
			</div>
			<div id=gBtnArea align=center>
				<% if(gboard.getgWriter().equals(loginUser.getNickName())){ %>
				<button type=submit id=gBtn>수정하기</button>
				<button type=button id=delBtn onclick="deleteGboard();">삭제하기</button><br>
				<% } %>
				<div onclick='location.href="<%= request.getContextPath() %>/list.guide"' id=listBtn>목록으로</div>
			</div>
		</form>
		<div class=replyArea>
			<div class=replyWriterArea> <!-- 댓글 작성 부분 -->
				<table>
					<tr>
						<td>댓글 작성</td>
						<td><textarea rows=3 cols=80 id=replyContent stype="resize:none;"></textarea></td>
						<td><button id=addReply>댓글등록</button></td>
				</table>
			</div>
		</div>
		<div id=replySelectArea> <!-- 댓글 조회부분 -->
			<table id=replySelectTable>
				<% if(list.isEmpty()){ %>
					<tr><td colspan=3>댓글이 없습니다.</td></tr>
				<% } else { %>
					<% for(int i = 0; i < list.size(); i++) { %>
						<tr>
							<td width=100px><%= list.get(i).getWriter() %></td>
							<td width=400px><%= list.get(i).getGrContent() %></td>
							<td width=200px><%= list.get(i).getCreatDate() %></td>
						</tr>
					<% } %>
				<% } %>
			</table>
		</div>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
<script>
	function deleteGboard() {
		var bool = confirm("정말로 삭제하시겠습니까?");
		
		if(bool) {
			$('#gDetailForm').attr('action','<%= request.getContextPath() %>/delete.guide?gNum=' + <%= gboard.getGbNum() %>);
			$('#gDetailForm').submit();
		}
	}
	
	$('#addReply').click(function(){
		var writer ='<%= loginUser.getmId() %>';
		var gbNum = <%= gboard.getGbNum() %>;
		var content = $('#replyContent').val();
		
		$.ajax({
			url: '<%= request.getContextPath() %>/insertReply.guide',
		 	type: 'post',
		 	data: {writer: writer, content: content, gbNum:gbNum},
		 	success: function(data){
		 		$replyTable = $('#replySelectTable');
		 		$replyTable.html("");
		 		for(var key in data){
		 			var $tr = $('<tr>');
		 			var $writerTd = $('<td>').text(data[key].writer).css('width','100px');
		 			var $contentTd = $('<td>').text(data[key].grContent).css('width','400px');
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
	
	var msg = "<%= msg %>";
	
	$(function(){
		if(msg != "null"){
			alert(msg);
		}
	});
</script>
</body>
</html>