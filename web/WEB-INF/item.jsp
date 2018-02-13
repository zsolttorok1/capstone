<%--
    Document   : InventoryServlet
    Created on : Feb 6, 2018, 1:10:21 PM
    Author     : 725899
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
                <a class="breadcrumb" href="main">Item</a>
            </div>
            <div class="fixedright">
                <a class="logout" href="main">Logout</a>
            </div>
        </div>
        <h1 class="bodyheaderc">ITEM</h1>
        <p>${errorMessage}</p>
        <div class="addbutton">Add New Job</div>
        <form method="post" action="item" class="formcenter">
            Item Name: <input type="text" name="name" /><br>
            Description: <input type="text" name="description" /><br>
            Category: <input type="text" name="category" /><br>
            Quantity: <input type="text" name="quantity" /><br>
            <input type="hidden" name="action" value="add">
            <input type="submit" value="save">
        </form>
        <c:forEach var="item" items="${sessionScope.itemList}">
            <div class="row">
                <div class="rowitem">${item.itemName}</div>
                <div class="rowitem">${item.description}</div>
                <div class="rowitem">${item.category}</div>
                <div class="rowitem">${item.quantity}</div>
            </div>
        </c:forEach>
    </body>
</html>
