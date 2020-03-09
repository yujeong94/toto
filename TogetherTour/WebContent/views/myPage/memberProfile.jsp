<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String userNick = request.getParameter("userNick"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>profile | TogetherTour</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.4.1.min.js"></script>
<script>
   // 클릭한 작성자 닉네임 불러오기
   function writerInfo(){
      $('#inputNick').text('<%=userNick%>');
   }
   // 프로필 사진, 정보 불러오기
   $(function(){
      var userNick = $('#inputNick').text();
      console.log(userNick);
      $.ajax({
         url : '<%= request.getContextPath() %>/memberProfile.mypage',
         type : 'post',
         data : {userNick:userNick},
         success : function(data){
            var userImg = data[0].changeName;
            $('#userImg').attr('src','<%= request.getContextPath() %>/uploadFiles/'+userImg) ;
            var name = data[1].userName;
            var gender = data[1].gender;
            var age = data[1].age;
            var grade = data[1].grade;
            $('#name').text(name);
            $('#gender').text(gender);
            $('#age').text(age);
            $('#grade').text(grade);
         },
         error : function(data){
            console.log('error');
         }
      });
   });
</script>
<style>
   body{background: url("../../images/main_sub.jpg") center 0 no-repeat;}
   h2 span {
      display : inline-block;
      border-bottom : 3px solid #333f50;
      padding-bottom : 10px;
      margin-bottom: 30px;
      margin-top: 20px;
   }
   table {
      border-collapse: collapse;
      margin:auto;
      margin-bottom: 50px;
      width:70%;
   }
    th, td{
      padding: 15px;
      vertical-align: middle;
      height:30px;
      border-bottom: 1px solid gray;
      text-align: center;
   }
   th{width:100px;}
    #userImgArea{border: 1px solid black; 
              width:150px; height:150px; margin:0 auto; 
              margin-top:30px; margin-bottom:30px;
              overflow:hidden;}
    #btnArea{margin-top:20px;}
    #okBtn{padding: 9px 20px;
             border: none;
            background: #999999;
          color: #fff;
         cursor:pointer;}
</style>
</head>
<body onload="writerInfo();">
<div class="wrapper">
   <div class=contents>
   <h2><span>profile</span></h2>
   <div class=profileArea>
   <form id=memberProfile>
      <div id=userImgArea>
         <img id=userImg>
      </div>
      <br>
      <table>
         <tr>
            <th>닉네임</th>
            <td id=inputNick></td>
         </tr>
         <tr>
            <th>이름</th>
            <td id=name></td>
         </tr>
         <tr>
            <th>성별</th>
            <td id=gender></td>
         </tr>
         <tr>
            <th>생년월일 </th>
            <td id=age></td>
         </tr>
         <tr>
            <th>평점</th>
            <td id=grade></td>
         </tr>
      </table>
      
      <div id=btnArea align=center>
      <input type=submit id=okBtn value=닫기>
      </div>
   </form>
   </div>
   </div>
</div>
<script>
   $('#okBtn').click(function(){
      window.close();
   });
</script>
</body>
</html>