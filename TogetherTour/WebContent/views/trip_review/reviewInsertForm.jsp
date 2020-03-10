<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "member.model.vo.*"%>
<%
	/* Member loginUser = (Member)session.getAttribute("loginUser"); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
	.ImgArea{display:inline-block; border:1px dashed rgb(203,203,203); width:200px; height:200px; margin-right:10px;}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div class="contents">
		<br>
		<h2>여행 후기 & 팁 작성</h2>
		<hr>
		<form action="<%= request.getContextPath() %>/insert.rv" method="post" id ="insertForm" encType="multipart/form-data">
			<table>
				<!-- <tr>
					<th>여행지</th>
					<td colspan="2"><input type="text" size="58" name="location"></td>
				</tr>  -->
				
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
					<th>여행기간 </th>
					<td><input type="date" name="startDate"> - <input type="date" name="lastDate"></td>
					
				</tr>
				
				<tr>
					<th>가이드 동행여부</th>
					<td><input type="checkbox" name="guide" value="guide"><label>(동행시 체크)</label></td>
				</tr>
				
				<tr>
					<th>제목</th>
					<td colspan="3"><input type="text" size="58" name="title"></td>
				</tr>
				
				<tr>
					<th>내용</th>
					<td colspan="3">
					<textarea rows="20" cols="130" name="content" style="resize:none;"></textarea>
					</td>
				</tr>
				
		
				
				<tr>
					<th>사진 첨부</th>
					<td colspan="3">  
						<div id="contentImgArea1" class="ImgArea" style="overflow:hidden">
							<img id="Img1" width="200" height="200"> 
						</div>

						<div id="contentImgArea2" class="ImgArea" style="overflow:hidden">
							<img id="Img2" width="200" height="200"> 
						</div>
	
						<div id="contentImgArea3" class="ImgArea" style="overflow:hidden">
							<img id="Img3" width="200" height="200"> 
						</div>
					</td>
				</tr>
				
				
	
			</table>
			
			<!-- 파일 업로드 하는 부분 -->
			<div id="fileArea">
				<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)" required>
				<input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this,2)">
				<input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this,3)">
			</div>
			
			<script>
				// 내용 작성 부분의 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
				$(function(){
					$("#fileArea").hide();
					
					$("#contentImgArea1").click(function(){
						$("#thumbnailImg1").click();
					});
					$("#contentImgArea2").click(function(){
						$("#thumbnailImg2").click();
					});
					$("#contentImgArea3").click(function(){
						$("#thumbnailImg3").click();
					});
				});
					
				// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
				function LoadImg(value, num){
					if(value.files && value.files[0]){
						var reader = new FileReader();
						
						reader.onload = function(e){								
							switch(num){
							case 1:
								$("#Img1").attr("src", e.target.result);
								break;
							case 2: 
								$("#Img2").attr("src", e.target.result);
								break;
							case 3:
								$("#Img3").attr("src", e.target.result);
								break;
							}
						}
						
						reader.readAsDataURL(value.files[0]);
					}
				}
				
				// 여행지부분
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
			
			
						
			<div align="center">
				<button type="submit" id="insertBtn">등록하기</button>
				
				
				
					<input type="button" onclick="back();" id="canCelBtn" value="취소">
			</div>
		</form>
		
		<script>
			$(function(){
			   $('#insertBtn').click(function(){
			      if($('#thumbnailImg1').val()== ''){
			         alert('사진을 최소 1개이상 넣어주세요!');
			      } else{
			         
			      }
			   });
			   
			});
		
		
		
		
		
			function back(){
				$('#insertForm').attr('action', '<%= request.getContextPath() %>/list.rv');
				$('#insertForm').submit();
			}
			
			
			
		</script>
	</div>	
	
	<%@ include file="../common/footer.jsp" %> 
<script>

</script>	
	
</body>
</html>