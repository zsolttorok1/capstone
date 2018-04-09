<div class="contentWrapperSubmit">
    <div class="contentSubmit">
        <h1>Employee Login</h1>
        <form action='login' method='POST'>
            <label for="username">Username</label><br>
            <input type="text" id="username" class="contentBodyInputBig" name="userName" value='${userName}'/><br>

            <label for="password">Password (<a href="login?action=resetPassword">Forgot Password</a>)</label><br>
            <input type="password" id="password" class="contentBodyInputBig" name="password" value='${password}'/><br>   

            <div>
                <div class="inlineBlock">Remember Me</div>

                <label class="checkbox">
                    <input type="checkbox" name="remember" value="true" ${checked}>
                    <span></span>
                </label>
            </div>

            <div>
                <button type="submit" class="ok saveButton" value='Login' onclick=""> 
                    <div>Login</div>
                </button>
            </div>

        </form>
        <br/>      
    </div>
</div>
        
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>

