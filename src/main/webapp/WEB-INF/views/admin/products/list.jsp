<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="tag" %>
<!-- Section-->
<div>
	<section class="py-5">
		<h1 class="text-center"><tag:message code="label.home.listProduct"/></h1>
		<div class="container px-4 px-lg-5 mt-5">
		<div class="row">
		
				<div class="col-md-6">
					<form action="/admin/cateId" method="get">
				<div class="form-row align-items-center mb-5">
				
					<div class="row">
					<div class="col-6">
						<label class="mr-sm-2" for="inlineFormCustomSelect">Lọc sản phẩm</label>
						<select class="custom-select mr-sm-2 form-control"  name="category" id="inlineFormCustomSelect">
							<option value="999">All</option>
						<c:forEach items="${listCate }" var="cate">
						<option value=${cate.categoryId }>${cate.name }</option>
						</c:forEach>
						
						</select>
						</div>
						<div class="col-3 mt-4">
						<button type="submit" class="btn btn-success">Lọc</button></div>
					</div>

				</div>
			</form>
				</div>
				<div class="col-md-6">
				<form action="/admin/search" method="post">
				<div class="form-row align-items-center mb-5">
				
					<div class="row">
					<div class="col-6">
						<label class="mr-sm-2" for="name">Tìm kiếm</label>
						<div class="col-md-12">
								<input type="text" class="form-control ml-2" name="productName"
									id="name" aria-describedby="nameHId" placeholder="Name">
							</div>
							
						</div>
						<div class="col-md-2 mt-4">
								<button type="submit" class="btn btn-success">Search</button>
							</div>
					</div>

				</div>
			</form>
				</div>
			</div>
		
		
			
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="product" items="${ListProduct.content}">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<c:choose>
								<c:when test="${product.image == null ||  product.image == ''}">
									<img class="card-img-top" src="../../../uploads/maytinh.jpg"
										alt="..." />
								</c:when>
								<c:otherwise>

									<img class="card-img-top"
										src="../../../uploads/${product.image }" alt="..." />
								</c:otherwise>
							</c:choose>

							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${product.name}</h5>
									<!-- Product price-->
									${product.price}
									<h5>${product.category.name }</h5>
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" onclick="confirm()"
										href="/admin/shoppingCart/add/${product.id }">Add to Cart</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

			</div>
			<div class="card-footer text-muted p-4 pt-0 border-top-0 bg-transparent">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<li class="page-item "><a class="page-link" href="/admin?p=0"
							aria-label="First"> <span aria-hidden="true">&laquo;</span> <span
								class="sr-only">First</span>
						</a></li>
						<li class="page-item "><a class="page-link"
							href="/admin?p=${ListProduct.number <=0 ? 0 :(ListProduct.number  -1 )}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								<span class="sr-only">Previous</span>
						</a></li>

						<li class="page-item "><a class="page-link" href="#">${ListProduct.number}</a></li>
						<li class="page-item"><a class="page-link"
							href="/admin?p=${ListProduct.number + 1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
						</a></li>
						<li class="page-item "><a class="page-link"
							href="/admin?p=${ListProduct.totalPages - 1}" aria-label="Last">
								<span aria-hidden="true">&raquo;</span> <span class="sr-only">Last</span>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>

	</section>

</div>
<script>
	function confirm() {
		alert("Thêm vào giỏ hàng thành công ! ");
	}
</script>

