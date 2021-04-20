
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



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

