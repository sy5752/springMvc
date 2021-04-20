<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">


<title>사용자</title>

<%@include file="/WEB-INF/views/common/common_lib.jsp"%>

<!-- Custom styles for this template -->


</head>

<body>

	<%@include file="/WEB-INF/views/common/header.jsp"%>


	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@include file="/WEB-INF/views/common/left.jsp"%>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h2 class="sub-header">사용자 전체조회</h2>
				<table class="table table-striped">
					<tr>
						<td>사용자 아이디</td>
						<td>사용자 이름</td>
						<td>사용자 별명</td>
						<td>등록일시</td>
					</tr>

					
					<c:forEach items="${userList }" var="users">
						<tr>
							<td>${users.userid}</td>
							<td>${users.usernm}</td>
							<td>${users.alias}</td>
							<td><fmt:formatDate value="${users.reg_dt}" pattern="yyyy.MM.dd"/></td>
						</tr>
					</c:forEach>
					
				</table>
				
			</div>
		</div>
	</div>
</body>
</html>
