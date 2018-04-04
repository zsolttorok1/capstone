<h1 class="bodyheaderc">CUSTOMERS</h1>

<div class="listWrapper">
    <div class="rowWrapper">
        <div class="rowHeader">
            <div class="rowitemHeader">Name</div>
            <div class="rowitemHeader">Company</div>
            <div class="rowitemHeader">Phone</div>
            <div class="rowitemHeader">Email</div>
            <div class="rowitemHeader">Address</div>
        </div>
    </div>
    
    <div class="rowWrapper">
        <div class="addRow" id="myBtn">
            <img class="addPlus" src="res/plus.png" />
        </div>
    </div>
        
    <c:forEach var="customer" items="${customerList}">
        <div class="rowWrapper">
            <div class="row">
                <div class="rowitem" name="name">${customer.firstName}&nbsp${customer.lastName}</div>
                <div class="rowitem" name="company">${customer.companyName}</div>
                <div class="rowitem" name="phone">
                    <c:forEach var="phone" items="${customer.phoneNumberList}">
                        ${phone}<br>
                    </c:forEach>
                </div>
                <div class="rowitem" name="email">${customer.email}</div>
                <div class="rowitem" name="address">${customer.houseNumber} ${customer.street}</div>
                
                <form method="post" action="viewCustomer">
                    <input type="hidden" name="action" value="view">
                    <input type="hidden" name="customerId" value="${customer.customerId}">
                    <input type="submit" class="rowButton" value="">
                </form>
            </div>
                    
            <form method="post" action="customer">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="customerId" value="${customer.customerId}">
                <button type="submit" class="deleteButton" name="action" value="" onclick="closeForm(this);">
            </form>
                
        </div>
    </c:forEach>
</div>
    
<div id="myModal" class="modal">
    <div class="modal-content">
        <form method="post" action="customer">
            <div class="divTable">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableHead">New Customer</div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">First Name:</div>
                        <div class="divTableCell"><input name="firstName" type="text" placeholder="First Name" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Last Name:</div>
                        <div class="divTableCell"><input name="lastName" type="text" placeholder="Last Name" /></div>
                    </div>
                        <div class="divTableRow">
                        <div class="divTableCell">Email:</div>
                        <div class="divTableCell"><input name="emailAddress" type="text" placeholder="Email" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Phone:</div>
                        <div class="divTableCell"><input name="phoneNumberList[]" id="phoneField" type="text" placeholder="Phone" /></div>
                        <div class="divTableCell" onClick="addNumberInput()"><img class="phonePlus" src="res/plus.png" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell"></div>
                        <div class="divTableCell" id="btnPhone"></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">House Number:</div>
                        <div class="divTableCell"><input name="houseNumber" type="text" placeholder="House Number" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Street Name:</div>
                        <div class="divTableCell"><input name="street" type="text" placeholder="Street Name" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">City:</div>
                        <div class="divTableCell"><input name="city" type="text" placeholder="City" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Province:</div>
                        <div class="divTableCell"><input name="province" type="text" placeholder="Province" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Postal Code:</div>
                        <div class="divTableCell"><input name="postalCode" type="text" placeholder="Postal Code" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Country:</div>
                        <div class="divTableCell"><input name="country" type="text" placeholder="Country" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Company:</div>
                        <div class="divTableCell"><input name="companyName" type="text" placeholder="Company" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Position:</div>
                        <div class="divTableCell"><input name="position" type="text" placeholder="Position" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Notes:</div>
                        <div class="divTableCell"><input name="notes" type="text" placeholder="Some notes." /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell"><input name="action" type="hidden" value="add" /> <input type="submit" value="Save" /></div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
                
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
