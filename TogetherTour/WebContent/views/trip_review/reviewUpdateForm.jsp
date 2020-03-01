<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="review.model.vo.Review, java.util.ArrayList"%>
<%
	request.setCharacterEncoding("UTF-8");
	String guide = request.getParameter("guide");
	String gCheck ="";
	
	if(guide.equals("Y")){
		gCheck="checked";
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
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/index.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
<style>
	.ImgArea{display:inline-block; border:1px dashed rgb(203,203,203); width:120px; height:100px; margin-right:10px;}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	<div class="contents">
		<h2>여행 후기 & 팁 수정</h2>
			<form action = "<%= request.getContextPath()%>/update.rv" method="post" encType="multipart/form-data">
				<table>
					<tr>
						<th>여행지역<input type="hidden" name ="rnum" value="<%= request.getParameter("rnum")%>"></th>
						<td><input type="text" name = "location" value = "<%=request.getParameter("location")%>"></td>
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
							<textarea rows="15" cols="60" name="content" style="resize:none;"><%= request.getParameter("content") %></textarea>
						</td>
					</tr>
					
					<tr>
						<th>사진</th>
						<td colspan="3">
							<% for(int i = 0; i < 3; i++){ %>
							<div id="contentImgArea<%=i%>" class="ImgArea">
								<img id="contentImg<%=i%>" width="120" height="100" <%= images.get(i) %>> 
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
					<div id="cancelBtn" onclick="location.href='<%= request.getContextPath() %>/list.rv'">취소</div>
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
	</script>
	
	
	
	
	<%@ include file="../common/footer.jsp" %>
</body>
</html>