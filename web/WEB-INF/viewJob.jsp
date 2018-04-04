        <h1 class="bodyheaderc">${job.jobName} for ${job.customer.companyName}</h1>
        <p class="center">${message}</p>
                    
        <div class="viewcontentJob">
            <form method="post" action="viewJob">
                <div class="contentInfo">
                    <h1 class="bodyheaderc">Company Management</h1>
                    <p class="contentHeader">Employees Assigned</p>
                    <c:forEach var="user" items="${job.userList}">
                        Employee Name: ${user.firstName} ${user.lastName}<br>
                        Hours:  ${user.hours} hour(s)<br>
                        ---------------------------------------<br>
                    </c:forEach>
                    <div class="editIconJob"><img class="editIconJob" src="res/editIcon.png" title="Edit" id="editIconEmployee" />  Assign New Employees  </div>
                    
                    <p class="contentHeader">Items in Use</p>
                    <c:forEach var="item" items="${job.itemList}">
                        Item Name: ${item.itemName}<br>
                        Category: ${item.category}<br>
                        ---------------------------------------<br>
                    </c:forEach>
                        <div class="editIconJob"><img class="editIconJob" src="res/editIcon.png" title="Edit" id="editIconItem" />  Assign New Items  </div>
                        <br>
                </div>
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save Changes">
            </form>
        </div>
        
        <div class="viewcontentJob">
            <form method="post" action="viewJob">
                <div class="contentInfo">
                    <h1 class="bodyheaderc">Job Information</h1>
                    
                    <p class="contentHeader">Description</p>
                    <input type="text" id="desc" class="contentBodyInput" name="description" value="${job.description}" />
                    <p class="contentHeader">Location</p>
                    <label for="street">Street:</label><br>
                    <input type="text" id="street" class="contentBodyInputSmall" name="houseNumber" value="${job.customer.houseNumber}" />  <input type="text" class="contentBodyInputBig" name="street" value="${job.customer.street}" /><br>
                    <label for="city">City & Province:</label><br>
                    <input type="text" id="city" class="contentBodyInputNormal" name="city" value="${job.customer.city}" />  <input type="text" class="contentBodyInputNormal" name="province" value="${job.customer.province}" /><br>
                    <label for="post">Postal Code & Country:</label><br>
                    <input type="text" id="post" class="contentBodyInputNormal" name="postalCode" value="${job.customer.postalCode}" />  <input type="text" class="contentBodyInputNormal" name="country" value="${job.customer.country}" /><br>
                    <br>
                    <p class="contentHeader">Contact Info for ${job.customer.firstName}&nbsp${job.customer.lastName}&nbsp(${job.customer.position})</p>
                    <label for="phone">Phone Number(s):</label><br>
                    <c:forEach var="phoneNumber" items="${job.customer.phoneNumberList}">
                        <input type="text" id="phone" class="contentBodyInputNormal" name="phoneNumberList[]" value="${phoneNumber}" />
                    </c:forEach>
                    <br>
                    <label for="email">Email:</label><br>
                    <input type="text" id="email" class="contentBodyInput" name="email" value="${job.customer.email}" />
                    <br><br>
                    
                    <p class="contentHeader">Additional Information</p>
                    <label for="start">Start Date:</label><br>
                    <input type="text" id="start" class="contentBodyInputNormal" name="dateStarted" value="${job.dateStarted}" />
                    <br>
                    <label for="end">End Date:</label><br>
                    <input type="text" id="end" class="contentBodyInputNormal" name="dateFinished" value="${job.dateFinished}" />
                    <br>
                    <label for="balance">Balance & Status:</label><br>
                    <input type="text" id="balance" class="contentBodyInputNormal" name="balance" value="${job.balance}" />
                    <input type="text" class="contentBodyInputNormal" name="status" value="${job.status}" />
                    <br><br>
                </div>
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save Changes">
            </form>
        </div>
                    
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
