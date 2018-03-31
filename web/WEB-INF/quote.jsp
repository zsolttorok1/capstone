<%--
    Document   : InventoryServlet
    Created on : Feb 6, 2018, 1:10:21 PM
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
        <title>Quote</title>
    </head>
    <body>
        <div class="fixedmenu">
            <div class="fixedleft">
                <img class="menuicon" src="res/lion.png" />
            </div>
            <div class="fixedright">
                <a class="logout" href="main">Logout</a>
            </div>
            <div class="fixedright">
                <form method="post" action="search">
                    <input type="text" name="keyword" placeholder="Search..." value="${keyword}" />
                    <input type="submit" value="Search">
                </form>
            </div>
        </div>
        
        <div id="mySidenav" class="sidenav">
            <a href="item">Item Inventory</a>
            <a href="user">Employees</a>
            <a href="customer">Customers</a>
            <a href="job">Jobs</a>
            <a href="report">Reports</a>
            <a href="quote">Quotes</a>
            <a href="">----</a>
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">Close</a>
        </div>
        
        <span style="font-size:20px;cursor:pointer" onclick="openNav()">&#9776; MENU</span>
        
        <h1 class="bodyheaderc">QUOTE</h1>
        <p class="center">${message}</p>
        
        <div class="rowHeader">
            <div class="rowitemHeader">Name</div>
            <div class="rowitemHeader">Email</div>
            <div class="rowitemHeader">Description</div>

            
        </div>
        <c:forEach var="quote" items="${quoteList}">
            <div class="rowWrapper">
                <div class="row">
                    <div class="rowitem" name="quoteName">${quote.name}</div>
                    <div class="rowitem" name="email">${quote.email}</div>
                    <div class="rowitem" name="description">${quote.description}</div>
                </div>
                <div class="listOptions">
                    <div class="listButton">
                        <form method="post" action="quote">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="quoteName" value="${quote.name}">
                            <input type="submit" value="View">
                        </form>
                    </div>
                    <div class="listButton">
                        <form method="post" action="quote">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="quoteName" value="${quote.name}">
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
