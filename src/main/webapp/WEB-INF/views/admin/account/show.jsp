<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
<title>Insert title here</title>
</head>
<body>
	<table class="table table-strip table-primary">
		<thead>
			<tr>
				<td>Username</td>
				<td>Password</td>
				<td>Email</td>
				<td>Photo</td>
				<td>Tài khoản</td>
				<td>Trạng thái</td>
			</tr>
		</thead>

		<tbody>
				<tr>
					<td>${ user.username }</td>
					<td>${ user.password }</td>
					<td>${ user.email }</td>
					<td>${user.photo }</td>
					<td>${ user.admin == 1 ? "Admin" : "User" }</td>
					<td>${ user.activated == 1 ? "Đang hoạt động" : "Vô hiệu hóa" }</td>

				</tr>
		</tbody>
	</table>
</body>
</html>