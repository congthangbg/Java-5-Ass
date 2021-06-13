<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Navigation-->
   <nav class="navbar navbar-expand-lg navbar-light bg-light">
       <div class="container px-4 px-lg-5">
           <a class="navbar-brand" href="#!"><img alt="" src="../../../uploads/logo.png" height="50px">AsmShop</a>
           <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
           <div class="collapse navbar-collapse" id="navbarSupportedContent">
               <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                   <li class="nav-item"><a class="nav-link active" aria-current="page" href="/home">Home</a></li>
                   <li class="nav-item"><a class="nav-link" href="#!">Product</a></li>
                    <li class="nav-item"><a class="nav-link" href="#!">Category</a></li>
                     <li class="nav-item"><a class="nav-link" href="#!">Login</a></li>
                      <li class="nav-item"><a class="nav-link" href="#!">Register</a></li>
                   <li class="nav-item dropdown">
                       <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Account</a>
                       <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                           <li><a class="dropdown-item" href="#!">Logout</a></li>
                           <li><hr class="dropdown-divider" /></li>
                           <li><a class="dropdown-item" href="#!">Change Password</a></li>
                           <li><a class="dropdown-item" href="#!">Edit account</a></li>
                       </ul>
                   </li>
               </ul>
               <form class="d-flex">
                   <button class="btn btn-outline-dark" type="submit">
                       <i class="bi-cart-fill me-1"></i>
                       Cart
                       <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                   </button>
               </form>
           </div>
       </div>
 </nav>