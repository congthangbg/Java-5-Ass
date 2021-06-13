<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
layout:decorate="~{admin/dashBoardLayout.html}">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath}/resource/css/styles.css" rel="stylesheet" />

     <style type="text/css">
      .error{
      color: red;
      }
      </style>
    
    </head>
    <body>
        <!-- Menu -->
        <tiles:insertAttribute name="menu" />
        <!-- Header-->
        <tiles:insertAttribute name="header" />
        <!-- content -->
        <tiles:insertAttribute name="body" />
        <!-- Footer-->
        <tiles:insertAttribute name="footer" />
    </body>
</html>
