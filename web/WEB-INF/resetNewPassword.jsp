<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Satomi - New Password</title>
    </head>
<body 
    <c:if test="${!empty message}">
        class="trigger_message"
    </c:if>
>  
    <h2>New Password</h2>
    
    <div class="box">
        <div class="add_attributes">
            <form action="resetPassword" method="post"/>
                <p>
                    Enter a new password.
                </p>
                
                <div>
                    <div class="inline">New Password</div><input type="text" name="password" value=""/>
                </div>

                <input type="hidden" name="action" value="newPassword"/>
                <input type="hidden" name="uuid" value="${uuid}"/>
                
                <div>
                    <button type="submit" name="submit" class="ok" value='Submit' onclick=""> 
                        <div>Ok</div>
                    </button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
