        
        <h1 class="bodyheaderc">USER VIEW FOR: ${user.userName}</h1>
        <p class="center">${message}</p>
        <div class="viewcontent">
            <div>
                <img class="editIcon" src="res/editIcon.png" title="Edit" id="editIcon" />
            </div>
            <form method="post" action="viewUser">
                <div class="contentInfo">
                    
                    <p class="contentHeader">Name</p>
                    <p class="contentBody"><input type="text" name="firstName" value="${user.firstName}">
                        <input type="text" name="lastName" value="${user.lastName}">
                    </p>
                    
                    <p class="contentHeader">Address</p>
                    <p class="contentBody">Street: <input type="text" name="houseNumber" value="${user.houseNumber}">
                        <input type="text" name="street" value="${user.street}">,<br>
                        City & Province: <input type="text" name="city" value="${user.city}">
                        <input type="text" name="province" value="${user.province}"><br>
                        Postal Code: <input type="text" name="postalCode" value="${user.postalCode}"><br>
                        Country: <input type="text" name="country" value="${user.country}">
                    </p>
                    
                    <p class="contentHeader">Phone Number(s)</p>
                    <p class="contentBody">
                        <c:forEach var="phoneNumber" items="${user.phoneNumberList}">
                            <input type="text" name="phoneNumberList[]" value=${phoneNumber}>
                        </c:forEach>
                    </p>
                    
                    <p class="contentHeader">Email</p>
                    <p class="contentBody"><input type="text" name="email" value="${user.email}"></p>
                    
                    <p class="contentHeader">Role & Pay Rate</p>
                    <p class="contentBody"><input type="text" name="role" value="${user.role}">
                    <input type="text" name="hourlyRate" value="${user.hourlyRate}">$/hr
                    </p>
                    
                </div>
                <input type="hidden" name="action" value="save">
                <input type="hidden" name="userName" value="${user.userName}">
                <input type="submit" value="Save Changes">
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
