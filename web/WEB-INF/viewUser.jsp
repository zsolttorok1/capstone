        
        <h1 class="bodyheaderc">USER VIEW FOR: ${user.userName}</h1>
        <p class="center">${message}</p>
        <div class="viewcontent">
            <form method="post" action="viewUser">
                <div class="contentInfo">
                    
                    <label for="name">Name:</label><br>
                    <input type="text" id="name" class="contentBodyInputNormal" name="firstName" value="${user.firstName}" />  <input type="text" id="name" class="contentBodyInputNormal" name="lastName" value="${user.lastName}" /><br>
                    <br>
                    <label for="street">Street:</label><br>
                    <input type="text" id="street" class="contentBodyInputSmall" name="houseNumber" value="${user.houseNumber}" />  <input type="text" class="contentBodyInputBig" name="street" value="${user.street}" /><br>
                    <label for="city">City & Province:</label><br>
                    <input type="text" id="city" class="contentBodyInputNormal" name="city" value="${user.city}" />  <input type="text" class="contentBodyInputNormal" name="province" value="${user.province}" /><br>
                    <label for="post">Postal Code & Country:</label><br>
                    <input type="text" id="post" class="contentBodyInputNormal" name="postalCode" value="${user.postalCode}" />  <input type="text" class="contentBodyInputNormal" name="country" value="${user.country}" /><br>
                    <br>
                    <label for="phone">Phone Number(s):</label><br>
                    <c:forEach var="phoneNumber" items="${user.phoneNumberList}">
                        <input type="text" id="phone" class="contentBodyInputNormal" name="phoneNumberList[]" value="${phoneNumber}" />
                    </c:forEach>
                    <br>
                    <label for="email">Email:</label><br>
                    <input type="text" id="email" class="contentBodyInput" name="email" value="${user.email}" />
                    <br><br>
                    <label for="role">Role & Pay Rate:</label><br>
                    <input type="text" id="role" class="contentBodyInputBig" name="role" value="${user.role}" />  <input type="text" class="contentBodyInputSmall" name="hourlyRate" value="${user.hourlyRate}" />$/hr<br>
                    <br>
                    
                </div>
                <input type="hidden" name="action" value="save">
                <input type="hidden" name="userName" value="${user.userName}">
                <input type="submit" value="Save Changes">
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
