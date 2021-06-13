<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <style type="text/css">
      .error{
      color: red;
      }
      </style>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container" style="margin-top: 10px;">
            <div class="row" style="border: 1px darkgrey solid; border-radius: 10px;width: 50%; margin: 0 auto; padding: 20px;" >
                <div class="col-sm-12">
                    <h2>Register Account</h2>
                    <form:form action="/account/SaveOrUpdate" modelAttribute="ACCOUNT" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label>UserName</label>
                            <form:input type="text" class="form-control" path="username"  placeholder="Enter UserName"/>
                       		<form:errors path="username" cssClass="error"></form:errors>
                        </div>
                        <div class="form-group">
                            <label>Password:</label>
                            <form:input type="password" class="form-control" path="password"  placeholder="Enter password"/>
                            <form:errors path="password" cssClass="error"></form:errors>
                        </div>
                        <div class="form-group">
                            <label>FullName</label>
                            <form:input type="text" class="form-control" path="fullname"  placeholder="Enter fullname"/>                         
                       <form:errors path="fullname" cssClass="error"></form:errors>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <form:input type="text" class="form-control" path="email"  placeholder="Enter email"/>
                            <form:errors path="email" cssClass="error"></form:errors>
                        
                        </div>
						<div class="form-group">
                            <label>Photo</label>
                            <input type="file" class="form-control" name="image"  placeholder="Enter photo"/>
                        	<p class="error">${ErrorPhoto }</p>
                        </div>
                        <div class="form-group">
                            <label>Status</label>
                            <div class="radio"><!--value = 1 => Nam| 0 =>Nu -->
                                <label class="checkbox-inline">
                                	<form:checkbox path="activated"/>Activated</label>
                                <label class="checkbox-inline">
									<form:checkbox path="admin" />Admin</label>
                            </div>
                        </div>
                        <form:button type="submit" class="btn btn-primary">Submit</form:button>
                        <form:button type="reset" class="btn btn-primary">Cancel</form:button>
                        <a href="/account/views" class="btn btn-primary">Display Account</a>
                    </form:form>
                </div>
            </div>
        </div>
    </body>
</html>