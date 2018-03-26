<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Satomi - Login</title>
    </head>
<body
    <c:if test="${!empty message}">
        class="trigger_message"
    </c:if>
>
    <h2>Satomi - Login</h2>
    
    <div class="box">
        <div class="add_attributes">
            <form action='login' method='POST'>
            <p>
                    SATOMI SOFTWARE
            </p>
            <div>
                <div class="inline">Username</div><input type='text' name='userName' value='${userName}'>
            </div>
            <div>
                <div class="inline">Password</div><input type='password' name='password' value='${password}'>
            </div>
            
            <div>
                <div class="inline">Remember Me</div>
                
                <label class="checkbox">
                    <input type="checkbox" name="remember" value="true" ${checked}>
                    <span></span>
                </label>
            </div>
                    
            <div>
                <button type="submit" class="ok" value='Login' onclick=""> 
                    <div>Login</div>
                </button>
            </div>

          </form>
        </div>
    </div>
    <div><a href="login?action=resetPassword">Forgot Password</a></div>
        
    <div class="message" id="message">
        ${message}
        <div class="message_close" onclick='closeMessage()'></div>
    </div>
</body>
</html>
