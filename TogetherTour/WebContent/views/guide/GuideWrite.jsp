<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가이드 등록 | TogetherTour</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
 	table{border-top:1px solid; margin-top:100px;}
 	td,th{border-bottom:1px solid;}
 	#gInsertForm{margin-bottom:100px;}
 	#introduce{text-align:center; margin-top:50px;}
 	label{vertical-align:middle;}
 	.term{margin-right:10px;}
 	#term2{margin-left:10px; margin-right:10px;}
 	#gBtnArea{margin-top:40px;}
 	#cancleBtn{
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
	}
	#gBtn{margin-right:20px;}
</style>
</head>
<body>
<div class='wrapper'>
	<%@ include file="../common/header.jsp" %>
	<div class='contents'>
		<h2><span>가이드 등록</span></h2>
		<form action="<%= request.getContextPath() %>/insert.guide" method=post id=gInsertForm>
			<table id=gWrite>
				<tr>
					<th width=200px><label>제목</label></th>
					<td><input type=text name=gTitle id=gTitle size=100px required></td>
				</tr>
				<tr>
					<th><label>활동장소</label></th>
					<td>
						<select name=kind id=kindList>
						<option>분류</option>
						</select>
						<select name=country id=countryList>
						<option>국가</option>
						</select>
						<select name=city id=cityList>
						<option>도시</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>여행기간</th>
					<td>
						<label class=term>여행시작일</label>
							<input type=date name=gStart required><label id=term2>~</label>
						<label class=term>여행종료일</label>
							<input type=date name=gEnd required>
					</td>
				</tr>
				<tr>
					<th><label>가격</label></th>
					<td><input type=number min=0 name=price id=price required> 원</td>
				</tr>
				<tr>
					<th><label>오픈카카오톡주소</label></th>
					<td><input type=text name=kakao id=kakao size=80px></td>
				</tr>
			</table>
			<div id=introduce>
				<p style="font-size:12pt;">여행소개</p><br>
				<textarea rows=50 cols=140 style='resize:none;' name=gContents placeholder="내용을 입력해주세요." required></textarea>
			</div>
			<div id=gBtnArea align=center>
				<button type=submit id=gBtn>등록</button>
				<div onclick='location.href="javascript:history.go(-1);"' id=cancleBtn>취소</div>
			</div>
		</form>
	</div>
	<%@ include file="../common/footer.jsp" %>
</div>
<script>
	$(function(){
		$.ajax({
			url: '<%= request.getContextPath() %>/list.loca',
		 	type: 'post',
		 	success: function(data){
		 		$selectKind = $('#kindList');
				$selectCountry = $('#countryList');
				$selectCity = $('#cityList');
				for(var i in data[0]){
					var $option = $('<option>');
					$option.val(data[0][i]);
					var con = null;
					if(data[0][i] == 1){
						con = "국내";
					} else {
						con = "해외";
					}
					$option.text(con);
					$selectKind.append($option);
				}
				
				// county변경
				$('#kindList').change(function(){
					var kindSel = $('#kindList option:selected').text();
					if(kindSel == "국내"){
						$('#countryList').find('option').remove();
						var $option = $('<option>');
						$option.val('국가');
						$option.text('국가');
						$selectCountry.append($option);
						var $option = $('<option>');
						$option.val('한국');
						$option.text('한국');
						$selectCountry.append($option);
					} else if(kindSel == "해외"){
						$('#countryList').find('option').remove();
						var $option = $('<option>');
						$option.val('국가');
						$option.text('국가');
						$selectCountry.append($option);
						for(var i in data[1]){
							var $option = $('<option>');
							$option.val(data[1][i]);
							$option.text(data[1][i]);
							$selectCountry.append($option);
						}
					} else {
						$('#countryList').find('option').remove();
						var $option = $('<option>');
						$option.text('국가');
						$selectCountry.append($option);
					}
				});
					// 해외city변경
					$('#countryList').change(function(){ 
						var countrySel = $('#countryList option:selected').text();
						$('#cityList').find('option').remove();
						for(var i in data[2]){
							if(data[2][i].country == countrySel){
								var $option = $('<option>');
								$option.val(data[2][i].city);
								$option.text(data[2][i].city);
								$selectCity.append($option);
							}
						}
						$('#kindList').change(function(){
							$('#cityList').find('option').remove();
							var $option = $('<option>');
							$option.val('도시');
							$option.text('도시');
							$selectCity.append($option);
						});
					});
		 	},
		 	error: function(data){
		 		console.log('error');
		 	}
		});
	});
</script>
</body>
</html>