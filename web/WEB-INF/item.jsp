<%-- 
    Document   : InventoryServlet
    Created on : Feb 6, 2018, 1:10:21 PM
    Author     : 725899
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Item test</h1>
        
        <c:forEach var="item" items="${sessionScope.itemList}">
            --------------------- <br/>
            Item Name ........: ${item.itemName}<br/>
            Item Quantity ....: ${item.quantity}<br/>
            Item Category ....: ${item.category}<br/>
            Item description .: ${item.description}<br/>
        </c:forEach>
    </body>
</html>
