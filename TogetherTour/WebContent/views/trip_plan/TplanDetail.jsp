<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="trip_plan.model.vo.Tplan, java.util.Date" %>  
<%
	Tplan t = (Tplan)request.getAttribute("Tplan");
	String tKind = null;
	if(t != null){
		if(t.getKind() == 1){
			tKind = "국내";
		} else {
			tKind = "해외";
		}
	}
	
	// 서블릿에서 "-"로 조인된 문자열을 tContents에 받아서
	String tContents = t.getContent();
	// 다시 "-"로 분리한 후 tContentsArr 배열에 담는다.
	String[] tContentsArr = tContents.split("-");
	
	//게시글 수정 알림창
	String msg = (String)request.getAttribute("msg");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행 일정 상세 | TogetherTour</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/trip/trip_common.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/common_sub.css">
	<style>
		label.term {
			vertical-align : middle;
			margin-right : 10px;
		}
		
		span.term2 {
			padding : 0 10px;
			vertical-align : middle;
		}
		
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
		#listBtn{
		 	padding : 9px 20px;
			border : none;
		    /* background: #999999; */
		    background : #005952;
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
		    margin-top:20px;
		    float:right;
		}
		.tableArea tr:last-child th, .tableArea tr:last-child td {
			border-bottom : 1px solid #000;
		}
		
		
		/*일정에 대한 스타일*/
		.date-box-title{text-align:center; margin-top:50px;}
		.date-box-area {
			margin : 0 100px;
		}
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
			padding : 8px 12px;
			border-bottom : 1px solid #cbcbcb;
			margin-bottom : 50px;
			min-height : 200px;
			border : 1px solid #cbcbcb;
			background : #f0f0f0;
			color : #000;
			box-shadow : inset 1px 1px 3px #e6e6e6;
		}
	</style>
</head>
<body>
<div class="wrapper">
	<!--S:header-->
	<%@ include file="../common/header.jsp" %>
	<!--E:header-->
	<div class="contents">
		<h2><span>여행 일정 상세</span></h2>
		<form action="<%= request.getContextPath() %>/views/trip_plan/TplanUpdate.jsp" id="detailForm" name="detailForm">
			<fieldset>
				<legend>여행 일정 상세</legend>
				<table class="tableArea" id="tWrite">
					<caption>여행 일정 상세</caption>
					<tbody>
						<tr>
							<th width="200px">제목</th>
							<td colspan="5">
								<input type="hidden" name="tPnum" value='<%= t.gettPnum() %>'>
								<input type="hidden" name="title" value="<%= t.getTitle() %>">
								<%= t.getTitle() %>
							</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>
							<input type="hidden" name="mid" value="<%= t.getmId() %>">
								<%= t.getmId() %>	
							</td>
							<th>작성일</th>
							<td><input type="hidden" name="create_date" value="<%= t.getCreateDate() %>">
								<%= t.getCreateDate() %>
							</td>
							<th>조회수</th>
							<td><input type="hidden" name="tcount" value="<%= t.gettCount() %>">
								<%= t.gettCount() %>
							</td>
						</tr>
						<tr>
							<th>여행 시작일</th>
							<td><input type="hidden" name="tStart" value="<%= t.getStartDate() %>"><%= t.getStartDate() %></td>
							<th>여행 종료일</th>
							<td><input type="hidden" name="tEnd" value="<%= t.getEndDate() %>"><%= t.getEndDate() %></td>
							<th>여행 기간</th>
							<td><input type="hidden" name="tday" value="<%= t.getDay() %>"><%= t.getDay() %>days</td>
						</tr>
						<tr>
							<th>여행지역</th>
							<td colspan="5">
								<input type="hidden" name="kind" value="<%= tKind %>">
								<input type="hidden" name="country" value="<%= t.getCountry() %>">
								<input type="hidden" name="city" value="<%= t.getCity() %>">
								<%= tKind %> / <%= t.getCountry() %> / <%= t.getCity() %>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="date-box-area">
					<p style="font-size:12pt; padding : 0 0 50px;" class="date-box-title">일정 보기</p><br>
					<% if(tContentsArr.length != 0){ %>
						<% for(int i = 0; i < tContentsArr.length; i++){ %>
							
								<div class='date-box-date'><span>[ <%= i+1 %>일차 ]</span></div>
								<div class="date-box-content"><%= tContentsArr[i] %></div>
								<input type="hidden" name="tcontents" value="<%= tContentsArr[i] %>">
							
						<% } %>
					<% } else if(tContentsArr.length == 0) { %>
							<div class="date-box-content" style="text-align : center; line-height : 200px;">등록된 일정이 없습니다.</div>
					<% } %>
				</div>
				<div id=tBtnArea align=center>
					<% if(t.getmId().equals(loginUser.getmId())){ %>
					<button type=submit id=tBtn>수정하기</button>
					<% } %>
					<% if(t.getmId().equals(loginUser.getmId()) || t.getmId().equals("admin")){ %>
					<button type=button id=delBtn onclick="deleteTplan();">삭제하기</button><br>
					<% } %>
				<div onclick='location.href="<%= request.getContextPath() %>/list.trip"' id=listBtn>목록으로</div>
			</div>	
			</fieldset>
		
		</form>
	</div>
	<!--S:footer-->
		<%@ include file="../common/footer.jsp" %>
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
	
	function deleteTplan(){
		var bool = confirm("정말로 삭제하시겠습니까?");
		if(bool) {
			location.href="<%= request.getContextPath() %>/delete.trip?tPnum=" + <%= t.gettPnum() %>;
		}
	}
	</script>
</body>
</html>