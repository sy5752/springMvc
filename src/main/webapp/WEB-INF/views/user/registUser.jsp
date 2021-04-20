<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Jsp</title>

<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>

<!-- Custom styles for this template -->
<link href="${cp}/css/dashboard.css"
	rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">

<% UserVo user = (UserVo)request.getAttribute("vo"); %>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function() {
		<% if(user != null){ %>
			$('#userid').val("<%= user.getUserid()%>");
			$('#usernm').val("<%= user.getUsernm()%>");
			$('#pass').val("<%= user.getPass()%>");
			$('#alias').val("<%= user.getAlias()%>");
			$('#addr1').val("<%= user.getAddr1()%>");
			$('#addr2').val("<%= user.getAddr2()%>");
			$('#zipcode').val("<%= user.getZipcode()%>");
		<% }%>
		// 주소 검색 버튼이 클릭되었을 때 다음주소 api 팝업을 연다
		$("#addrBtn").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {

					$("#addr1").val(data.roadAddress); //도로주소
					$("#zipcode").val(data.zonecode); //우편번호
					
					// 사용자 편의성을 위해 상세주소 입력 input태그로 focus 설정 -  도로주소를 입력하면 커서가 상세주소로
					$("#addr2").focus();
				}
			}).open();

		})
	})
</script>
</head>

<body>
	
	
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	 
	spring message : <%-- <spring:message code="LANG"/>  --%>

	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/WEB-INF/views/common/left.jsp"%>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h2 class="sub-header">사용자 등록</h2>
			
			
				
<%-- 				<form class="form-horizontal" role="form" action="${cp }/registUser" method="post"> --%>
				<form class="form-horizontal" role="form" method="post" action="${cp }/user/registUser" enctype="multipart/form-data">
				
					
					<div class="form-group">
						<label class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userid" name="userid" placeholder="사용자 아이디" value="${param.userid}"/>
							<span style="color:red"><form:errors path="userVo.userid"/></span>
						<input type="file" class="form-control" name="profile"/>
						</div>
					</div>

					<div class="form-group">
						<label for="usernm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm"
								placeholder="사용자 이름" value="${param.usernm}" />
						</div>
					</div>
					
					
			
					<div class="form-group">
						<label for="alias" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
						
							<input type="text" class="form-control" id="alias" name="alias"
								placeholder="별명" value="${param.alias}" />
						</div>
					</div>
		

					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-10">
						
							<input type="password" class="form-control" id="pass" name="pass"
								placeholder="비밀번호" value="${param.pass}"/>
						</div>
					</div>
					<%-- 
					<div class="form-group">
						<label for="reg_dt" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="reg_dt" name="reg_dt"
								placeholder="" value="<%=user.getReg_dt_fmt() %>"/>
						</div>
					</div> --%>
					
					
					
					<div class="form-group">
						<label for="addr1" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-8">
						
							<input type="text" class="form-control" id="addr1" name="addr1"
								placeholder="도로주소"  value="${param.addr1}"readonly/>
					</div>
						<div class="col-sm-2">
							<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
						</div>
					</div>
					
					<div class="form-group">
						<label for="addr2" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
						
							<input type="text" class="form-control" id="addr2" name="addr2"
								placeholder="상세주소" value="${param.addr2}"/>
						</div>
					</div>

					<div class="form-group">
						<label for="zipcode" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-10">
						
							<input type="text" class="form-control" id="zipcode" name="zipcode"
								placeholder="우편번호"  value="${param.zipcode}"readonly />
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">사용자 등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>