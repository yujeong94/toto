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
			<form id="uptForm" enctype="multipart/form-data" action="<%= request.getContextPath() %>/update.share?sbNum=<%=sbNum%>" method="post">
				<table id="wrtArea">
					<tr>
						<th id="wrtList">게시글 제목 * </th>
						<td colspan="4">
							<input type="hidden" id="sbNum" value="<%=sbNum%>" class="wrtInput">
							<input type="text" placeholder="제목 입력" id="title" name="title" value="<%=title%>" class="wrtInput" required>
						</td>
					</tr>
					<tr>
						<th id="wrtList">작성자</th>
						<td colspan="4">
							<input type="text" value="<%=loginUser.getmId()%>" id="mId" name="mId" class="wrtInput" readonly>
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
							<select id="category" name="category" class="wrtInput" required>
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
						<th id="wrtList">거래 장소 *</th>
						<td colspan="4">
							<select name=kind id=kindList class="location" required>
								<option value="0">분류</option>
							</select>
							<select name=country id=country class="location" required>
								<option value="0">국가</option>
							</select>
							<select name=city id=city class="location">
								<option value="0">도시</option>
							</select>
						</td>
					</tr>
					<tr>
						<th id="wrtList">오픈채팅방 주소</th>
						<td colspan="4">
							<input type="text" placeholder="연락처 입력" id="kakao" name="kakao" class="wrtInput" value="<%=kakao%>" style="width:100%;">
						</td>
					</tr>
					<tr>
						<th id="wrtList">거래 상태 *</th>
						<td colspan="4">
							<script>
								$(function() {
									var stName = "<%=stName%>" ;
									switch(stName) {
									case "거래가능" : $('#stName1').prop("selected",true) ; break ;
									case "거래진행중" : $('#stName2').prop("selected",true) ; break ;
									case "거래완료" : $('#stName3').prop("selected",true) ; break ;
									}
								})
							</script>
							<select id="stNum" name="stNum">
								<option value="1" id="stName1" selected>거래 가능</option>
								<option value="2" id="stName2" selected>거래 진행중</option>
								<option value="3" id="stName3"selected>거래 완료</option>
							</select>
						</td>
					</tr>
					<tr>
						<th id="wrtList">내용 *</th>
						<td colspan="4">
							<textarea id="content" name="content"><%=content%></textarea>
						</td>
					</tr>
					<tr id="insertArea">
						<th id="wrtList">첨부파일</th>
						<td colspan="4">
							<input type="file" id="shareImg1" name="shareImg1" accept=".gif, .jpg, .jpeg, .png" name="shareImg1" class="shareImg" onchange="LoadImg(this,1)">
							<input type="file" id="shareImg2" name="shareImg2" accept=".gif, .jpg, .jpeg, .png" name="shareImg2" class="shareImg" onchange="LoadImg(this,2)">
							<input type="file" id="shareImg3" name="shareImg3" accept=".gif, .jpg, .jpeg, .png" name="shareImg3" class="shareImg" onchange="LoadImg(this,3)">
							<input type="file" id="shareImg4" name="shareImg4" accept=".gif, .jpg, .jpeg, .png" name="shareImg4" class="shareImg" onchange="LoadImg(this,4)">
							<input type="file" id="shareImg5" name="shareImg5" accept=".gif, .jpg, .jpeg, .png" name="shareImg5" class="shareImg" onchange="LoadImg(this,5)"><br><br>
							<img id="contentImg1" class="contentImg" src=""><br><br>
							<img id="contentImg2" class="contentImg" src=""><br><br>
							<img id="contentImg3" class="contentImg" src=""><br><br>
							<img id="contentImg4" class="contentImg" src=""><br><br>
							<img id="contentImg5" class="contentImg" src=""><br><br>
							<p id="fileText">파일은 .jpg, .jpeg, .png, .gif 파일만 업로드 가능합니다. 최대 5개까지 입력 가능합니다.</p>
							<input type="hidden" id="fileBool2" name="fileBool2" value="trueFiles">
						</td>
					</tr>
				</table><br>
				<div id="wrtButton">
					<input type="button" id="button" class="wrt" value="수정하기" onclick="submitCheck();">
					<input type="button" id="button" class="wrt" onclick="location.href='<%=request.getContextPath()%>/detail.share?sbNum=<%=sbNum%>'" value="취소하기">
				</div><hr>
			</form>
			<script>
				$(function() {
					$.ajax({
						url: '<%= request.getContextPath() %>/list.loca',
					 	type: 'post',
					 	success: function(data) {
					 		$selectKind = $('#kindList') ;
							$selectCountry = $('#country') ;
							$selectCity = $('#city') ;
							for(var i in data[0]) {
								var $option = $('<option>') ;
								$option.val(data[0][i]) ;
								var con = null ;
								if(data[0][i] == 1)	con = "국내";
								else				con = "해외";
								$option.text(con) ;
								$selectKind.append($option) ;
							}
							
							// county변경
							$('#kindList').change(function() {
								var kindSel = $('#kindList option:selected').text() ;
								if(kindSel == "국내") {
									$('#country').find('option').remove() ;
									var $option = $('<option>') ;
									$option.val('국가') ;
									$option.text('국가') ;
									$selectCountry.append($option) ;
									var $option = $('<option>') ;
									$option.val('한국') ;
									$option.text('한국') ;
									$selectCountry.append($option) ;
								} else if(kindSel == "해외") {
									$('#country').find('option').remove() ;
									var $option = $('<option>') ;
									$option.val('국가') ;
									$option.text('국가') ;
									$selectCountry.append($option) ;
									for(var i in data[1]) {
										var $option = $('<option>') ;
										$option.val(data[1][i]) ;
										$option.text(data[1][i]) 
										$selectCountry.append($option) ;
									}
								} else {
									$('#country').find('option').remove() ;
									var $option = $('<option>') ;
									$option.text('국가') ;
									$selectCountry.append($option) ;
								}
							}) ;
							// 해외city변경
							$('#country').change(function() { 
								var countrySel = $('#country option:selected').text() ;
								$('#city').find('option').remove() ;
								for(var i in data[2]) {
									if(data[2][i].country == countrySel) {
										var $option = $('<option>') ;
										$option.val(data[2][i].city) ;
										$option.text(data[2][i].city) ;
										$selectCity.append($option) ;
									}
								}
								$('#kindList').change(function() {
									$('#city').find('option').remove() ;
									var $option = $('<option>') ;
									$option.val('도시') ;
									$option.text('도시') ;
									$selectCity.append($option) ;
								}) ;
							}) ;
							
							// 국내 city : 서울 자동선택
							$('#country').change(function() {
								var countryVal = $('#country').val() ;
								if(countryVal == '한국')
									$('#city option:eq(4)').prop('selected',true) ;
							}) ;
					 	},
					 	error: function(data) {
					 		console.log('error') ;
					 	}
					}) ;
				}) ;
				
				// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
				function LoadImg(value, num) {
					if(value.files && value.files[0]) {
						var reader = new FileReader() ;
						
						reader.onload = function(e) {								
							switch(num) {
							case 1 : $("#contentImg1").attr("src", e.target.result) ; break ;
							case 2 : $("#contentImg2").attr("src", e.target.result) ; break ;
							case 3 : $("#contentImg3").attr("src", e.target.result) ; break ;
							case 4 : $("#contentImg4").attr("src", e.target.result) ; break ;
							case 5 : $("#contentImg5").attr("src", e.target.result) ; break ;
							}
						}
						reader.readAsDataURL(value.files[0]) ;
					}
				}
				
				$(function() {
					$('#content').attr('maxlength', 2000) ;
					var content = $('#content') ;
					content.bind("keydown keyup click", function() {
						var max = $(this).attr('maxlength') ;
						if($(this).val().length > max)
							$(this).val($(this).val().substr(0, max)) ;
					}) ;
				}) ;
				
				function submitCheck() {
					var bool = true ;
					var fileCheck1 = $('#shareImg1').val() ;
					var fileCheck2 = $('#shareImg2').val() ;
					var fileCheck3 = $('#shareImg3').val() ;
					var fileCheck4 = $('#shareImg4').val() ;
					var fileCheck5 = $('#shareImg5').val() ;
					
					if($('#title').val() == "") {
						alert('제목을 입력하세요.') ;
						$('#title').focus() ;
						bool = false ;
					} else if($('#category').val() == "0") {
						alert('물품 종류를 선택하세요.') ;
						bool = false ;
					} else if($('#kind').val() == 0 || $('#country').val() == 0 || $('#city').val() == 0) {
						alert('거래 장소를 입력하세요.') ;
						bool = false ;
					} else if($('#stNum').val() == "")
					alert($('#kind').val()) ;
					if($('#kakao').val() == "") {
						$('#kakao').text("내용이 입력되지 않았습니다.") ;
						alert("Kakao : "+$('#kakao').val()) ;
					}
					
					if(fileCheck1 == "" && fileCheck2 == "" && fileCheck3 == "" && fileCheck4 == "" && fileCheck5 == "") {
						$('#fileBool2').val("falseFiles") ;
					} else {
						$('#fileBool2').val("trueFiles") ;
					}
					
					if(bool) $('#uptForm').submit() ;
				}
			</script>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>