<title>NotesKeepr - Forgot Password</title>
</head>
<body 
    <c:if test="${!empty message}">
        class="trigger_message"
    </c:if>
>  
    <h2>Forgot Password</h2>
    
    <div class="box">
        <div class="add_attributes">
            <form action="resetPassword" method="post"/>
                <p>
                    Please enter your email address to reset your password.
                </p>
                
                <div>
                    <div class="inline">Email address</div><input type="text" name="email" value=""/>
                </div>
                
                <div>
                    <button type="submit" name="submit" class="ok" value='Submit' onclick=""> 
                        <div>Ok</div>
                    </button>
                </div>
                
            </form>
        </div>
    </div>
        
    <div class="message" id="message">
        ${message}
        <div class="message_close" onclick='closeMessage()'></div>
    </div>
</body>
</html>
