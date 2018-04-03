
    
<div class="listWrapper">
    <h2>SATOMI SOFTWARE - Login</h2>
    
    <div class="box">
        <div class="add_attributes">
            <form action='login' method='POST'>

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
</div>
        
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>

