<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- 공통적으로 사용할 css, js파일을 등록 -->
 
 <% 
 	((HttpServletRequest)pageContext.getRequest()).getContextPath();
 %>
<!DOCTYPE html>
<!-- Bootstrap core CSS -->
<link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
<link href="${cp}/css/dashboard.css" rel="stylesheet">
<link href="${cp}/css/blog.css" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${cp}/js/bootstrap.js"></script><!-- Custom styles for this template -->
