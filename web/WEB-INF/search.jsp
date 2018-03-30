<%--
    Document   : Search
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
        <title>Search</title>
    </head>
    <body>
        
        <div class="fixedmenu">
            <div class="fixedleft">
                <img class="menuicon" src="res/lion.png" />
            </div>
            <div class="fixedright">
                <form method="post" action="search">
                    <input type="text" name="keyword" placeholder="Search..." value="${keyword}" />
                    <input type="submit" value="Search">
                </form>
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
        
        <h1 class="bodyheaderc">Search Results for: ${keyword}</h1>
        <p class="center">${message}</p>
        
        <form method="post" action="search">
            Search for: <input type="text" name="keyword" placeholder="Search..." value="${keyword}" /><br/>
            <input type="submit" value="Search">
        </form>
        
        <c:forEach var="item" items="${itemList}">
           Item entry found: ${item.itemName}<br/>
        </c:forEach>
           
        <c:forEach var="user" items="${userList}">
           User entry found: ${user.userName}<br/>
        </c:forEach>

        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
