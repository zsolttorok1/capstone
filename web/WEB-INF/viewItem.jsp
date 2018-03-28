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
            </div>
            <div class="fixedright">
                <a class="logout" href="main">Logout</a>
            </div>
        </div>
        
        <div id="mySidenav" class="sidenav">
            <a href="item">Item Inventory</a>
            <a href="user">Employees</a>
            <a href="customer">Customers</a>
            <a href="jobs">Jobs</a>
            <a href="report">Reports</a>
            <a href="quote">Quotes</a>
            <a href="">----</a>
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">Close</a>
        </div>
        
        <span style="font-size:20px;cursor:pointer" onclick="openNav()">&#9776; MENU</span>
        
        <h1 class="bodyheaderc">ITEM VIEW</h1>
        <p class="center">${errorMessage}</p>
        <div class="viewcontent">
            <div>
                <img class="editIcon" src="res/editIcon.png" title="Edit" id="editIcon" />
            </div>
            <form method="post" action="viewItem">
                <div class="contentInfo">
                    <p class="contentHeader">Name and Category</p>
                    <p>${itemName} (${category})</p>
                    <input type="hidden" name="name" value="${itemName}" />
                    <input type="text" class="contentBodyInput" placeholder="Category" name="categoryEdit" value="${category}" />
                    <p class="contentHeader">Description</p>
                    <p class="contentBody">${description}</p>
                    <textarea cols="100" rows="5" class="contentBodyInput" placeholder="Description" name="descriptionEdit" >${description}</textarea>
                    <p class="contentHeader">Quantity</p>
                    <p class="contentBody">${quantity}</p>
                    <input type="number" class="contentBodyInput" placeholder="Quantity" name="quantityEdit" value="${quantity}" />
                    <p class="contentHeader">Additional Notes</p>
                    <p class="contentBody">${notes}</p>
                </div>
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save Changes">
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
