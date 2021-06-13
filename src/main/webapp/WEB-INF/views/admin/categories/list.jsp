<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<section class="row ">
		<div class="col mt-4 ">
			<div class="card">
				<div class="card-header">
					<h2>List of Category</h2>
				</div>
				<div class="card-body">
					<c:if test="${ not empty sessionScope.message }">
						<div class="alert alert-success mt-4">${ sessionScope.message }</div>
						<c:remove var="message" scope="session" />
					</c:if>

					<div class="row">
						<div class="col-md-10">
							<form action="/admin/categories">
								<div class=" row ">
									<div class="col-md-1 mt-2">
										<label for="name">Name :</label>


									</div>
									<div class="col-md-5">
										<input type="text" class="form-control ml-2" name="name"
											id="name" aria-describedby="nameHId" placeholder="Name">
									</div>
									<div class="col-md-2">
										<button class="btn btn-outline-primary ml-2">Search</button>
									</div>
								</div>
							</form>
						</div>
						<div class="col-md-2">
							<div class="col">

								<a class="btn btn-outline-primary" href="/admin/categories/add">Add
									New Category</a>

							</div>
						</div>
					</div>
					<c:if test="${!categoryPage.hasContent()}">
						<div class="row mt-4">
							<div class="col">
								<div class="alert alert-danger" role="alert">
									<strong>No category found</strong>
								</div>
							</div>
						</div>
					</c:if>
					<table class="table table-striped table-inverse ">
						<thead class="thead-inverse">
							<tr>
								<th>Category ID</th>
								<th>Name</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${categoryPage.content }" var="cate">
								<tr>
									<td scope="row">${cate.categoryId }</td>
									<!-- th:text="${category.name}" -->
									<td>${cate.name }</td>
									<td>
									 <a
										href="${pageContext.request.contextPath}/admin/categories/edit/${cate.categoryId}"
										class="btn btn-outline-warning"><i class="fas fa-edit "></i>Edit</a>
										<a
										href="${pageContext.request.contextPath}/admin/categories/delete/${cate.categoryId}"
										class="btn btn-outline-danger" onclick="confirmDelete();"><i
											class="fas fa-recycle "></i>Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card-footer text-muted">

					<div class="row">
						<div class="col col-md-1">
							<div class="form-group col-md-12">
								<form action="">
									<label for="inputState">Page Size :</label> <select
										id="inputState" class="form-control" name="size" id="size"
										onchange="this.form.submit()">
										<option value="5">5</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
									</select>
								</form>
							</div>
						</div>
						<div class="col">
							<ul class="pagination justify-content-center mt-3">
								<li class="page-item "><a class="page-link"
									href="/admin/categories?page=1" aria-label="First"> <span
										aria-hidden="true">&laquo;</span> <span class="sr-only">First</span>
								</a></li>
								<li class="page-item "><a class="page-link"
									href="/admin/categories?page=${categoryPage.number <=0 ? 1:categoryPage.number}"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">Previous</span>
								</a></li>
								<li class="page-item "><a class="page-link" href="#">${categoryPage.number +1}</a></li>
								<li class="page-item"><a class="page-link"
									href="/admin/categories?page=${categoryPage.number + 2 }"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Next</span>
								</a></li>
								<li class="page-item "><a class="page-link"
									href="/admin/categories?page=${categoryPage.totalPages}"
									aria-label="Last"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only">Last</span>
								</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

		</div>
	</section>
</div>



