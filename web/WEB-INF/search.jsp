<%--
    Document   : Search
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
        <title>Search</title>
    </head>
    <body>
        <form method="post" action="search">
            Search for: <input type="text" name="keyword" placeholder="keyword" value="${keyword}" /><br/>
            <input type="submit" value="search">
        </form>
        
        <c:forEach var="item" items="${itemList}">
           Item entry found: ${item.itemName}<br/>
        </c:forEach>
           
        <c:forEach var="user" items="${userList}">
           User entry found: ${user.userName}<br/>
        </c:forEach>

        
    </body>
</html>
