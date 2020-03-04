<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String gradeNick = request.getParameter("gradeNick");
	String gradeMid = request.getParameter("gradeMid");
	String gradeNick2 = (String)request.getAttribute("gradeNick2");
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행자 평가하기</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<style>
	h2{
		margin-top: 30px; margin-bottom:50px; 
		margin-left:20px; font-size:25pt;
	}
	h2 span{
		display: inline-block;
	    border-bottom: 3px solid #333f50;
	    padding-bottom: 10px;
	}
	.star{
		  font-size:50pt; cursor:pointer; 
		  color:lightgray; text-align:center;
	}
	.star.on{color:#08088A;}
	legend{font-size:15pt;}
	select{width:200px; height:30px;}
	#rating,#report{width:70%; margin:0 auto;}
	#rating{margin-bottom:50px; height:150px;}
	#report{height:400px;}
	#reportCate{margin-top:30px; margin-left:10px; margin-bottom:30px;}
	textarea{
			width:500px; height:200px; resize:none; 
			margin-left:10px; margin-right:10px;
	}
	button{
			background:white; color:black; 
			border:1px solid gray; height:30px;
			font-size:12pt; cursor:pointer;
	}
	#starBtn{margin-left:10px;}
	#reportBtn{float:right; margin-top:30px;}
	#closeBtn{
			  background:gray; color:white; margin-top:30px;
			  width:80px; height:40px;
	}
	/* .show{display:none;} */
</style>
</head>
<body>
<div class=wrapper>
	<div class=contents>
		<% if(gradeNick2 == null) { %>
			<h2><span>동행자 '<%= gradeNick %>'님 평가 & 신고</span></h2>
		<% } else { %>
			<h2><span>동행자 '<%= gradeNick2 %>'님 평가 & 신고</span></h2>
		<% } %>
		<fieldset id=rating>
			<legend>▶별점주기 (1~5점까지 별점을 줄 수 있습니다)</legend>
			<div id=starbox align=center>
				<span class="star on" id=1>★</span>
				<span class=star id=2>★</span>
				<span class=star id=3>★</span>
				<span class=star id=4>★</span>
				<span class=star id=5>★</span>
				<span><button class=starBtn>평가</button></span>
			</div>
		</fieldset>
		
		<form action='<%= request.getContextPath() %>/insert.report' method=get>
		<fieldset id=report>
			<legend>▶신고하기</legend>
			<input type=hidden value='<%= gradeMid %>' name=gradeMid>
			<input type=hidden value='<%= gradeNick %>' name=gradeNick>
			<select id=reportCate name=reportCate>
				<option>신고사유선택</option>
				<option value=1>부적절한 언행 및 행동</option>
				<option value=2>약속 불이행</option>
				<option value=3>연락두절</option>
			</select>
			<div>
			<textarea name=rContents placeholder="내용 입력"></textarea>
			</div>
			<div>
			<button id=reportBtn>신고</button>
			</div>
		</fieldset>
		</form>
		<div align=center>
		<button id=closeBtn onclick="window.close();">닫기</button>
		</div>
	</div>
</div>
<script>
	$('#starbox .star').click(function(){
		$(this).parent().children('span').removeClass('on');
		$(this).addClass('on').prevAll('span').addClass('on');
	});

	var grade = 0;
	var gradeNick = '<%= gradeNick %>';
	$('.starBtn').click(function(){
		if($('#5').hasClass('on')){
			grade = 5;
		} else if($('#4').hasClass('on')){
			grade = 4;
		} else if($('#3').hasClass('on')){
			grade = 3;
		} else if($('#2').hasClass('on')){
			grade = 2;
		} else {
			grade = 1;
		}
		$.ajax({
			url : '<%= request.getContextPath() %>/updateGrade.me',
			type : 'post',
			data : {grade:grade, gradeNick:gradeNick},
			success : function(data){
					console.log(data);
				if(data > 0){
					alert('평가완료');
					/* $('.starBtn').attr('class','show');
					var $span = $('<span>');
					var $button = $('<button>');
					$button.text('평가취소').attr('class','resetBtn');
					$('#starbox').append($span);
					$span.append($button); */
				} else {
					alert('평가실패!');
				}
			},
			error : function(data){
				console.log('평가 error');
			}
		});
	});
	<%-- $('.resetBtn').click(function(){
		console.log("이거되나");
			$.ajax({
				url : "<%= request.getContextPath() %>/resetGrade.me",
				type : 'post',
				data : {grade:grade,gradeNick:gradeNick},
				success : function(data){
						console.log(data);
					if(data > 0){
						$('.resetBtn').attr('class','show');
						$('.show').attr('class','starBtn');
					} else {
						alert('평가취소 실패');
					}
				},
				error : function(data){
					console.log('평가취소 error');
				}
			});
	}); --%>
	
	var msg = '<%= msg %>';
	
	$(function(){
		if(msg != 'null') {
			alert(msg);
		}
	});
</script>
</body>
</html>