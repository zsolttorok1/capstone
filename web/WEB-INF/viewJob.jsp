
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Job</title>
    </head>
    <body>
        <div class="fixedmenu">
            <div class="fixedleft">
                <img class="menuicon" src="res/lion.png" />
            </div>
            <div class="fixedright">
                <a class="logout" href="main">Logout</a>
                <a class="logout" href="login?action=logout">RealLogout</a>
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
        
        <h1 class="bodyheaderc">${job.jobName}</h1>
        <p class="center">${message}</p>
            
            <div class="viewcontentJob">
            <div>
                <img class="editIcon" src="res/editIcon.png" title="Edit" id="editIcon" />
            </div>
            <form method="post" action="viewJob">
                <div class="contentInfo">
                    <h1 class="bodyheaderc">Job Information</h1>
                    <p class="contentHeader">Job Name</p>
                    <p class="contentBody">${job.jobName}
                    </p>
                    
                    <p class="contentHeader">Location</p>
                    <p class="contentBody">Street: <input type="text" name="houseNumber" value="${job.customer.houseNumber}">
                        <input type="text" name="street" value="${job.customer.street}">,<br>
                        City & Province: <input type="text" name="city" value="${job.customer.city}">
                        <input type="text" name="province" value="${job.customer.province}"><br>
                        Postal Code: <input type="text" name="postalCode" value="${job.customer.postalCode}"><br>
                        Country: <input type="text" name="country" value="${job.customer.country}">
                    </p>
                    
                    <p class="contentHeader">Contact Info</p>
                    <p class="contentBody">
                        ${job.customer.firstName} ${job.customer.lastName}:<br> Phone Number(s): <c:forEach var="phoneNumber" items="${job.customer.phoneNumberList}">
                            <input type="text" name="phoneNumberList[]" value=${phoneNumber}>
                        </c:forEach>
                        <p class="contentBody">Email Address: <input type="text" name="email" value="${job.customer.email}"></p>
                    
                    <p class="contentHeader">Start and End Date:</p>
                    <p class="contentBody">Start: <input type="text" name="dateStarted" value="${job.dateStarted}"></p>
                    <p class="contentBody">End:  <input type="text" name="dateFinished" value="${job.dateFinished}"></p>
                    
                    <p class="contentHeader">Status and Balance</p>
                    <p class="contentBody">Status: <input type="text" name="status" value="${job.status}"></p>
                    <p class="contentBody">Balance: $<input type="number" name="balance" value="${job.balance}">
                    </p>
                </div>
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save Changes">
            </form>
        </div>
                    
        <div class="viewcontentJob">
            <form method="post" action="viewJob">
                <div class="contentInfo">
                    <h1 class="bodyheaderc">Company Management</h1>
                    <p class="contentHeader">Employees Assigned</p>
                    <c:forEach var="user" items="${job.userList}">
                        Employee Name: ${user.firstName} ${user.lastName}<br>
                        Hours:  ${user.hours} hour(s)<br>
                        ---------------------------------------<br>
                    </c:forEach>
                    <div class="editIconJob"><img class="editIconJob" src="res/editIcon.png" title="Edit" id="editIconEmployee" />  Assign New Employees  </div>
                    
                    <p class="contentHeader">Items in Use</p>
                    <c:forEach var="item" items="${job.itemList}">
                        Item Name: ${item.itemName}<br>
                        Category: ${item.category}<br>
                        ---------------------------------------<br>
                    </c:forEach>
                        <div class="editIconJob"><img class="editIconJob" src="res/editIcon.png" title="Edit" id="editIconItem" />  Assign New Items  </div>
                        <br>
                </div>
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save Changes">
            </form>
        </div>
                    
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
