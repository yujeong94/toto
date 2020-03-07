<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");

	String kind = request.getParameter("kind");
	String country = request.getParameter("country");
	String city = request.getParameter("city");
	
	String[] selected = new String[2];
	if(kind.equals("1")){
		selected[0] = "selected";
		selected[1] = "";
	} else {
		selected[0] = "";
		selected[1] = "selected";
	}
	
	String[] tContentsArr = request.getParameterValues("tcontents");
	String tContents = "";
	if(tContentsArr != null) {
		tContents = String.join("-", tContentsArr);
	}
	tContentsArr = tContents.split("-");
	
	System.out.println("들어온 내용이 있나?" + tContents);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행 일정 수정 | TogetherTour</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/trip/trip_common.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
	<style>
		label.term {
			vertical-align : middle;
			margin-right : 10px;
		}
		
		span.term2, span.tDay {
			padding : 0 10px;
			vertical-align : middle;
		}
		#introduce{text-align:center; margin-top:50px;}
		#tBtnArea{margin-top:40px;}
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
		#tBtn{margin-right:20px;}
		
		
		div.makePlanArea {
			text-align:center; 
			margin-top:50px
		}
		p.makePlanTitle {
			padding : 50px 0 0;
			border-top : 1px solid #000;
		}
		div.makePlan {
			padding : 50px 0;
			border-bottom : 1px solid #000;
		}
		#tBtnArea {
			margin-top : 50px;
		}
		
		/*일정에 대한 스타일*/
		.date-box-date {
			text-align : center;

		}
		.date-box-date span{
			display : inline-block;
			width : 100px;
			height : 30px;
			text-align : center;
			color : #cbcbcb;
			padding-bootom : 10px;
			margin-bottom : 10px;
		}
		.date-box-content {
			margin-bottom : 40px;
		}
		
	</style>
</head>
<body>
<div class="wrapper">
	<!--S:header-->
	<%@ include file="../common/header.jsp" %>
	<!--E:header-->
	<div class="contents">
		<h2><span>여행 일정 수정</span></h2>
		<form action="<%= request.getContextPath() %>/update.trip" id="tUpdateForm" name="tUpdateForm">
			<fieldset>
				<legend>여행 일정 수정</legend>
				<table class="tableArea" id="tWrite">
					<caption>여행 일정 수정</caption>
					<col>
					<col>
					<tbody>
						<tr>
							<th width="200px">제목</th>
							<td>
								<input type="hidden" name="tPnum" id=tPnum value="<%= request.getParameter("tPnum") %>">
								<input type="text" name="title" size="100px" value="<%= request.getParameter("title") %>">
							</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>
							<%= request.getParameter("mid") %><input type="hidden" name="mid" value="<%= request.getParameter("mid") %>">
							</td>
						</tr>
						<tr>
							<th>여행지역</th>
							<td>
								<select name=kind id=kindList>
								<option>분류</option>
								<option value="국내" <%= selected[0] %>>국내</option>
								<option value="해외" <%= selected[1] %>>해외</option>
								</select>
								<select name=country id=countryList>
								<option><%= country %></option>
								</select>
								<select name=city id=cityList>
								<option><%= city %></option>
								</select>
							</td>
						</tr>
						<tr>
							<th><label class=term>여행기간</label></th>
							<td>
								<label class=term>여행시작일</label>
								<input type="date" name="tStart" id="tStart" value="<%= request.getParameter("tStart") %>" required onchange="tplanConfirm();">
								<span class="term2"> ~ </span>
								<label class=term>여행종료일</label>
								<input type="date" name="tEnd" id="tEnd" value="<%= request.getParameter("tEnd") %>" required onchange="tplanConfirm();">
								<span id="tDay" class="tDay">betweenDay</span><input type=hidden id="tDay1" name="tDay">
							</td>
						</tr>
						
					</tbody>
				</table>
				<div class="makePlanArea">
					<p style="font-size:12pt;" class="makePlanTitle">여행 일정 보기</p><br>
					<div class="makePlan" id="makePlan">
					<% if(tContentsArr.length != 0){ %>
						<% for(int i = 0; i < tContentsArr.length; i++){ %>
						<div class='date-box-date'><span>[ <%= i+1 %>일차 ]</span></div>
						<textarea name='tContents' class='date-box-content' rows='10' cols='140' style='resize:none;'><%= tContentsArr[i] %></textarea>
						<% } %>
					<% } else if(tContentsArr.length == 0) { %>
							<div class="date-box-content" style="text-align : center; line-height : 200px;">등록된 일정이 없습니다.</div>
					<% } %>
					</div>
					
				</div>
				
				<div id=tBtnArea align=center>
					<button type=submit id=tBtn>수정완료</button>
					<div onclick='location.href="javascript:history.go(-1);"' id=cancleBtn>취소</div>
				</div>
			</div>	
			</fieldset>
		
		</form>
	</div>
	<!--S:footer-->
	<%@ include file="../common/footer.jsp" %>
	<!--E:footer-->
	</div>
	<script>
	
	// 여행 시작일과 여행 종료일이 정해지면 일차가 계산되는 함수
	var betweenDay = <%= request.getParameter("tday") %>;
	document.getElementById("tDay").innerHTML = "(" + betweenDay + "일차)";
	document.getElementById("tDay1").value = betweenDay;
	var tStart = "";
	var tEnd = "";
	
	<%-- <% for(int i = 0; i < tContentsArr.length; i++){ %>
		$("#makePlan").append("<div class='date-box-date'><span>" 
			+ <%= i+1 %> + "일차</span></div><textarea name='tContents' class='date-box-content' rows='10' cols='140' style='resize:none;' placeholder='" 
			+ <%= i %> + "일차 일정을 입력하세요'><%= tContentsArr[i] %></textarea>"); 
	
	<% } %> --%>
	
	
	function tplanConfirm(){
		
		tStart = document.getElementById("tStart").value;
		tEnd = document.getElementById("tEnd").value;
		
		var sDay = new Date(tStart);	// 여행시작일
		var eDay = tEnd;				// 여행종료일
		
		var eDayArray = eDay.split("-"); 			
		var eDayObj = new Date(eDayArray[0], Number(eDayArray[1])-1, eDayArray[2]);
		
		if(tStart <= tEnd){
			if(tEnd != null && tEnd != "") {
				if(tStart == tEnd) {
					betweenDay = Math.ceil(Math.abs((sDay.getTime() - eDayObj.getTime())/1000/60/60/24));     // 여행 일수(두 날짜 차이 + 1)
				} else{
					betweenDay = Math.ceil(Math.abs((sDay.getTime() - eDayObj.getTime())/1000/60/60/24))+1;   // 여행 일수(두 날짜 차이 + 1)
				}
					document.getElementById("tDay").innerHTML = "(" + betweenDay + "일차)";
					document.getElementById("tDay1").value = betweenDay;
				
			} 
			
		} 
		
		$.ajax({
			
			url : '<%= request.getContextPath() %>/updateContents.trip',
			data : {tContents : "<%= tContents %>"},
			type : 'post',
			success : function(data){
				
				$("#makePlan").html("");
				for(var i = 0; i <= betweenDay-1; i++){
					$("#makePlan").append("<div class='date-box-date'><span>[ " 
												+ (i+1) + "일차 ]</span></div><textarea name='tContents' class='date-box-content' rows='10' cols='140' style='resize:none;'>" + data[i] + "</textarea>");
			
					console.log(data[i]);
					if(data[i] == "undefined"){
						data[i] = "zero";
					}
				}
				
			}
			
		});
		
	}
	
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