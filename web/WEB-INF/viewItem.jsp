<%-- 
    Document   : viewItem
    Created on : Feb 22, 2018, 10:46:40 AM
    Author     : 685442
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Items</title>
    </head>
    <body>
        <div class="fixedmenu">
            <div class="fixedleft">
                <img class="menuicon" src="res/lion.png" />
                <a class="breadcrumb" href="item">Item</a>
            </div>
            <div class="fixedright">
                <a class="logout" href="main">Logout</a>
            </div>
        </div>
        <h1 class="bodyheaderc">ITEM VIEW</h1>
        <p class="center">${errorMessage}</p>
        <div class="viewcontent">
            <p class="contentHeader">Name and Category</p>
            <p>${itemName} (${category})}</p>
            <p class="contentHeader">Description</p>
            <p>${description}</p>
            <p class="contentHeader">Quantity</p>
            <p>${quantity}</p>
            <p class="contentHeader">Additional Notes</p>
            <p>${notes}</p>
        </div>
    </body>
</html>
