.<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>여행동행사이트 투투</title>
	<link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="css/common.css">
	
	<style>
	#dateSearch {
		padding : 9px 20px;
		border : none;
		background : #000;
		color : white;
	  }
</style>
</head>



<body>
	<div class="wrapper">
		<!--S:header-->
		<%@ include file="views/common/header.jsp" %>
		<!--E:header-->
		<!--S:contents-->
		<div class="contents">
			<section class="total-search">
				<div>
					<h3>동행 찾기</h3>
					<form  action="<%= request.getContextPath() %>/search.main" method="post" id ="searchForm">
						<fieldset>
							<legend>국가별 검색</legend>
							<h4>국가별 검색</h4>
							<div>
								<select name=kind id=kindList>
								<option value="KIND">분류</option>
							</select>
							<select name=country id=countryList style="width:140px;">
								<option value="COUNTRY">국가</option>
							</select>
							<select name=city id=cityList style="width:90px;">
								<option value="CITY">도시</option>
							</select>
								<button>검색</button>
							</div>
							
							
						</fieldset>
						<fieldset>
							<legend>날짜별 검색</legend>
							<h4>날짜별 검색</h4>
							<div>
								<input type="date" name="startDate"> -
								<input type="date" name="endDate">
								<input type="button" onclick="dateSeacrch();" id="dateSearch" value="검색">
							</div>
						</fieldset>
					</form>
					
					
					<script>
					function dateSeacrch(){
						$('#searchForm').attr('action', '<%= request.getContextPath() %>/dSearch.main');
						$('#searchForm').submit();
					}
					
					
					</script>
					
					
				</div>
			</section>
		</div>
		<!--E:contents-->
		<!--S:footer-->
		<%@ include file="views/common/footer.jsp" %>
		<!--E:footer-->
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