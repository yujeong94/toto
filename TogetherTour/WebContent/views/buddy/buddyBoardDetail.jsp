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
		
	String kakao = request.getParameter("kakao");
	String KaKao = null; 
	if(kakao == null){
		kakao = "";
	} else {
		KaKao = kakao;
	}
	
	String headC = (String)request.getAttribute("headC");
	System.out.println("jsp:" + headC);
	int headC2 = 0;
	if(headC != null){
		headC2 = Integer.parseInt(headC);
	}
	
	System.out.println("headC 어디에 찍히지?" + "headC");
	String msg = (String)request.getAttribute("msg");

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
	table{width:100%; align:center}
		    
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
	    
	th{width:100px;}

	#joinBtn{background: #999999;
	 	 color: #fff;
	  	 cursor: pointer;
	  	 box-sizing: border-box;
	  	 padding : 9px 20px;
	  	 width:100px;}
	
	#Btn{margin-right:10px;}	    
 </style>

 
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="contents">
		<h2><span>동행자 상세조회</span></h2>
		<form action="<%=request.getContextPath() %>/views/buddy/buddyUpdateForm.jsp" id="detailForm" method="post">
			<table>
				<tr>
					<th>제목</th>
					<td colspan="3"><%=b.getTitle() %>
						<input type="hidden" name="bnum" value="<%= b.getBnum() %>">
						<input type="hidden" name="title" value="<%= b.getTitle() %>">
					</td>
				</tr>
				
				<tr>
					<th><label>여행지역</label></th>
					<td>국내/해외
						<label><%= kind %></label>
						<input type="hidden" name="kind" value="<%=b.getKind() %>">
					</td>
					<td>국가
						<lable><%= b.getCountry() %></lable>
						<input type=hidden name=country value="<%= b.getCountry()%>">
					</td>
					<td>도시
						<label><%= b.getCity() %></label>
						<input type=hidden name="city" value="<%= b.getCity() %>">
					</td>	
				</tr>
				<tr>
					<th>동행 날짜</th>
					<td colspan="3">
						<input type=hidden value="<%= b.getStart_date() %>" name=start_date> <%= b.getStart_date() %>
						&nbsp; ~ &nbsp;
						<input type=hidden value="<%= b.getEnd_date() %>" name=end_date> <%= b.getEnd_date() %> 
					</td>
				</tr>
				<tr>
					<th>여행테마</th>
					<td colspan="3" name="theme">
						<input type=hidden name=theme value="<%= b.getTheme() %>" ><%= b.getTheme() %>
						<%System.out.println("★theme null값인가?: " + b.getTheme()); %>
					</td>
				</tr>
				<tr>
					<th rowspan="3">동행자 모집</th>
					<td colspan="3" style="font-weight:bold;">모집인원
						<input type=hidden name=head_cnt value="<%= b.getHead_cnt() %>"> &nbsp; <%= b.getHead_cnt() %> 명
					</td>
				</tr>
				<tr>
					<td colspan="3" style="font-weight:bold;">모집성별 
						<input type=hidden name="gender" value="<%= b.getGender()%>"> &nbsp;<%= b.getGender()%>
					</td>
				</tr>
				<tr>
					<td colspan="3" name="group_age" style="font-weight:bold;">연령대
					&nbsp;
						<input type=hidden name=group_age value="<%= b.getGroup_age()%>"><%= b.getGroup_age()%>
					</td>
				</tr>
				<tr>
						<th><label>오픈카카오톡주소</label></th>
						<td colspan="3"><input type=hidden name=kakao value="<%= b.getKakao() %>"><%= b.getKakao()%></td>
					</tr>
				<tr>
					<th>여행소개</th>
					<td colspan="3" ><textarea rows="8" cols="80" style="resize:none"; name="content" readonly><%=b.getContent() %></textarea></td>
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
					console.log(headCnt);
					if(<%= headC2 %> >= headCnt) {
						alert('동행이 마감되었습니다.');
					} else {
					 	var bnum = <%= b.getBnum() %>;
					 	var userId = '<%= b.getMid() %>';
					 	var head_cnt = <%= b.getHead_cnt() %>;
					 	var msg = "<%= msg %>";
						location.href="<%= request.getContextPath() %>/insert.myBuddy?bnum=" + bnum + "&userId=" + userId;  
						if(msg != 'null'){
							alert(msg);
						}
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