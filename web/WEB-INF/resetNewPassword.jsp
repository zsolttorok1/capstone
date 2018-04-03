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
