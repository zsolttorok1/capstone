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
        <div class="addRow" id="myBtn">
            <img class="addPlus" src="res/plus.png" />
        </div>
        <div id="myModal" class="modal">
            <div class="modal-content">
                <form method="post" action="customer">
                    <div class="divTable">
                        <div class="divTableBody">
                            <div class="divTableRow">
                                <div class="divTableHead">New User</div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">First Name:</div>
                                <div class="divTableCell"><input name="firstName" type="text" placeholder="First Name" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Last Name:</div>
                                <div class="divTableCell"><input name="lastName" type="text" placeholder="Last Name" /></div>
                            </div>
                                <div class="divTableRow">
                                <div class="divTableCell">Email:</div>
                                <div class="divTableCell"><input name="emailAddress" type="text" placeholder="Email" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Phone:</div>
                                <div class="divTableCell"><input name="phoneNumberList[]" id="phoneField" type="text" placeholder="Phone" /></div>
                                <div class="divTableCell" onClick="addNumberInput()"><img class="phonePlus" src="res/plus.png" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell"></div>
                                <div class="divTableCell" id="btnPhone"></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">House Number:</div>
                                <div class="divTableCell"><input name="houseNumber" type="text" placeholder="House Number" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Street Name:</div>
                                <div class="divTableCell"><input name="street" type="text" placeholder="Street Name" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">City:</div>
                                <div class="divTableCell"><input name="city" type="text" placeholder="City" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Province:</div>
                                <div class="divTableCell"><input name="province" type="text" placeholder="Province" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Postal Code:</div>
                                <div class="divTableCell"><input name="postalCode" type="text" placeholder="Postal Code" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Country:</div>
                                <div class="divTableCell"><input name="country" type="text" placeholder="Country" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Company:</div>
                                <div class="divTableCell"><input name="companyName" type="text" placeholder="Company" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell">Position:</div>
                                <div class="divTableCell"><input name="position" type="text" placeholder="Position" /></div>
                            </div>
                            <div class="divTableRow">
                                <div class="divTableCell"><input name="action" type="hidden" value="add" /> <input type="submit" value="Save" /></div>
                            </div>
                        </div>
                    </div>
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
                            ${phone}<br>
                        </c:forEach>
                    </div>
                    <div class="rowitem" name="email">${customer.email}</div>
                    <div class="rowitem" name="address">${customer.houseNumber} ${customer.street}</div>
                </div>
                <div class="listOptions">
                    <div class="listButton">
                        <form method="post" action="viewCustomer">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="customerName" value="${customer.customerName}">
                            <input type="submit" value="View">
                        </form>
                    </div>
                    <div class="listButton">
                        <form method="post" action="customer">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="customerName" value="${customer.customerName}">
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
