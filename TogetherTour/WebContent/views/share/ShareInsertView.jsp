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
			<div>
				<h2><span>나눔 게시글 작성</span></h2><hr>
				<form id="wrtForm" enctype="multipart/form-data" action="<%= request.getContextPath() %>/insert.share" method="post">
					<table id="wrtArea">
						<tr>
							<th id="wrtList">게시글 제목 *</th>
							<td colspan="4">
								<input type="hidden" name="mid" value="<%=loginUser.getmId()%>">
								<input type="text" placeholder="제목을 입력하세요. 최소 1글자, 최대 글자는 50글자입니다." id="title" name="title" class="wrtInput" maxlength="50" style="width:100%;">
							</td>
						</tr>
						<tr>
							<th id="wrtList">물품 종류 *</th>
							<td colspan="4">
								<select id="category" name="category" class="wrtInput" style="width:100%;">
									<option value="0" selected>물품 종류 선택</option>
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
							<th id="wrtList">거래 장소 *</th>
							<td colspan="4">
								<select name=kind id=kindList class="location">
									<option>분류</option>
								</select>
								<select name=country id=country class="location">
									<option>국가</option>
								</select>
								<select name=city id=city class="location">
									<option>도시</option>
								</select>
							</td>
						</tr>
						<tr>
							<th id="wrtList">카카오톡 주소</th>
							<td colspan="4"><input type="text" placeholder="카카오톡 오픈채팅방 주소 입력" id="kakao" name="kakao" class="wrtInput" style="width:100%;"></td>
						</tr>
						<tr>
							<th id="wrtList">내용 *</th>
							<td colspan="4">
								<textarea id="content" name="content" placeholder="최소 1글자, 최대 2000글자"></textarea>
							</td>
						</tr>
						<tr id="insertArea">
							<th id="wrtList">첨부파일</th>
							<td colspan="4">
								<!-- <input type="file" style="width:100%;" value="파일 업로드" id="file" name="file" accept=".gif, .jpg, .jpeg, .png" multiple><br> -->
								<!-- <p id="fileText">파일은 .jpg, .jpeg .png, .gif 파일만 업로드 가능합니다. 여러 사진을 선택하시려면 [ctrl] 키나 [shift] 키를 눌러주십시오. 최대 5개까지 입력 가능합니다.</p> -->
								<input type="file" id="shareImg1" accept=".gif, .jpg, .jpeg, .png" name="shareImg1" class="shareImg" onchange="LoadImg(this,1)">
								<input type="file" id="shareImg2" accept=".gif, .jpg, .jpeg, .png" name="shareImg2" class="shareImg" onchange="LoadImg(this,2)">
								<input type="file" id="shareImg3" accept=".gif, .jpg, .jpeg, .png" name="shareImg3" class="shareImg" onchange="LoadImg(this,3)">
								<input type="file" id="shareImg4" accept=".gif, .jpg, .jpeg, .png" name="shareImg4" class="shareImg" onchange="LoadImg(this,4)">
								<input type="file" id="shareImg5" accept=".gif, .jpg, .jpeg, .png" name="shareImg5" class="shareImg" onchange="LoadImg(this,5)"><br><br>
								<img id="contentImg1" class="contentImg" src=""><br><br>
								<img id="contentImg2" class="contentImg" src=""><br><br>
								<img id="contentImg3" class="contentImg" src=""><br><br>
								<img id="contentImg4" class="contentImg" src=""><br><br>
								<img id="contentImg5" class="contentImg" src=""><br><br>
								<p id="fileText">파일은 .jpg, .jpeg, .png, .gif 파일만 업로드 가능합니다. 최대 5개까지 입력 가능합니다.</p>
								<input type="hidden" id="fileBool" name="fileBool" value="trueFiles">
							</td>
						</tr>
					</table><hr><br>
					<div id="wrtButton">
						<button type="button" id="button" class="wrt" onclick="submitCheck() ;">작성하기</button>
						<button type="button" id="button" class="wrt" onclick="location.href='<%= request.getContextPath() %>/list.share'">취소하기</button>
					</div><br>
				</form>
				<div id="fileArea">
					
				</div>
			</div>
		</div>
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
				} else if($('#kind').val() == "0" || $('#country').val() == "none" || $('#city').val() == "none") {
					alert('거래 장소를 입력하세요.') ;
					bool = false ;
				}
				
				if(fileCheck1 == "" && fileCheck2 == "" && fileCheck3 == "" && fileCheck4 == "" && fileCheck5 == "") {
					alert("falseFiles") ;
					$('#fileBool').val("falseFiles") ;
				} else {
					alert("trueFiles") ;
					$('#fileBool').val("trueFiles") ;
				}
				
				if($('#kakao').isEmpty) $('#kakao').val("내용이 없습니다") ;
				if(bool) $('#wrtForm').submit() ;
			}
		</script>
		<%@ include file="../common/footer.jsp" %>
	</div>
</body>
</html>