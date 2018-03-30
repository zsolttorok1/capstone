<%-- 
    Document   : submitQuote
    Created on : Mar 20, 2018, 5:26:50 PM
    Author     : 742227
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submit Quote</title>
    </head>
    <body>
        <h1>Submit a Quote</h1>
        <form  action="submitQuote" name= "submitQuote" method="post">
            <p>Name:  <input type ="text" name="name" value=""></p>
            <p>Email:  <input type ="text" name="email" value=""></p>
            <p>Description:  <input type ="text" name="description" value=""></p>
            
            <input type ="submit" value="Submit" >
            <input type="hidden" name="action" value="submit">
        </form>
         <p>${errorMessage}</p>
    </body>
</html>
