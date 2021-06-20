<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="mt-5 col-10 offset-1">
	<form:form modelAttribute="user" method="POST"
		enctype="multipart/form-data"
		action="${ pageContext.request.contextPath }/admin/account/update/${user.id }">

		<div class="card">
			<div class="card-header text-center">
				<h2>Add New Account</h2>
			</div>
			<div class="card-body">
				<div class="row">
				<div class="col-md-2"></div>
					<div class="col">

						<div class="form-group mt-3">
							<label for="name">Name</label>
							<form:input path="username" class="form-control" id="username"
								name="username" autocomplete="off" />
							<form:errors path="username" element="span"
								cssClass="text-danger" />
						</div>
						<div class="form-group mt-3">
							<label for="email">Email</label>
							<form:input path="email" class="form-control" id="email"
								name="email" autocomplete="off" readonly="true" />
							<form:errors path="email" element="span" cssClass="text-danger" />
						</div>
						<div class="form-group mt-3">
							<label for="password">Password</label>
							<form:password path="password" value="*******" name="password"
								class="form-control" readonly="true" />
							<form:errors path="password" element="span"
								cssClass="text-danger" />
						</div>
						<div class="form-group mt-3">
							<label for="role">Tài khoản</label>
							<form:select path="admin" class="form-control">
								<form:option value="1">Admin</form:option>
								<form:option value="0">User</form:option>
							</form:select>
							<form:errors path="admin" element="span" cssClass="text-danger" />
						</div>
						<div class="form-group mt-3">
							<label for="photo">Image</label> <br> <img alt=""
								src="${ pageContext.request.contextPath }/uploads/${user.photo}"
								width="100px"><br> <label for="photo">Create
								or Update </label> <br> <input type="file" class="form-control"
								name="upload_file">
						</div>
						<div class="form-group mt-3">
							<label for="status">Trạng thái</label>
							<form:select path="activated" id="activated" class="form-control">
								<form:option value="1">Đang hoạt động</form:option>
								<form:option value="0">Vô hiệu hóa</form:option>
							</form:select>
							<form:errors path="activated" element="span"
								cssClass="text-danger" />
						</div>
						<div class="form-group mt-3">
							<button class="btn btn-primary">Submit</button>
							<button type="reset" class="btn btn-danger">Clear</button>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
	</form:form>
</div>
