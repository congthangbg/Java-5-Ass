<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">

	<div class="text-center mt-4 mb-4">
		<h2>List Products</h2>
	</div>
	<div class="row">
		<div class="col-sm-12">

			<div class="row mt-4">
				<div class="col-md-10">
					<form action="/admin/products/views/page" >
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

						<a class="btn btn-outline-primary" href="/admin/products/views">Add
							New Product</a>

					</div>
				</div>
			</div>
			<c:if test="${ not empty sessionScope.message }">
            <div class="alert alert-success mt-4">${ sessionScope.message }</div>
            <c:remove var="message" scope="session"/>
           </c:if>
			<table class="table table-striped mt-5 mb-5">
				<thead>
					<tr>
						<th><a href="/admin/products/views/page?field=id"
							style="text-decoration: none">ID</a></th>
						<th><a href="/admin/products/views/page?field=name"
							style="text-decoration: none">Name</a></th>
						<th><a href="/admin/products/views/page?field=price"
							style="text-decoration: none">Price</a></th>
						<th><a href="/admin/products/views/page?field=createDate"
							style="text-decoration: none">Create Date</a></th>
						<th>Category</th>
						<th>Photo</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<!-- <a class="btn btn-danger btn-sm" href="/admin/products/delete/${p.id }"  >Del</a> -->
					<tr>
						<c:forEach items="${ListProduct.content}" var="p">
							<tr>
								<td scope="row">${p.id }</td>
								<td>${p.name }</td>
								<td>${p.price }</td>
								<td>${p.createDate }</td>
								<td>${p.category.name }</td>
								<td align="center" width="120"><img
									src="https://dienmaythienhoa.vn/static/images/4.%20hinh%20sp/3.%20Hinh%20SP%202/laptop-asus-s330fn-ey037t-1.png"
									width="100" height="100" /></td>
								<td><a class="btn btn-primary btn-sm" href="/admin/products/edit/${p.id }">Edit</a>| 
								<button onclick="openRelativeModal(${ p.id })" type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${ p.id }">
                                  Delete

                                </button>
                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal${ p.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                  <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                      <div class="modal-header">
                                        <h5 class="modal-title text-dark" id="exampleModalLabel">Modal title</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                      </div>
                                      <div class="modal-body text-dark">
                                        Bạn chắc chắn? 
                                      </div>
                                      <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <a id="delete" type="button" class="btn btn-primary" href="/admin/products/delete/${p.id }" >Xóa</a>
                                      </div>
                                    </div>
                                  </div>
                                </div>
								</td>
							</tr>

						</c:forEach>
					</tr>
				</tbody>
			</table>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center mt-3">
					<li class="page-item"><a class="page-link"
						href="/admin/products/views/page?p=0">First</a></li>
					<li class="page-item"><a class="page-link"
						href="/admin/products/views/page?p=${ListProduct.number <=0 ? 0 :(ListProduct.number  -1 )}">Previous</a></li>
					<li class="page-item"><a class="page-link" href="">${ListProduct.number}</a></li>
					<li class="page-item"><a class="page-link"
						href="/admin/products/views/page?p=${ListProduct.number + 1 }">Next</a></li>
					<li class="page-item"><a class="page-link"
						href="/admin/products/views/page?p=${ListProduct.totalPages -1 }">Last</a></li>
				</ul>
				<ul>
					<li>Số thực thể hiện tại : ${ListProduct.numberOfElements }</li>
					<li>Trang số : ${ListProduct.number }</li>
					<li>Kích thước trang : ${ListProduct.size }</li>
					<li>Tổng số thực thể : ${ListProduct.totalElements }</li>
					<li>Tổng số trang : ${ListProduct.totalPages }</li>
				</ul>
			</nav>
		</div>
	</div>

</div>

