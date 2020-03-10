<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="review.model.vo.Review, java.util.ArrayList"%>
<%
	request.setCharacterEncoding("UTF-8");
	String guide = request.getParameter("guide");
	String gCheck ="";
	
	if(guide.equals("Y")){
		gCheck="checked";
	}
	
	String kind = request.getParameter("kind");
	String country = request.getParameter("country");
	String city = request.getParameter("city");
	
	String[] selected = new String[2];
	if(kind.equals("국내")){
		selected[0] = "selected";
		selected[1] = "";
	} else {
		selected[0] = "";
		selected[1] = "selected";
	}
	
	ArrayList<String> images = new ArrayList<String>();
	for(int i = 0; i < 3; i++){
		images.add(request.getParameter("detailImg" + i) == null ? "" : "src=" + request.getContextPath() + "/uploadFiles/" + request.getParameter("detailImg" + i));
	}
	
	ArrayList<String> fIds = new ArrayList<String>();
	for(int i = 0; i < 3; i++){
		fIds.add(request.getParameter("detailImgId" + i) == null ? "" : request.getParameter("detailImgId" + i));
	}
	
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
		<h2>여행 후기 & 팁 수정</h2>
			<form action = "<%= request.getContextPath()%>/update.rv" id ="updateForm" method="post" encType="multipart/form-data">
				<table>
					<tr>
						<th><label>여행지</label> <input type="hidden" name ="rnum" value="<%= request.getParameter("rnum")%>"></th>
						<td>
							<select name=kind id=kindList>
								<option>분류</option>
								<option value="국내" <%= selected[0] %>>국내</option>
								<option value="해외" <%= selected[1] %>>해외</option>
							</select>
							<select name=country id=countryList>
								<option><%= country %></option>
								<!-- <option>국가</option> -->
							</select>
							<select name=city id=cityList>
								<option><%= city %></option>
								<!-- <option>도시</option> -->
							</select>
						</td>
					</tr>
					
					<tr>
						<th>가이드 동행여부</th>
						<td><input type="checkbox" name = "guide" value = "guide" <%= gCheck%>>(동행시 체크)</td>
					</tr>
					
					
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" value="<%= request.getParameter("title") %>"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea rows="20" cols="130" name="content" style="resize:none;"><%= request.getParameter("content") %></textarea>
						</td>
					</tr>
					
					<tr>
						<th>사진</th>
						<td colspan="3">
							<% for(int i = 0; i < 3; i++){ %>
							<div id="contentImgArea<%=i%>" class="ImgArea">
								<img id="contentImg<%=i%>" width="200" height="200" <%= images.get(i) %>> 
								<input type="hidden" id="detailImgId<%=i%>" name="detailImgId<%=i%>" value="<%= fIds.get(i) %>"> 
								<input type="hidden" id="cContent<%= i %>" name="cContent<%= i %>">
							</div>
							<% } %>
						</td>
						
					</tr>
				</table>
				
				
				<div id="fileArea">
				<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)">
				<input type="file" id="thumbnailImg2" multiple="multiple" name="thumbnailImg2" onchange="LoadImg(this,2)">
				<input type="file" id="thumbnailImg3" multiple="multiple" name="thumbnailImg3" onchange="LoadImg(this,3)">
				</div>
				
				
				<script>
					// 내용 작성 부분의 공간을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
					$(function(){
						$("#fileArea").hide();
					
						$("#contentImgArea0").click(function(){
							$("#thumbnailImg1").click();
						});
						$("#contentImgArea1").click(function(){
							$("#thumbnailImg2").click();
						});
						$("#contentImgArea2").click(function(){
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
									$("#contentImg0").attr("src", e.target.result);
									break;
								case 2: 
									$("#contentImg1").attr("src", e.target.result);
									break;
								case 3:
									$("#contentImg2").attr("src", e.target.result);
									break;
								}
							}
							
							reader.readAsDataURL(value.files[0]);
						}
					}
				</script>

				
				<br>
				<div align="center">
					<button type="submit" id="updateBtn">수정 완료 </button>
					<%-- <div id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/list.rv'">취소</div> --%>
					<input type="button" onclick="back();" id="canCelBtn" value="취소">
				</div>
		</form>
	</div>
	<script>
		$('#updateBtn').click(function(){
			var c1 = $("#contentImg0").attr('src');
			var c2 = $("#contentImg1").attr('src');
			var c3 = $("#contentImg2").attr('src');
			
			if(typeof(c1) != 'undefined'){
				$("#cContent0").val($("#contentImg0").attr('src').substring(0, 4));
			}
			if(typeof(c2) != 'undefined'){
				$("#cContent1").val($("#contentImg1").attr('src').substring(0, 4));
			}
			if(typeof(c3) != 'undefined'){
				$("#cContent2").val($("#contentImg2").attr('src').substring(0, 4));
			}
			
			$('.insertArea').parent().submit();
		});
		
		function back(){
			$('#updateForm').attr('action', '<%= request.getContextPath() %>/list.rv');
			$('#updateForm').submit();
		}
	</script>
	
	
	
	
	<%@ include file="../common/footer.jsp" %>
<script>
	$(function(){
		$.ajax({
			url: '<%= request.getContextPath() %>/list.loca',
		 	type: 'post',
		 	success: function(data){
		 		$selectKind = $('#kindList');
				$selectCountry = $('#countryList');
				$selectCity = $('#cityList');
				
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