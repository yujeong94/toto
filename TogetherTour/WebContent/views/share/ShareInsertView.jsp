<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나눔 게시판 작성하기</title>
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/common_sub.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/share/share_board.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<%@ include file="../common/header.jsp" %>
		<div class="contents">
			<div id="pageTitle">
				<h1 id="pageName">나눔 게시판</h1>&nbsp;여행하면서 서로 남은 물건들을 공유할 수 있는 곳입니다.
			</div>
			<hr>
			<div>
				<h2 id="wrtTitle">나눔 게시글 작성</h2><hr>
				<form id="wrtForm" enctype="multipart/form-data" action="<%= request.getContextPath() %>/insert.share" method="post">
					<table id="wrtArea">
						<tr>
							<th id="wrtList">게시글 제목 *</th>
							<td colspan="4"><input type="text" placeholder="제목 입력" id="title" class="wrtInput" style="width:100%;"></td>
						</tr>
						<tr>
							<th id="wrtList">물품 종류 *</th>
							<td colspan="4">
								<select id="category" class="wrtInput" style="width:100%;">
									<option value="0" disabled selected>물품 종류 선택</option>
									<option value="1">유심(USIM)</option>
									<option value="2">교통권</option>
									<option value="3">티켓(Ticket)</option>
									<option value="4">생필품(Necessity)</option>
									<option value="5">돈(Money)</option>
									<option value="6">기타(..etc)</option>
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
							<th id="wrtList">카카오톡 오픈채팅방 주소</th>
							<td colspan="4"><input type="text" placeholder="카카오톡 오픈채팅방 주소 입력" id="kakao" class="wrtInput" style="width:100%;"></td>
						</tr>
						<tr id="insertArea">
							<th id="wrtList">대표 이미지</th>
							<td> 
								<div id="titleImgArea" style="width:200px; height:200px; border:2px dashed darkgray; text-align:center; display:table-cell; vertical-align:middle;">
									<img id="titleImg" style="width:200px; height:200px; border:1px solid  black; text-align:center; display:table-cell; vertical-align:middle;" src="<%= request.getContextPath() %>/images/PageIcon.png">
								</div>
							</td>
						</tr>
						<tr>
							<th id="wrtList">내용 *</th>
							<td colspan="4">
								<textarea id="content"></textarea>
							</td>
						</tr>
						<tr id="insertArea">
							<th rowspan="2" id="wrtList">첨부파일</th>
							<td colspan="4">
								<input type="file" style="width:100%;" value="파일 업로드" id="file" accept=".gif, .jpg, .jpeg, .png, .pdf" multiple><br>
								<p id="fileText">파일은 .jpg, .jpeg .png, .gif, .pdf 파일만 업로드 가능합니다. 여러 사진을 선택하시려면 [ctrl] 키나 [shift] 키를 눌러주십시오. 최대 5개까지 입력 가능합니다.</p>
							</td>
						</tr>
					</table><hr><br>
					<div id="wrtButton">
						<button id="button" class="wrt" onclick="submitCheck();">작성하기</button>
						<button id="button" class="wrt" onclick="href=<%= request.getContextPath() %>/views/share/ShareListView.jsp">취소하기</button>
					</div><br>
				</form>
			</div>
			<div id="fileArea" style="">
				<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this)">
			</div>
		</div>
		<%@ include file="../common/footer.jsp" %>
		<script>
			// 내용 작성 부분의 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
			$(function() {
				$("#fileArea").hide() ;
				$("#titleImgArea").click(function() {
					$("#thumbnailImg1").click() ; }) ;
			});
			
			// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
			function LoadImg(value) {
				if(value.files && value.files[0]) {
					var reader = new FileReader() ;
					reader.onload = function(e) {
						$("#titleImg").attr("src", e.target.result) ; }
				} reader.readAsDataURL(value.files[0]) ;
			}
		</script>
	</div>
	<script>
		function submitCheck() {
			alert('submit') ;
			alert("물품 종류를 선택하세요.") ;
			$('#kind').focus ;
			if($('#kind').val() == "0") {
				alert("물품 종류를 선택하세요.") ;
				$('#kind').focus ;
				break ;
			} else if($('#file').length > 5) {
				alert("파일은 최대 5개까지만 업로드할 수 있습니다.") ;
				$("#file").prop('src', null);
			} else {
				$('#wrtForm').submit() ;
			}
		}
	</script>
</body>
</html>