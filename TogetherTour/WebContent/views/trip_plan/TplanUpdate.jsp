<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	int kind = Integer.parseInt(request.getParameter("kind"));
	String country = request.getParameter("country");
	String city = request.getParameter("city");
	
	String strKind = null;
	if(kind == 1){
		strKind = "국내";
	} else {
		strKind = "해외";
	} 

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행 일정 수정 | 투투</title>
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
								<input type="hidden" name="tPnum" value="<%= request.getParameter("tPnum") %>">
								<input type="text" name="title" value="<%= request.getParameter("title") %>">
							</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>
							<%= request.getParameter("mid") %><input type="hidden" name="mid" value="<%= request.getParameter("mid") %>">
							</td>
						</tr>
						<tr>
							<th>작성일</th>
							<td>
							<input type="date" name="create_date" value="<%-- <%= request.getParameter("create_date") %> --%>3">
							</td>
						</tr>
						<tr>
							<th><label class=term>여행기간</label></th>
							<td>
								<label class=term>여행시작일</label>
								<input type="date" name="sDate" value="<%= request.getParameter("sDate") %>">
								<span class="term2"> ~ </span>
								<label class=term>여행종료일</label>
								<input type="date" name="eDate" value="<%= request.getParameter("eDate") %>">
									
									<!-- (<span><input type=number name="tday" id="tday" required></span> days) -->
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
					</tbody>
				</table>
				<div id=introduce>
					<p style="font-size:12pt;">일정 등록</p><br>
					<textarea rows=50 cols=140 style='resize:none;' name=tContents><%= request.getParameter("tContents") %></textarea>
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