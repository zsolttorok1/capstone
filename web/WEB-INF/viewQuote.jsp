<%-- 
    Document   : viewUser
    Created on : Mar 7, 2018, 4:22:00 PM
    Author     : 685442 and 742227
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
                <a class="breadcrumb" href="user">Quote</a>
            </div>
            <div class="fixedright">
                <a class="logout" href="main">Logout</a>
            </div>
        </div>
        <h1 class="bodyheaderc">QUOTE VIEW FOR: ${quote.name}</h1>
        <p class="center">${message}</p>
        <div class="viewcontent">
            <form method="post" action="quote">
                <div class="contentInfo">
                    
                    <p class="contentHeader">Name</p>
                    <p class="contentBody">${quote.name} </p>
                    
                    <p class="contentHeader">Email Address</p>
                    <p class="contentBody">${quote.email}</p>
                    
                    <p class="contentHeader">Description</p>
                    <p class="contentBody">${quote.description}</p>
                     

                    
                </div>
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
