        <h1 class="bodyheaderc">${job.jobName} for ${job.customer.companyName}</h1>

<form method="post" action="viewJob">
    <div class="viewcontentJob">
        <input type="hidden" name="jobName" value="${job.jobName}">
        <input type="submit" class="saveChangesButton" name="save" value="Save Changes">

        <div class="contentInfo">
            <h1 class="bodyheaderc">Company Management</h1>

            <p class="contentHeader">Employees Assigned</p>
            <p class="contentSubHeader">Employee name & work hours:</p>
            <c:forEach var="user" items="${job.userList}">
                <div class="contentBodyInputNormal fixed2 uneditable">${user.firstName}&nbsp;${user.lastName}</div>
                <input name="userList[]" type="hidden" value="${user.userName}"/>
                <input name="hoursList[]" class="contentBodyInputSmall" type="number" placeholder="Hours" value="${user.hours}"/><input class="deleteButtonSmall2" type="submit" name="unassignedUserName" value="${user.userName}"/>
                <br/>
            </c:forEach>

            <p class="contentSubHeader">Assign new employee:</p>
            <select name="assignedUserName" class="contentBodyInputNormal">
                <c:forEach var="user" items="${unasignedUserList}">
                    <option value="${user.userName}">${user.firstName}&nbsp;${user.lastName}</option>
                </c:forEach>
            </select>
            <input name="hours" class="contentBodyInputSmall" type="number" placeholder="Hours" value="0"/>
            <input name="assignUser" class="addButtonSmall" type="submit"/>
            <br/>

            <p class="contentHeader">Items in Use</p>
            <c:forEach var="item" items="${job.itemList}">
                <div class="contentBlockWrapper">
                    <label for="city">Item name &amp; used quantity:</label><br/>
                    <div class="contentBodyInputNormal fixed uneditable"/>${item.itemName}</div>
                    <input name="itemList[]" type="hidden" value="${item.itemName}"/>

                    <input name="quantityList[]" class="contentBodyInputSmall fixed" type="number" placeholder="Quantity" value="${item.quantity}"/><input class="deleteButtonSmall" type="submit" name="unallocatedItemName" value="${item.itemName}"><br/><div class="contentBodyLeftInStock"><span>In stock:</span><span>${item.inventoryQuantity}</span></div><br/>
                    <label for="city">Item notes:</label><br/>
                        <textarea name="noteList[]" class="contentBodyInputNormalTextArea">${item.note}</textarea>
                    <br>
                </div>
            </c:forEach>

            <p class="contentSubHeader">Allocate new item:</p>
            <select name="allocatedItemName" class="contentBodyInputNormal">
                <c:forEach var="item" items="${unasignedItemList}">
                    <option value="${item.itemName}">${item.itemName}</option>
                </c:forEach>
            </select>
            <input name="quantity" class="contentBodyInputSmall" type="number" placeholder="Quantity" value="1"/>
            <input name="allocateItem" class="addButtonSmall" type="submit"/>
            <br/>
            
            <p class="contentHeader">Reports</p>
            <c:forEach var="report" items="${reportList}">
                <fmt:formatDate value="${report.dateCreated}" var="dateFormatted" type="date" pattern="MM/dd/yyyy (HH:mm)" />
                <div class="reportList">
                    ${dateFormatted}
                    <input type="submit" name="reportId" value="${report.reportId}"/>
                </div>
            </c:forEach>
            
            <p class="contentSubHeader">Generate new report with description:</p>
            
            <div class="generateReport">
                <textarea class="contentBodyInputNormalTextArea2" name="reportDescription"></textarea>
                <input name="generate" class="addButtonSmall" type="submit" />
            </div>
        </div>
    </div>
        
    <div class="viewcontentJob">
        <div class="contentInfo">
            <h1 class="bodyheaderc">Job Information</h1>

            <p class="contentHeader">Description</p>
            <input type="text" id="desc" class="contentBodyInput" name="description" value="${job.description}" />
            <p class="contentHeader">Location</p>
            <label for="street">Street:</label><br>
            <input type="text" id="street" class="contentBodyInputSmall" name="houseNumber" value="${job.houseNumber}" />  <input type="text" class="contentBodyInputBig" name="street" value="${job.street}" /><br>
            <label for="city">City & Province:</label><br>
            <input type="text" id="city" class="contentBodyInputNormal" name="city" value="${job.city}" />  <input type="text" class="contentBodyInputNormal" name="province" value="${job.province}" /><br>
            <label for="post">Postal Code & Country:</label><br>
            <input type="text" id="post" class="contentBodyInputNormal" name="postalCode" value="${job.postalCode}" />  <input type="text" class="contentBodyInputNormal" name="country" value="${job.country}" /><br>
            <br>
            <p class="contentHeader">Contact Info for ${job.customer.firstName}&nbsp${job.customer.lastName}&nbsp(${job.customer.position})</p>
            <label for="phone">Phone Number(s):</label><br>

            <%-- *** Phone stuff --%>
            <div class="phoneNumberList">
                <div class="uneditable">${job.customer.phoneNumberList[0]}</div>
                <br/>
                <c:forEach var="phoneNumber" items="${job.customer.phoneNumberList}" varStatus="loop">
                    <c:if test="${loop.index > 0}">
                        <div class="uneditable">${phoneNumber}</div>
                        <br/>
                    </c:if>
                </c:forEach>
            </div>

            <br>
            <label for="email">Email:</label><br>
            <div class="contentBodyInput uneditable">${job.customer.email}</div>
            <br><br>

            <p class="contentHeader">Additional Information</p>
            <label for="start">Start Date:</label><br>
            <input type="date" id="start" class="contentBodyInputNormal" name="dateStarted" value="${job.dateStarted}" />
            <br>
            <label for="end">End Date:</label><br>
            <input type="date" id="end" class="contentBodyInputNormal" name="dateFinished" value="${job.dateFinished}" />
            <br>
            <label for="balance">Balance & Status:</label><br>
            <input type="text" id="balance" class="contentBodyInputNormal" name="balance" value="${job.balance}" />
            <input type="text" class="contentBodyInputNormal" name="status" value="${job.status}" />
            <br><br>
        </div>
    </div>
</form>
                    
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
