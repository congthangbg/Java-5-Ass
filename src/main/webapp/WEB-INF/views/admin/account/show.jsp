<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

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
