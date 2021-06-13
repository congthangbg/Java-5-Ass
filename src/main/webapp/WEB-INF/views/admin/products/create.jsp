<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
	.error{
		color: red;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	Create Product
	${message}
	<form:form action="create" method="POST" modelAttribute="productForm">
		<div>
			Name : <form:input path="name" />
			<span><form:errors path="name" cssClass="error"/></span>
		</div>
		<div>
			Description : <form:input path="description" />
			<span><form:errors path="description" cssClass="error"/></span>
		</div>
		<div>
			Price : <form:input type="number" path="price" />
			<span><form:errors path="price" cssClass="error"/></span>
		</div>
		
		<div>
			Price : <form:input type="number" path="image" />
			<span><form:errors path="image" cssClass="error"/></span>
		</div>
		
		<div>
			<input type="submit" value="Submit" />
		</div>
	</form:form>
</body>
</html>