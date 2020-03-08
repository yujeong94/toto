	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="buddy.model.vo.*, java.util.ArrayList"%>
<%	
	buddyBoard b = (buddyBoard)request.getAttribute("board") ; 
	
	/* String strKind = null; */

	
	String kind = null;
	if(b != null){
		if(b.getKind() == 1){
			kind = "국내";
		} else {
			kind = "해외";
		}
	}

	String headC = (String)request.getAttribute("headC");
	System.out.println(headC);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행자 상세조회 | 투투</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/index.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/common_sub.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/buddy/buddyInsertForm.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<style>
	table{width:100%}
		    
	#listBtn{padding : 9px 20px;
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
	    float:right; }
	

	#joinBtn{background: #999999;
	 	 color: #fff;
	  	 cursor: pointer;
	  	 box-sizing: border-box;
	  	 padding : 9px 20px;
	  	 width:100px;}
	
	#Btn{margin-right:10px;}	    
 </style>
 <script>
 $(function() {
	 alert('<%= b.getMid() %>') ;
 }) ;
 
 
 $(function() {
 	var strKind = "";
 	if(<%= b.getKind() %> == 1 ){
		strKind = "국내" ;
	} else {
		strKind = "해외" ;
	}
 	$('#location').val("국내/해외&nbsp; "+strKind) ;
 }) ;
 </script>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="contents">
		<h2><span>동행자 상세조회</span></h2>
		<a>여행자의 게시글을 상세 조회할 수 있는 페이지입니다.</a>
		<form action="<%=request.getContextPath() %>/views/buddy/buddyUpdateForm.jsp" id="detailForm" method="post">
			<table>
				<tr>
					<th>제목</th>
					<td colspan="3">
						<input type="hidden" name="bnum" value="<%= b.getBnum() %>"> <%= b.getTitle() %>
					</td>
				</tr>
				<tr>
					<th>여행지역</th>
					<td id=location style="font-weight:bold;">국내/해외&nbsp;<a style="font-weight:normal;"> <%= b.getKind() %></a></td>
					<td name="country" style="font-weight:bold;">국가 &nbsp;<a style="font-weight:normal;"> <%= b.getCountry() %></a></td>
					<td name="city" style="font-weight:bold;">도시 &nbsp;<a style="font-weight:normal;"><%= b.getCity() %></a></td>	
				</tr>
				<tr>
					<th>동행 날짜</th>
					<td colspan="3">
						<%=b.getStart_date() %> &nbsp; ~ &nbsp;
						<%=b.getEnd_date()%>
					</td>
				</tr>
				<tr>
					<th>여행테마</th>
					<td colspan="3" name="theme">
						<%= b.getTheme() %>
					</td>
				</tr>
				<tr>
					<th rowspan="3">동행자 모집</th>
					<td colspan="3" name="head_cnt" style="font-weight:bold;">모집인원&nbsp;<a style="font-weight:normal;"> <%=b.getHead_cnt() %></a></td>
				</tr>
				<tr>
					<td colspan="3" name="gender" style="font-weight:bold;">모집성별&nbsp;<a style="font-weight:normal;"><%= b.getGender()%></a></td>
				</tr>
				<tr>
					<td colspan="3" name="group_age" style="font-weight:bold;">연령대&nbsp; <a style="font-weight:normal;"><%=b.getGroup_age() %></a></td>
				</tr>
				<tr>
					<th>여행소개</th>
					<td colspan="3" name="content"><textarea rows="8" cols="80" style="resize:none; overflow:scroll;"><%=b.getContent() %></textarea></td>
				</tr>
			</table>
		<br>
		<br>
		<br>
		
			 <div id=btnArea align="center">
				 <% if(b.getNick().equals(loginUser.getNickName())){ %>
				<button type="submit" id="alterBtn">수정하기</button>
				
				<% if(b.getNick().equals(loginUser.getNickName()) || loginUser.getmId().equals("amdin")) { %>
				<button type=button id=delete onclick="deleteBoard();">삭제하기</button><br>
				<% } %>
				<div onclick='location.href="<%= request.getContextPath() %>/list.buddy"' id=listBtn>목록으로</div>
				<% } else { %> 
				<div onclick="join();" id=joinBtn>참가하기</div>
				<div onclick='location.href="<%= request.getContextPath() %>/list.buddy"' id=listBtn>목록으로</div>
				<% } %>
			</div>
			<script>
				function deleteBoard(){
					var bool = confirm('정말로 삭제하시겠습니까?');
					if(bool){
						$('#detailForm').attr('action', '<%= request.getContextPath() %>/delete.buddy?bnum=' + <%= b.getBnum() %>);
						$('#detailForm').submit();
					}
				}
				
		
				// 참가 버튼 클릭시
				function join(){
					var headCnt = <%= b.getHead_cnt() %> + 1;
					if(<%= headC %> > headCnt) {
						alert('동행이 마감되었습니다.');
					} else {
						alert('참가되었습니다.');
					 	var bnum = <%= b.getBnum() %>;
					 	var userId = '<%= b.getMid() %>';
					 	var head_cnt = <%= b.getHead_cnt() %>;
						location.href="<%= request.getContextPath() %>/insert.myBuddy?bnum=" + bnum + "&userId=" + userId;  
					}
				}
				
				
			</script>
	</form>
		<%System.out.println(b.getBnum()); %>
	
		<%@ include file="../common/footer.jsp" %> 
		<br> <br>
		
	</div>
</body>
</html>