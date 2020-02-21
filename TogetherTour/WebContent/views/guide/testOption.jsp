<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.sql.PreparedStatement,
	java.sql.ResultSet,java.sql.SQLException,java.sql.Statement,static common.JDBCTemplate.*,
	java.util.ArrayList"%>
<%
	// db에서 정보 얻어와 테이블에 출력하기
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	ArrayList<String> kindArr = new ArrayList<String>();
	ArrayList<String> countryArr = new ArrayList<String>();
	ArrayList<String> cityArr = new ArrayList<String>();
	
	// 중복제거 담기
	ArrayList<String> kindArr2 = new ArrayList<String>();
	ArrayList<String> countryArr2 = new ArrayList<String>();
	ArrayList<String> cityArr2 = new ArrayList<String>();
	
	
	try {
		conn = getConnection();
		String sql = "SELECT * FROM LOCATION";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			// 결과 담기
			int kind = rs.getInt("kind");
			String country = rs.getString("country");
			String city = rs.getString("city");
			
			// 분류 번호에 국내/해외 string값 넣기
			String strKind = null;
			if(kind == 1) {
				strKind = "국내";
			} else {
				strKind = "해외";
			}
			
			kindArr.add(strKind);
			countryArr.add(country);
			cityArr.add(city);
			
		}
			// 중복 제거하여 담기 
			for(int i = 0; i < kindArr.size(); i++){
				if(!kindArr2.contains(kindArr.get(i))){
					kindArr2.add(kindArr.get(i));
				} 
			}
			
			for(int i =0; i < countryArr.size(); i++){
				if(!countryArr2.contains(countryArr.get(i))){
					countryArr2.add(countryArr.get(i));
				}
			}
			
			for(int i = 0; i < cityArr.size(); i++){
				if(!cityArr2.contains(cityArr.get(i))){
					cityArr2.add(cityArr.get(i));
				}
			} 
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rs);
		close(pstmt);
		close(conn);
	}
%>
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
						<select name=kind id=kind>
						<% for(int i = 0; i < kindArr2.size(); i++){ %>
							<option><%= kindArr2.get(i) %></option>
						</select>
							<select name=country id=country>
							<% for(int j = 0; j < countryArr2.size(); j++){ %>
								<% if(kindArr2.get(i).equals("국내")){ %>
									<option><%= countryArr2.get(0) %></option> <!-- 한국만 들어감  -->
									<% break; %>
								<% } else { %>
									<option><%= countryArr2.get(j) %></option>
							<% } %>
							</select>
							<select name=city id=city>
							<% for(int k = 0; k < cityArr2.size(); k++){ %>
								<option><%= cityArr2.get(k) %></option>
							<% } %>
							</select>
							<% } %>
							<% } %>
						
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
		 		//옵션에 뿌릴거
		 	},
		 	error: function(data){
		 		console.log('error');
		 	}
		});
	});
</script>
</body>
</html>