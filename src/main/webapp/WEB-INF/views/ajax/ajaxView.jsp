<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/common_lib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function() {
	$("#makeBtn").on("click", function() {
		$("#reqData").text("");
		
		//form
		if($("#type").val()=="form"){
			//$("#reqData").text($("#frm").serialize());
			$("#reqData").text("userid="+ $("input[name=userid]").val()+
					+"&usernm="+$("input[name=usernm]").val());
			
		}
		//json
		else{
			
		}
	});
	$("#send").on("click", function() {
		$.ajax({
			url : '/ajax/form',
			type : "post",
			data : $("#frm").serialize(),
			dataType : 'json',
			success : function (res); 			
			
		
		}
	})
	
})
</script>
</head>
<body>
	<form>
	사용자 아이디 : <input type="text" name="userid" value="brown"/><br>
	사용자 이름 : <input type="text" name="usernm" value="브라운"/><br>
	<select id="type">
		<option value="form">form전송</option>
		<option value="json">json전송</option>
	</select> <input type="button" id="makeBtn" value="전송데이터 생성"> <br><br>
	</form>
	<h4>전송 데이터</h4>
	<span id="reqData">
	</span>
	
	<h4>응답 데이터</h4>
	<span id="respData">
	</span>
	
	<input type="button" id="send" value="전송">
</body>
</html>