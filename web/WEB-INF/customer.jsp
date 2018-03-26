<%-- 
    Document   : customer
    Created on : Mar 19, 2018, 6:06:09 PM
    Author     : Steven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customers</title>
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
        
        <h1 class="bodyheaderc">CUSTOMERS</h1>
        <p class="center">${errorMessage}</p>
        
        <div class="rowHeader">
            <div class="rowitemHeader">Name</div>
            <div class="rowitemHeader">Company</div>
            <div class="rowitemHeader">Phone</div>
            <div class="rowitemHeader">Email</div>
            <div class="rowitemHeader">Address</div>
        </div>
        <div class="addRow" id="addbutton">
            <img class="addPlus" src="res/plus.png" />
        </div>
        <div id="myModal" class="modal">
            <div class="modal-content">
                <form method="post" action="user">
                    <h1>New Customer</h1>
                    First Name: <input type="text" name="firstname" placeholder="First Name" /><br>
                    Last Name: <input type="text" name="lastname" placeholder="Last Name" /><br>
                    Email: <input type="text" name="email" placeholder="Email" /><br>
                    Phone: <input type="text" name="phone" placeholder="Phone" /><br>
                    Address: <input type="text" name="address" placeholder="Address" /><br>
                    Company: <input type="text" name="company" placeholder="Company" /><br>
                    Position: <input type="text" name="position" placeholder="Position" /><br>
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Save">
                </form>
            </div>
        </div>
        
        <c:forEach var="customer" items="${customerList}">
            <div class="rowWrapper">
                <div class="row">
                    <div class="rowitem" name="name">${customer.firstName} ${customer.lastName}</div>
                    <div class="rowitem" name="company">${customer.companyName}</div>
                    <div class="rowitem" name="phone">
                        <c:forEach var="phone" items="${customer.phoneNumberList}">
                            ${phone}
                        </c:forEach>
                    </div>
                    <div class="rowitem" name="email">${customer.email}</div>
                    <div class="rowitem" name="address">${customer.houseNumber} ${customer.street}</div>
                </div>
                <div class="listOptions">
                    <div class="listButton">
                        <form method="post" action="viewCustomer">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedUsername" value="${customer.customerName}">
                            <input type="submit" value="View">
                        </form>
                    </div>
                    <div class="listButton">
                        <form method="post" action="customer">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedUsername" value="${customer.customerName}">
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
