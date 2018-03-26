<%-- 
    Document   : viewUser
    Created on : Mar 7, 2018, 4:22:00 PM
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
        <title>Customer</title>
    </head>
    <body>
        <div class="fixedmenu">
            <div class="fixedleft">
                <img class="menuicon" src="res/lion.png" />
                <a class="breadcrumb" href="user">User</a>
            </div>
            <div class="fixedright">
                <a class="logout" href="main">Logout</a>
            </div>
        </div>
        <h1 class="bodyheaderc">CUSTOMER VIEW FOR: ${customer.firstName} ${customer.lastName}</h1>
        <p class="center">${message}</p>
        <div class="viewcontent">
            <div>
                <img class="editIcon" src="res/editIcon.png" title="Edit" id="editIcon" />
            </div>
            <form method="post" action="viewCustomer">
                <div class="contentInfo">
                    
                    <p class="contentHeader">Name</p>
                    <p class="contentBody"><input type="text" name="firstName" value="${customer.firstName}">
                        <input type="text" name="lastName" value="${customer.lastName}">
                    </p>
                    
                    <p class="contentHeader">Address</p>
                    <p class="contentBody">Street: <input type="text" name="houseNumber" value="${customer.houseNumber}">
                        <input type="text" name="street" value="${customer.street}">,<br>
                        City & Province: <input type="text" name="city" value="${customer.city}">
                        <input type="text" name="province" value="${customer.province}"><br>
                        Postal Code: <input type="text" name="postalCode" value="${customer.postalCode}"><br>
                        Country: <input type="text" name="country" value="${customer.country}">
                    </p>
                    
                    <p class="contentHeader">Phone Number(s)</p>
                    <p class="contentBody">
                        <c:forEach var="phoneNumber" items="${customer.phoneNumberList}">
                            <input type="text" name="phoneNumberList[]" value=${phoneNumber}>
                        </c:forEach>
                    </p>
                    
                    <p class="contentHeader">Email</p>
                    <p class="contentBody"><input type="text" name="email" value="${customer.email}"></p>
                    
                    <p class="contentHeader">Company & Position</p>
                    <p class="contentBody"><input type="text" name="company" value="${customer.companyName}">
                    <input type="text" name="position" value="${customer.position}">
                    </p>
                    
                </div>
                <input type="hidden" name="action" value="save">
                <input type="hidden" name="customerName" value="${customer.customerName}">
                <input type="submit" value="Save Changes">
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
