<%--
    Document   : ReportServlet
    Created on : Feb 6, 2018, 1:10:21 PM
    Author     : 725899
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report</title>
    </head>
    <body>
        <div class="fixedmenu">
            <div class="fixedleft">
                <img class="menuicon" src="res/lion.png" />
                <a class="breadcrumb" href="user">User</a><br>
                <a class="breadcrumb" href="item">Item</a>
            </div>
            <div class="fixedright">
                <form method="get" action="item">
                    <input type="text" placeholder="Search..." />
                </form>
                <a class="logout" href="main">Logout</a>
            </div>
        </div>
        <h1 class="bodyheaderc">REPORT</h1>
        <p class="center">${errorMessage}</p>
        
        <form method="post" action="report">
            <input type="hidden" name="action" value="generate">
            <input type="submit" value="Generate test report">
        </form>
         
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
