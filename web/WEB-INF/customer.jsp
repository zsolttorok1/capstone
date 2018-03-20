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
                <a class="breadcrumb" href="customer">Customer</a><br>
                <a class="breadcrumb" href="item">Item</a>
                <a class="breadcrumb" href="user">User</a>
            </div>
            <div class="fixedright">
                <form method="get" action="item">
                    <input type="text" placeholder="Search..." />
                </form>
                <a class="logout" href="main">Logout</a>
            </div>
        </div>
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
        <form method="post" action="user">
            <div class="rowAdd" id="formcenter2">
                <div class="rowitemAdd"><input class="addText" type="text" name="username" placeholder="Username" /></div>
                <div class="rowitemAdd"><input class="addText" type="text" name="email" placeholder="Email" /></div>
                <div class="rowitemAdd"><input class="addText" type="text" name="phone" placeholder="Phone" /></div>
                <div class="rowitemAdd"><input class="addText" type="text" name="firstname" placeholder="First Name" /></div>
                <div class="rowitemAdd"><input class="addText" type="text" name="lastname" placeholder="Last Name" /></div>
            </div>
            <div class="rowAddOptions" id="addbuttons">
                <div class="rowAddButton">
                    <input type="hidden" name="action" value="add">
                    <input type="submit" value="Save">
                </div>
                <div class="rowAddButton">
                    <input type="hidden" name="action" value="cancel">
                    <input type="button" value="Cancel" id="cancel">
                </div>
            </div>
        </form>
        
        <c:forEach var="customer" items="${customerList}">
            <div class="rowWrapper">
                <div class="row">
                    <div class="rowitem" name="username">${customer.firstName} ${customer.lastName}</div>
                    <div class="rowitem" name="email">${customer.companyName}</div>
                    
                    <div class="rowitem" name="firstname">${customer.email}</div>
                    <div class="rowitem" name="lastname">${customer.addressId}</div>
                </div>
                <div class="listOptions">
                    <div class="listButton">
                        <form method="post" action="viewUser">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedUsername" value="${customer.firstName}">
                            <input type="submit" value="View">
                        </form>
                    </div>
                    <div class="listButton">
                        <form method="post" action="user">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedUsername" value="${customer.firstName}">
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
