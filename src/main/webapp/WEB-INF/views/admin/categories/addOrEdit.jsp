<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<section class="row">
	<div class="col-6 offset-3 mt-4 ">
		
		<form:form action="/admin/categories/saveOrUpdate"  modelAttribute="category" method="post">
			<div class="card">
				<div class="card-header text-center">
					<h2>Add New Category</h2>
				</div>
				<div class="card-body">
					<div class="form-group">
						<label for="categoryId">Category ID :</label> <input type="hidden" >
						<input type="text" value="${category.categoryId }" name="categoryId"
							class="form-control" readonly aria-describedby="categoryHId"
							placeholder="Id">
					</div>
					<div class="form-group">
						<label for="name">Name</label> <form:input type="text"
							class="form-control" path="name" value="${category.name }"
							aria-describedby="nameHId" placeholder="Name"/>
							<form:errors path="name" cssClass="error"></form:errors>
					</div>
				</div>
				<div class="card-footer text-muted">

					<button type="reset" class="btn btn-secondary">Reset</button>
					<a href="/admin/categories" class="btn btn-success">List
						Categories</a>
					<button class="btn btn-primary">
						<i class="fas fa-save"></i>
						<c:if test="${category.isEdit}">
							<span>Update</span>
						</c:if>
						<c:if test="${!category.isEdit}">
							<span>Save</span>
						</c:if>
					</button>
				</div>
			</div>
		</form:form>

	</div>
</section>

