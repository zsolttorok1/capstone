        
        <h1 class="bodyheaderc">USER VIEW FOR: ${user.userName}</h1>

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
                    
                    <%-- *** Phone stuff --%>
                    <div class="phoneNumberList">
                        <input name="phoneNumberList[]"  type="text" placeholder="Phone" value="${user.phoneNumberList[0]}"/>
                        <div class="phonePlus" onClick="javasctipt:addNumberInput(this)"></div><br/>
                        <c:forEach var="phoneNumber" items="${user.phoneNumberList}" varStatus="loop">
                            <c:if test="${loop.index > 0}"> 
                                <input type="text" id="phone" name="phoneNumberList[]" value="${phoneNumber}" /><div class="phoneMinus" onClick="javasctipt:deleteNumberInput(this)"></div><br/>
                            </c:if>
                        </c:forEach>
                    </div>
                        
                    <br>
                    <label for="email">Email:</label><br>
                    <input type="text" id="email" class="contentBodyInput" name="email" value="${user.email}" />
                    <br><br>
                    <label for="role">Role & Pay Rate:</label><br>
                    <select name="role" class="contentBodyInputBig">
                        <option value="employee" <c:if test="${user.role eq 'employee'}">selected</c:if> >employee</option>
                        <option value="manager" <c:if test="${user.role eq 'manager'}">selected</c:if> >manager</option>
                        <option value="owner" <c:if test="${user.role eq 'owner'}">selected</c:if> >owner</option>
                    </select>
                    <input type="text" class="contentBodyInputSmall" name="hourlyRate" value="${user.hourlyRate}" />$/hr<br>
                    <br>
                    
                </div>
                <input type="hidden" name="action" value="save">
                <input type="hidden" name="userName" value="${user.userName}">
                <input type="submit" value="Save Changes" class="saveChangesButton">
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
