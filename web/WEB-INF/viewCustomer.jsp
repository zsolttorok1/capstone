    
        <h1 class="bodyheaderc">CUSTOMER VIEW FOR: ${customer.firstName} ${customer.lastName}</h1>

        <div class="viewcontent">
            <form method="post" action="viewCustomer">
                <div class="contentInfo">
                    
                    <label for="name">Name:</label><br>
                    <input type="text" id="name" class="contentBodyInputNormal" name="firstName" value="${customer.firstName}" />  <input type="text" id="name" class="contentBodyInputNormal" name="lastName" value="${customer.lastName}" /><br>
                    <br>
                    <label for="street">Street:</label><br>
                    <input type="text" id="street" class="contentBodyInputSmall" name="houseNumber" value="${customer.houseNumber}" />  <input type="text" class="contentBodyInputBig" name="street" value="${customer.street}" /><br>
                    <label for="city">City & Province:</label><br>
                    <input type="text" id="city" class="contentBodyInputNormal" name="city" value="${customer.city}" />  <input type="text" class="contentBodyInputNormal" name="province" value="${customer.province}" /><br>
                    <label for="post">Postal Code & Country:</label><br>
                    <input type="text" id="post" class="contentBodyInputNormal" name="postalCode" value="${customer.postalCode}" />  <input type="text" class="contentBodyInputNormal" name="country" value="${customer.country}" /><br>
                    <br>
                    <label for="phone">Phone Number(s):</label><br>
                      
                    <%-- *** Phone stuff --%>
                    <div class="phoneNumberList">
                        <input name="phoneNumberList[]"  type="text" placeholder="Phone" value="${customer.phoneNumberList[0]}"/>
                        <div class="phonePlus" onClick="javasctipt:addNumberInput(this)"></div><br/>
                        <c:forEach var="phoneNumber" items="${customer.phoneNumberList}" varStatus="loop">
                            <c:if test="${loop.index > 0}"> 
                                <input type="text" name="phoneNumberList[]" value="${phoneNumber}" /><div class="phoneMinus" onClick="javasctipt:deleteNumberInput(this)"></div><br/>
                            </c:if>
                        </c:forEach>
                    </div>
                        
                    <br>
                    <label for="email">Email:</label><br>
                    <input type="text" id="email" class="contentBodyInput" name="email" value="${customer.email}" />
                    <br><br>
                    <label for="role">Company & Position:</label><br>
                    <input type="text" id="role" class="contentBodyInputNormal" name="companyName" value="${customer.companyName}" />  <input type="text" class="contentBodyInputNormal" name="position" value="${customer.position}" /><br>
                    <br>
                    <label for="notes">Notes:</label><br>
                    <textarea class="contentBodyInputNormal description" name="notes">${customer.notes}</textarea>
                    <br>
                    <br>
                    
                </div>
                <input type="hidden" name="action" value="save">
                <input type="hidden" name="customerId" value="${customer.customerId}">
                <input type="submit" value="Save Changes" class="saveChangesButton">
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
