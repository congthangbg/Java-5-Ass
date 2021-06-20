<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="tag" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div>
	<h2 class="text-center mt-4 mb-4"><tag:message code="label.home.listAccount"/></h2>
	<div class="col-10 offset-1 mt-5  p-2">
		<form method="GET"
			action="${ pageContext.request.contextPath }/admin/account">
			<div class="row col-12 mt-2">
				<div class="col-2">
					<label>Sắp xếp theo</label> <select name="sort_by"
						class="form-control">
						<option value="id">Mặc định</option>
						<option value="username">Họ Tên</option>
						<option value="email">Email</option>
						<option value="admin">Tài khoản</option>
						<option value="activated">Trạng thái</option>
					</select>
				</div>
				<div class="col-2">
					<label>Thứ tự</label> <select name="sort_direction"
						class="form-control">
						<option value="asc">Tăng dần</option>
						<option value="desc">Giảm dần</option>
					</select>
				</div>
				<div class="col-8">
					<button class="btn btn-primary mt-4">Lọc</button>
					<a href="${ pageContext.request.contextPath }/admin/account"
						class="btn btn-danger mt-4" type="reset"> Reset </a>
				</div>
			</div>
		</form>
	</div>

	<div class="mt-2 col-10 offset-1 border border-primary p-2">
		<c:if test="${ not empty sessionScope.sucessfully }">
			<div class="text-center alert alert-success">${ sessionScope.sucessfully }</div>
			<c:remove var="sucessfully" scope="session" />
		</c:if>
		<c:if test="${ not empty sessionScope.status }">
			<div class="text-danger text-center alert alert-success">${ sessionScope.status }</div>
			<c:remove var="status" scope="session" />
		</c:if>
		<div class="">
			<a class="btn btn-success col-1"
				href="${ pageContext.request.contextPath }/admin/account/create">Create</a>
		</div>
		<table class="table table-strip table-dark mt-3">
			<thead>
				<tr>
					<td>Id</td>
					<td>Username</td>
					<td>Email</td>
					<td>Tài khoản</td>
					<td>Trạng thái</td>
					<td colspan="2">Thao tác</td>
				</tr>
			</thead>
			<!-- 

<form
								action="${ pageContext.request.contextPath }/admin/account/delete/${user.id}"
								method="POST">
								<button class="btn btn-danger">Delete</button>
							</form>
 -->
			<tbody>
				<c:forEach items="${ pageData.content }" var="user">
					<tr>
						<td>${ user.id }</td>
						<td>${ user.username }</td>
						<td>${ user.email }</td>
						<td>${ user.admin == 1 ? "Admin" : "User" }</td>
						<td>${ user.activated == 1 ? "Đang hoạt động" : "Vô hiệu hóa" }</td>
						<td><a class="btn btn-primary"
							href="${ pageContext.request.contextPath }/admin/account/edit/${user.id}">Update</a>
						</td>
						<td>
							<button onclick="openRelativeModal(${ user.id })" type="button"
								class="btn btn-danger" data-bs-toggle="modal"
								data-bs-target="#exampleModal${ user.id  }">Delete</button> <!-- Modal -->
							<div class="modal fade" id="exampleModal${ user.id  }"
								tabindex="-1" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title text-dark" id="exampleModalLabel">Modal
												title</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body text-dark">Bạn chắc chắn?</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Close</button>

											<form
												action="${ pageContext.request.contextPath }/admin/account/delete/${user.id}"
												method="POST">
												<button class="btn btn-danger">Delete</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div>
			<ul class="pagination">
				<c:forEach begin="0" end="${ pageData.totalPages - 1 }"
					varStatus="page">
					<li class="page-item"><a
						href="${ pageContext.request.contextPath }/admin/account/?page=${page.index}"
						class="page-link">${ page.index + 1 } </a></li>
				</c:forEach>
			</ul>
			
		</div>
	</div>
</div>