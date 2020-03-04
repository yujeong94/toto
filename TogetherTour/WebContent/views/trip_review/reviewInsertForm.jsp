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
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
	.ImgArea{display:inline-block; border:1px dashed rgb(203,203,203); width:120px; height:100px; margin-right:10px;}
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
				<tr>
					<th>여행지</th>
					<td colspan="2"><input type="text" size="58" name="location"></td>
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
						<div id="contentImgArea1" class="ImgArea">
							<img id="Img1"> 
						</div>

						<div id="contentImgArea2" class="ImgArea">
							<img id="Img2"> 
						</div>
	
						<div id="contentImgArea3" class="ImgArea">
							<img id="Img3"> 
						</div>
					</td>
				</tr>
				
				
	
			</table>
			
			<!-- 파일 업로드 하는 부분 -->
			<div id="fileArea">
				<input type="file" id="thumbnailImg1" multiple="multiple" name="thumbnailImg1" onchange="LoadImg(this,1)">
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
			</script>	
			
			
						
			<div align="center">
				<button type="submit" id="insertBtn">등록하기</button>
					<%-- <div id="cancelBtn" onclick="location.href='<%= request.getContextPath()%>/list.rv'">취소</div>  --%>
					<input type="button" onclick="back();" id="canCelBtn" value="취소">
			</div>
		</form>
		
		<script>
			function back(){
				$('#insertForm').attr('action', '<%= request.getContextPath() %>/list.rv');
				$('#insertForm').submit();
			}
		
		</script>
	</div>	
		
		
	<%@ include file="../common/footer.jsp" %> 
</body>
</html>