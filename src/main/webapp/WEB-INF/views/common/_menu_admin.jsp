<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="tag" %>
<!-- Navigation-->

 
 <nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">
      <div class="container px-4 px-lg-5">
          <a class="navbar-brand" href="/admin"><img alt="" src="../../../uploads/logo.png" height="50px">AsmShop</a>
           <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
           <div class="collapse navbar-collapse" id="navbarSupportedContent">
               <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                   <li class="nav-item"><a class="nav-link active" aria-current="page" href="/admin">Home</a></li>
                   <li class="nav-item"><a class="nav-link" href="/admin/products/views/page">Product</a></li>
                    <li class="nav-item"><a class="nav-link" href="/admin/categories">Category</a></li>
                    <li class="nav-item"><a class="nav-link" href="/admin/account">Account</a></li>
                     <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                     
                   <li class="nav-item"><a class="nav-link" href="/admin?lang=vi">Tiếng Anh</a></li>
						<li class="nav-item"><a class="nav-link" href="/admin?lang=en">Tiếng Việt</a></li>
               </ul>
               <form class="d-flex">
                   <a class="btn btn-outline-dark" type="submit" href="/admin/shoppingCart/views">
                       <i class="bi-cart-fill me-1"></i>
                      My Cart
                 
                   </a>
               </form>
           </div>
      </div>
    </nav>
    
    