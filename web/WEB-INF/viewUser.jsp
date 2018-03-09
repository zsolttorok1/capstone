<%-- 
    Document   : viewUser
    Created on : Mar 7, 2018, 4:22:00 PM
    Author     : 685442
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
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
        <h1 class="bodyheaderc">USER VIEW</h1>
        <p class="center">${errorMessage}</p>
        <div class="viewcontent">
            <div>
                <img class="editIcon" src="res/editIcon.png" title="Edit" id="editIcon" />
            </div>
            <form method="post" action="viewItem">
                <div class="contentInfo">
                    
                    <p class="contentHeader">Username</p>
                    <p>${userName}</p>
                    
                    <p class="contentHeader">Name</p>
                    <p class="contentBody">${firstname} ${lastname}</p>
                    
                    <p class="contentHeader">Address</p>
                    <p class="contentBody">${address_id}</p>
                    
                    <p class="contentHeader">Phone Number(s)</p>
                    <p class="contentBody">
                        <c:forEach var="phone" items="${user.phoneNumberList}">
                            ${phone}<br>
                        </c:forEach>
                    </p>
                    
                    <p class="contentHeader">Email</p>
                    <p class="contentBody">${email}</p>
                    
                </div>
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save Changes">
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
