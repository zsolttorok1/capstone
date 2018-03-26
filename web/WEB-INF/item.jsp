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
        <title>Items</title>
    </head>
    <body>
        <div class="fixedmenu">
            <div class="fixedleft">
                <img class="menuicon" src="res/lion.png" />
            </div>
            <div class="fixedright">
                <form method="get" action="item">
                    <input type="text" placeholder="Search..." />
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
                    <div class="divTable">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableHead">
                                    New Item
                                </div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Item Name:</div>
                                <div class="divTableCell"><input name="name" type="text" placeholder="Item Name" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Description:</div>
                                <div class="divTableCell"><input name="description" type="text" placeholder="Description" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Category:</div>
                                <div class="divTableCell"><input name="category" type="text" placeholder="Category" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Quantity:</div>
                                <div class="divTableCell"><input name="quantity" type="number" placeholder="Quantity" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell"><input name="action" type="hidden" value="add" /> <input type="submit" value="Save" /></div>
                            </div>
                        </div>
                    </div>
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
