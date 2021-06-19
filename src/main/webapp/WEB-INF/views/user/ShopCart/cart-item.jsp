<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container">
	<section class="row ">
		<div class="col mt-4 ">
			<h2 class="text-center">My Shopping Cart</h2>
			<div class="row">
				<div class="col">
					<a class="btn btn-warning btn-md" href="/user/shoppingCart/clear">Clear
						Cart</a> <a class="btn btn-success btn-md" href="/user/">Add more</a>
				</div>
			</div>
			<div class="col-sm-12 text-center">
				<table class="table table-striped ">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Name</th>
							<th scope="col">Price</th>
							<th scope="col">Quantity</th>
							<th scope="col">Amount</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

<c:forEach var="item" items="${cartItems}">
	<form action="/user/shoppingCart/update" method="post">
		<input type="hidden" name="id" value="${item.productId}" />
		<tr>
			<td scope="row">${item.productId }</td>
			<td>${item.name }</td>
			<td>${item.price}</td>
			<td><input name="quantity" value="${item.quantity}"
				onblur="this.form.submit()" style="width: 50px;"></td>
			<td>${item.price*item.quantity}</td>
			<!-- 
			<td><a class="btn btn-danger btn-sm"
				href="/admin/shoppingCart/remove/${item.productId }">Remove</a></td>
			 -->
			<td>
				<button onclick="openRelativeModal(${ item.iproductIdd })"
					type="button" class="btn btn-danger btn-sm"
					data-bs-toggle="modal"
					data-bs-target="#exampleModal${item.productId  }">Remove</button>
				<!-- Modal -->
				<div class="modal fade" id="exampleModal${item.productId }"
					tabindex="-1" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title text-dark" id="exampleModalLabel">Warning
									!!!</h5>
								<button type="button" class="btn-close"
									data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body text-dark">Bạn chắc chắn muốn
								xóa?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>

								<a href="/user/shoppingCart/remove/${item.productId }"
									class="btn btn-danger">Remove</a>
							</div>
						</div>
					</div>
				</div>
			</td>
		</tr>
	</form>
</c:forEach>


					</tbody>
				</table>


				<hr />

			</div>
			<div class="row float-right">
				<div class="col-md-8 text-center">
			
				</div>
				<div class="col-md-4 float-right">
					<div class="row">
						<div class="col-md-7">
							<p>Tổng Tiền:${total}</p>
						</div>
						<div class="col-md-5">
							<a class="btn btn-warning btn-md"  onclick="confirm();">Thanh
								toán</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<script>
  		
  	function confirm() {
  		alert("Bạn đã thanh toán thành công : " + ${total} + " VND");
  	}
  	
  </script>
