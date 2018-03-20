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
        <title>Users</title>
    </head>
    <body>
        <div class="fixedmenu">
            <div class="fixedleft">
                <img class="menuicon" src="res/lion.png" />
                <a class="breadcrumb" href="user">User</a><br>
                <a class="breadcrumb" href="item">Item</a>
                <a class="breadcrumb" href="customer">Customer</a>
            </div>
            <div class="fixedright">
                <form method="get" action="item">
                    <input type="text" placeholder="Search..." />
                </form>
                <a class="logout" href="main">Logout</a>
            </div>
        </div>
        <h1 class="bodyheaderc">USER</h1>
        <p class="center">${errorMessage}</p>
        
        <div class="rowHeader">
            <div class="rowitemHeader">Username</div>
            <div class="rowitemHeader">Email</div>
            <div class="rowitemHeader">Phone</div>
            <div class="rowitemHeader">First Name</div>
            <div class="rowitemHeader">Last Name</div>
            
        </div>
        <div class="addRow" id="myBtn">
            <img class="addPlus" src="res/plus.png" />
        </div>
        <div id="myModal" class="modal">
            <div class="modal-content">
                <form method="post" action="user">
                    <h1>New User</h1>
                    Username: <input type="text" name="username" placeholder="Username" /><br>
                    First Name: <input type="text" name="firstname" placeholder="First Name" /><br>
                    Last Name: <input type="text" name="lastname" placeholder="Last Name" /><br>
                    Email: <input type="text" name="email" placeholder="Email" /><br>
                    Phone: <input type="text" name="phone" placeholder="Phone" /><br>
                    Address: <input type="text" name="address" placeholder="Address" /><br>
                    Role: <input type="text" name="role" placeholder="Role" /><br>
                    Pay Rate: <input type="text" name="hourly_rate" placeholder="Pay Rate" /><br>
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Save">
                </form>
            </div>
        </div>
        <c:forEach var="user" items="${userList}">
            <div class="rowWrapper">
                <div class="row">
                    <div class="rowitem" name="username">${user.userName}</div>
                    <div class="rowitem" name="email">${user.email}</div>
                    <div class="rowitem" name="phone">
                        <c:forEach var="phone" items="${user.phoneNumberList}">
                            ${phone}
                        </c:forEach>
                    </div>
                    <div class="rowitem" name="firstname">${user.firstName}</div>
                    <div class="rowitem" name="lastname">${user.lastName}</div>
                </div>
                <div class="listOptions">
                    <div class="listButton">
                        <form method="post" action="viewUser">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedUsername" value="${user.userName}">
                            <input type="submit" value="View">
                        </form>
                    </div>
                    <div class="listButton">
                        <form method="post" action="user">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedUsername" value="${user.userName}">
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
