<div class="contentWrapperSubmit">
    <div class="contentSubmit">
        <h1>New Password</h1>
        <form action="resetPassword" method="post"/>
            <p>
                Enter a new password.
            </p>
            
            <label for="password">New Password</label><br>
            <input type="password" id="password" class="contentBodyInputBig" name="password" value=''/><br/>

            <input type="hidden" name="action" value="newPassword"/>
            <input type="hidden" name="uuid" value="${uuid}"/>

            <div>
                <button type="submit" name="submit" class="ok saveButton" value='Submit' onclick="">
                    <div>Submit</div>
                </button>
            </div>
        </form>
    </div>
</div>

    
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
