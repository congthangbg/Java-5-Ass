<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
<title>Insert title here</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/login.css" />
</head>
<body>
	<section class="login-block">
		<div class="container">
			<div class="row">
				<div class="col-md-4 login-sec">
					<h2 class="text-center">Login Now</h2>
					<form class="login-form" method="POST" action="${ pageContext.request.contextPath }/login">
						<div class="form-group">
							<c:if test="${ not empty sessionScope.error }">
								<div class="alert alert-danger">${ sessionScope.error }</div>
								<c:remove var="error" scope="session" />
							</c:if>
							
							<label for="email" class="text-uppercase">Email</label>
							<input type="email" name="email" id="email" class="form-control" placeholder="">

						</div>
						<div class="form-group">
							<label for="password" class="text-uppercase">Password</label>
							<input type="password" name="password" id="password" class="form-control" placeholder="">
						</div>


						<div class="form-check">
							<label class="form-check-label"> <input type="checkbox"
								class="form-check-input"> <small>Remember Me</small>
							</label>
							<button type="submit" class="btn btn-login float-right">Submit</button>
						</div>

					</form>
					<div class="copy-text">
						Created with <i class="fa fa-heart"></i> by Công Thắng
					</div>
				</div>
				<div class="col-md-8 banner-sec">
					<div id="carouselExampleIndicators" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleIndicators" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active">
								<img class="d-block img-fluid"
									src="https://static.pexels.com/photos/33972/pexels-photo.jpg"
									alt="First slide">
								<div class="carousel-caption d-none d-md-block">
									<div class="banner-text">
										<h2>This is Heaven</h2>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing
											elit, sed do eiusmod tempor incididunt ut labore et dolore
											magna aliqua. Ut enim ad minim veniam, quis nostrud
											exercitation</p>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<img class="d-block img-fluid"
									src="https://images.pexels.com/photos/7097/people-coffee-tea-meeting.jpg"
									alt="First slide">
								<div class="carousel-caption d-none d-md-block">
									<div class="banner-text">
										<h2>This is Heaven</h2>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing
											elit, sed do eiusmod tempor incididunt ut labore et dolore
											magna aliqua. Ut enim ad minim veniam, quis nostrud
											exercitation</p>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<img class="d-block img-fluid"
									src="https://images.pexels.com/photos/872957/pexels-photo-872957.jpeg"
									alt="First slide">
								<div class="carousel-caption d-none d-md-block">
									<div class="banner-text">
										<h2>This is Heaven</h2>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing
											elit, sed do eiusmod tempor incididunt ut labore et dolore
											magna aliqua. Ut enim ad minim veniam, quis nostrud
											exercitation</p>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				</div>
			</div>
	</section>
</body>
</html>