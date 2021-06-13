<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create</title>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
</head>
<body>
	<div class="mt-5 col-10 offset-1">
		<form:form modelAttribute="user" method="POST"
			enctype="multipart/form-data"
			action="${ pageContext.request.contextPath }/admin/account/store">
			<div class="form-group mt-3">
				<label for="username">Name</label>
				<form:input path="username" class="form-control" autocomplete="off" />
				<form:errors path="username" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<label for="email">Email</label>
				<form:input path="email" class="form-control" autocomplete="off" />
				<form:errors path="email" element="span" cssClass="text-danger" />
			</div>
			<c:if test="${ not empty sessionScope.error }">
			<div class="alert alert-danger">${ sessionScope.error }</div>
			<c:remove var="error" scope="session"/>
		   </c:if>
			<div class="form-group mt-3">
				<label for="password">Password</label>
				<form:password path="password" name="password" class="form-control" />
				<form:errors path="password" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<label for="role">Tài khoản</label>
				<form:select path="admin" id="admin" class="form-control">
					<form:option value="1">Admin</form:option>
					<form:option value="0">member</form:option>
				</form:select>
				<form:errors path="admin" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<label for="photo">Image</label> <input type="file"
					class="form-control" name="upload_file">
			</div>
			<div class="form-group mt-3">
				<label for="status">Trạng thái</label>
				<form:select path="activated" id="activated" class="form-control">
					<form:option value="1">Đang hoạt động</form:option>
					<form:option value="0">Vô hiệu hóa</form:option>
				</form:select>
				<form:errors path="activated" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<button class="btn btn-primary">Submit</button>
				<a href="${ pageContext.request.contextPath }/admin/users/create" type="reset" class="btn btn-danger">Clear</a >
			</div>
		</form:form>
	</div>


</body>
</html>