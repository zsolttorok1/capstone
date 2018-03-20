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
                <a class="breadcrumb" href="item">Item</a><br>
                <a class="breadcrumb" href="user">User</a>
            </div>
            <div class="fixedright">
                <form method="get" action="item">
                    <input type="text" placeholder="Search..." />
                </form>
                <a class="logout" href="main">Logout</a>
            </div>
        </div>
        <h1 class="bodyheaderc">ITEM</h1>
        <p class="center">${errorMessage}</p>
        
        <div class="rowHeader">
            <div class="rowitemHeader">Item Name</div>
            <div class="rowitemHeader">Description</div>
            <div class="rowitemHeader">Category</div>
            <div class="rowitemHeader">Quantity</div>
        </div>
        <div class="addRow" id="myBtn">
            <img class="addPlus" src="res/plus.png" />
        </div>
        <div id="myModal" class="modal">
            <div class="modal-content">
                <form method="post" action="item">
                    <h1>New Item</h1>
                    Item Name: <input type="text" name="name" placeholder="Item Name" /><br>
                    Description: <input type="text" name="description" placeholder="Description" /><br>
                    Category: <input type="text" name="category" placeholder="Category" /><br>
                    Quantity: <input type="number" name="quantity" placeholder="Quantity" /><br>
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Save">
                </form>
            </div>
        </div>
        
        <c:forEach var="item" items="${sessionScope.itemList}">
            <div class="rowWrapper">
                <div class="row">
                    <div class="rowitem" name="name">${item.itemName}</div>
                    <div class="rowitem" name="description">${item.description}</div>
                    <div class="rowitem" name="category">${item.category}</div>
                    <div class="rowitem" name="quantity">${item.quantity}</div>
                </div>
                <div class="listOptions">
                    <div class="listButton">
                        <form method="post" action="viewItem">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedItemName" value="${item.itemName}">
                            <input type="submit" value="View">
                        </form>
                    </div>
                    <div class="listButton">
                        <form method="post" action="item">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedItemName" value="${item.itemName}">
                            <input type="submit" value="Delete">
                        </form>
                    </div>
                    <div class="listButton">
                        <input type="hidden" name="action" value="cancel">
                        <input type="button" value="Cancel" id="cancelbox">
                    </div>
                </div>
            </div>
        </c:forEach>
        
        
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
