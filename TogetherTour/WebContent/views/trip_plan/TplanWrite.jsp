<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행 일정 등록 | TogetherTour</title>
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
		.tableArea tr:last-child th, .tableArea tr:last-child td {
			border-bottom : 1px solid #000;
		}
		.makePlanBtn{margin-top:40px; text-align:center; }
		div.makePlanArea {
			display : none;
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
		
		/*일정 입력박스에 대한 스타일*/
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
		<h2><span>여행 일정 등록</span></h2>
		<form action="<%= request.getContextPath() %>/insert.trip" method=post id=tInsertForm>
			<fieldset>
				<legend>여행 일정 등록</legend>
				<table class="tableArea">
					<caption>여행 일정 등록</caption>
					<col>
					<col>
					<tbody>
						<tr>
							<th width="200px">제목</th>
							<td>
								<input type="text" name="title" id="title" size="100px" required>
							</td>
						</tr>
						<tr>
							<th>여행지역</th>
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
								<input type=date name="tStart" id="tStart" required onchange="tplanConfirm();"> 
								<span class="term2">~</span> 
								<label class=term>여행종료일</label>
								<input type=date name="tEnd" id="tEnd" required onchange="tplanConfirm();">
								<span id="tDay" class="tDay">betweenDay</span><input type=hidden id="tDay1" name="tDay">
							</td>
						</tr>
					</tbody>
				</table>
				<div class="makePlanBtn">
					<button type="button">일정 만들기</button>
				</div>
				<div class="makePlanArea">
					<p style="font-size:12pt;" class="makePlanTitle">여행 일정 등록</p>
					<div class="makePlan" id="makePlan"></div>
					<div id=tBtnArea align=center>
						<button type=submit id=tBtn onclick="location.href='<%= request.getContextPath() %>/insert.trip'">등록</button>
						
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
	var betweenDay = 0;
	document.getElementById("tDay").innerHTML = "(" + betweenDay + "일차)";
	document.getElementById("tDay1").value = betweenDay;
	var tStart = "";
	var tEnd = "";
	
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
					betweenDay = Math.ceil(Math.abs((sDay.getTime() - eDayObj.getTime())/1000/60/60/24));	// 여행 일수(두 날짜 차이 + 1)
				} else{
					betweenDay = Math.ceil(Math.abs((sDay.getTime() - eDayObj.getTime())/1000/60/60/24))+1;	// 여행 일수(두 날짜 차이 + 1)
				}
					document.getElementById("tDay").innerHTML = "(" + betweenDay + "일차)";
					document.getElementById("tDay1").value = betweenDay;
				
			} 
		} 
	}
	
	// 일정 만들기 버튼을 클릭하면 일차대로 일정을 메모할 수 있는 입력박스가 나오는 함수
	$(function(){
		$(".makePlanBtn button").click(function(){
			if(tStart > tEnd) {
				
				alert("여행 기간을 다시 설정하세요.\n여행 종료일은 여행 시작일보다 같거나 늦어야 합니다.");
				$(".makePlanArea").css("display", "none");
				betweenDay = 0;
				document.getElementById("tDay").innerHTML = "(" + betweenDay + "일차)";
				
			} else {
				$(".makePlanArea").css("display", "block");
				
				$("#makePlan").html("");
				
				for(var i = 1; i <= betweenDay; i++){
					$("#makePlan").append("<div class='date-box-date'><span>[ " 
												+ i + "일차 ]</span></div><textarea name='tContents' class='date-box-content' rows='10' cols='140' style='resize:none;' placeholder='" 
												+ i + "일차 일정을 입력하세요'></textarea>");

				}
			}
			
		});	
	});
	
	// 여행지역 목록을 보여주는 함수
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