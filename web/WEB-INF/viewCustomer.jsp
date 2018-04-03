    
        <h1 class="bodyheaderc">CUSTOMER VIEW FOR: ${customer.firstName} ${customer.lastName}</h1>

        <div class="viewcontent">
            <div>
                <img class="editIcon" src="res/editIcon.png" title="Edit" id="editIcon" />
            </div>
            <form method="post" action="viewCustomer">
                <div class="contentInfo">
                    
                    <p class="contentHeader">Name</p>
                    <p class="contentBody"><input type="text" name="firstName" value="${customer.firstName}">
                        <input type="text" name="lastName" value="${customer.lastName}">
                    </p>
                    
                    <p class="contentHeader">Address</p>
                    <p class="contentBody">Street: <input type="text" name="houseNumber" value="${customer.houseNumber}">
                        <input type="text" name="street" value="${customer.street}">,<br>
                        City & Province: <input type="text" name="city" value="${customer.city}">
                        <input type="text" name="province" value="${customer.province}"><br>
                        Postal Code: <input type="text" name="postalCode" value="${customer.postalCode}"><br>
                        Country: <input type="text" name="country" value="${customer.country}">
                    </p>
                    
                    <p class="contentHeader">Phone Number(s)</p>
                    <p class="contentBody">
                        <c:forEach var="phoneNumber" items="${customer.phoneNumberList}">
                            <input type="text" name="phoneNumberList[]" value=${phoneNumber}>
                        </c:forEach>
                    </p>
                    
                    <p class="contentHeader">Email</p>
                    <p class="contentBody"><input type="text" name="email" value="${customer.email}"></p>
                    
                    <p class="contentHeader">Company & Position</p>
                    <p class="contentBody"><input type="text" name="company" value="${customer.companyName}">
                    <input type="text" name="position" value="${customer.position}">
                    </p>
                    
                </div>
                <input type="hidden" name="action" value="save">
                <input type="hidden" name="customerId" value="${customer.customerId}">
                <input type="submit" value="Save Changes">
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
