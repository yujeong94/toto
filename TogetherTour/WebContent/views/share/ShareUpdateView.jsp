<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList"%>
<%
	int sbNum = Integer.parseInt(request.getParameter("sbNum")) ;
	String title = request.getParameter("title") ;
	String category = request.getParameter("category") ;
	String kakao = request.getParameter("kakao") ;
	String content = request.getParameter("content") ;
	String stName = request.getParameter("stName") ;
%>
<!DOCTYPE html>
<html> 
<head>
	<meta charset="UTF-8">  
	<title>나눔 게시판 수정하기</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common_sub.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/share/share_board.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head> 
<body>
	<div id="wrapper">
		<%@ include file="../common/header.jsp"%>
		<div class="contents">
			<h2><span>나눔 게시판 수정</span></h2>
			<hr>
			<form id="uptForm" enctype="multipart/form-data" action="<%= request.getContextPath() %>/update.share" method="post">
				<table id="wrtArea">
					
					<tr>
						<th id="wrtList">게시글 제목 * </th>
						<td colspan="4">
							<input type="hidden" value="" id="sbNum" value="<%=sbNum%>" class="wrtInput">
							<input type="text" placeholder="제목 입력" id="title" value="<%=title%>" class="wrtInput">
						</td>
					</tr>
					<tr>
						<th id="wrtList">물품 종류 * </th> 
						<td colspan="4">
							<script>
								$(function() {
									var categoryStr = "<%=category%>" ;
									switch(categoryStr) {
									case "유심(USIM)" : $('#category1').prop("selected",true) ; break ;
									case "교통권(Traffic)" : $('#category2').prop("selected",true) ; break ;
									case "티켓(Ticket)" : $('#category3').prop("selected",true) ; break ;
									case "생필품(Neccessity)" : $('#category4').prop("selected",true) ; break ;
									case "돈(Money)" : $('#category5').prop("selected",true) ; break ;
									case "기타(Etc)" : $('#category6').prop("selected",true) ; break ;
									}
								}) ;
							</script>
							<select id="category" class="wrtInput">
								<option id="category1" value="1">유심(USIM)</option>
								<option id="category2" value="2">교통권</option>
								<option id="category3" value="3">티켓(ticket)</option>
								<option id="category4" value="4">생필품(necessity)</option>
								<option id="category5" value="5">돈(money)</option>
								<option id="category6" value="6">기타(..etc)</option>
							</select>
						</td>
					</tr>
					<tr>
						<th id="wrtList">거래 장소 * : 미완</th>
						<td colspan="4">
							<select id="kind" style="width:33%;">
								<option value="1">국내</option>
								<option value="2">국외</option>
							</select>
							<select id="country" style="width:33%;">
								<option value="한국">한국</option>
							</select>
							<select id="city" style="width:33%;">
								<option value="서울">서울</option>
							</select>
						</td>
					</tr>
					<tr>
						<th id="wrtList">연락처</th>
						<td colspan="4">
							<input type="text" placeholder="연락처 입력" id="kakao" class="wrtInput" value="<%=kakao%>" style="width:100%;">
						</td>
					</tr>
					<tr>
						<th id="wrtList">거래 상태</th>
						<td colspan="4">
							<script>
								$(function() {
									var stName = "<%=stName%>" ;
									switch(categoryStr) {
									case "거래가능" : $('#stName1').prop("selected",true) ; break ;
									case "거래진행중" : $('#stName2').prop("selected",true) ; break ;
									case "거래완료" : $('#stName3').prop("selected",true) ; break ;
									}
								})
							</script>
							<select id="stNum">
								<option value="1" id="stName1" selected>거래 가능</option>
								<option value="2" id="stName2" selected>거래 진행중</option>
								<option value="3" id="stName3"selected>거래 완료</option>
							</select>
						</td>
					</tr>
					<tr>
						<th id="wrtList">내용 *</th>
						<td colspan="4">
							<textarea id="content"><%=content%></textarea>
						</td>
					</tr>
					<tr id="insertArea">
						<th rowspan="2" id="wrtList">첨부파일</th>
						<td colspan="4">
							<input type="file" style="width:100%;" value="파일 업로드" id="file" accept=".gif, .jpg, .jpeg, .png, .pdf" multiple><br>
							<p id="fileText">파일은 .jpg, .jpeg .png, .gif, .pdf 파일만 업로드 가능합니다. 여러 사진을 선택하시려면 [ctrl] 키나 [shift] 키를 눌러주십시오. 최대 5개까지 입력 가능합니다.</p>
						</td>
					</tr>
				</table><br>
				<div id="wrtButton">
					<input type="button" id="button" class="wrt" value="수정하기">
					<input type="button" id="button gobackBtn" class="wrt" onclick="location.href='<%=request.getContextPath()%>/detail.share?sbNum=<%=sbNum%>'" value="취소하기">
				</div><hr>
			</form>
			<div id="fileArea" style="">
				<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this)">
			</div>
			<script>
				function submitCheck() {
					if($('#category').val() == "0") {
						alert("물품 종류를 선택하세요.") ;
						$('#category').focus ;
						break ;
					} else if($('#file').length > 5) {
						alert("파일은 최대 5개까지만 업로드할 수 있습니다.") ;
						$("#file").prop('src', null);
					} else {
						$('#wrtForm').submit() ;
					}
				}
			</script>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>