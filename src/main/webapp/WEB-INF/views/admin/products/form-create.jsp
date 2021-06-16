<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container ">
	<section class="row">
		<div class="col mt-4 ">
			<form:form action="/admin/products/add" modelAttribute="product"
				method="post">
				<div class="card">
					<div class="card-header text-center">
						<h2>Add New Product</h2>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-8">
								<div class="form-group mb-4">
									<label for="productId">Product ID</label> <input type="hidden">
									<input type="text" readonly class="form-control" name="id"
										value="${product.id }" id="productId"
										aria-describedby="productHId" placeholder="Product ID">

								</div>

								<div class="form-group mt-4 mb-4">
									<label for="name">Name</label>
									<form:input type="text" class="form-control"
										value="${product.name }" path="name" id="name"
										aria-describedby="nameHId" placeholder="Name" />
									<form:errors path="name" cssClass="error"></form:errors>
								</div>
								<div class="input-group form-group mt-4 mb-2">
									<span class="input-group-text">Unit Price</span>
									<form:input type="number" path="price" id="unitPrice"
										value="${product.price }" class="form-control"
										placeholder="Unit Price" />
									<span class="input-group-text">$</span>

								</div>
								<form:errors path="price" cssClass="error"></form:errors>
								<div class="form-group mt-4 mb-4">
									<label for="categoryId">Category</label> <select
										class="form-control" name="category" id="categoryId">
										<c:forEach items="${listCate }" var="cate">
											<c:choose>
												<c:when
													test="${product.category.categoryId == cate.categoryId }">

													<option value=" ${cate.categoryId }" selected>${cate.name}</option>
												</c:when>
												<c:otherwise>
													<option value=" ${cate.categoryId }">${cate.name}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>

							</div>
							<div class="col-4">
								<c:choose>
								<c:when test="${product.image == null ||  product.image == ''}">
									<img src="../../../../uploads/maytinh.jpg" width="80%"
									class="img-fluid {3|rounded-top,rounded-right,rounded-bottom,rounded-left,rounded-circle,|}"
									alt="">
								</c:when>
								<c:otherwise>
									
										<img class="card-img-top"
										src="../../../uploads/${product.image }" alt="..." />
								</c:otherwise>
							</c:choose>
								<div class="form-group mt-4">
									<label for="productImage">Image Files</label>
									 <input
										type="file" class="form-control-file" name="image"
										id="productImage" placeholder="Image file"
										aria-describedby="productImage">
										
								</div>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted text-center">
						<button  type="reset" class="btn btn-success">Clear</button>
						<button class="btn btn-primary">Save</button>
					</div>
				</div>
			</form:form>
		</div>
	</section>
</div>