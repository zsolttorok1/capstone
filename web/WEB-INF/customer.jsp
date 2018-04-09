<h1 class="bodyheaderc">CUSTOMER LIST</h1>

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
    
<div id="myModal" class="modal newUser">
    <div class="modal-content">
        <form method="post" action="customer" onsubmit="javascript:modalFormSubmitted();">
            <div class="divTable">
                <div class="divTableRow">
                    <div class="divTableHead">New Customer</div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">First Name:</div>
                    <div class="divTableCellField"><input name="firstName" type="text" placeholder="First Name" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Last Name:</div>
                    <div class="divTableCellField"><input name="lastName" type="text" placeholder="Last Name" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Email:</div>
                    <div class="divTableCellField"><input name="emailAddress" type="text" placeholder="Email" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Phone:</div>
                    <div class="divTableCellField">
                        <input name="phoneNumberList[]" id="phoneField" type="text" placeholder="Phone" />
                        <div class="phonePlus" onClick="javasctipt:addNumberInput(this)"></div><br/>
                    </div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">House Number:</div>
                    <div class="divTableCellField"><input name="houseNumber" type="text" placeholder="House Number" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Street Name:</div>
                    <div class="divTableCellField"><input name="street" type="text" placeholder="Street Name" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">City:</div>
                    <div class="divTableCellField"><input name="city" type="text" placeholder="City" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Province:</div>
                    <div class="divTableCellField"><input name="province" type="text" placeholder="Province" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Postal Code:</div>
                    <div class="divTableCellField"><input name="postalCode" type="text" placeholder="Postal Code" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Country:</div>
                    <div class="divTableCellField"><input name="country" type="text" placeholder="Country" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Company:</div>
                    <div class="divTableCellField"><input name="companyName" type="text" placeholder="Company" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Position:</div>
                    <div class="divTableCellField"><input name="position" type="text" placeholder="Position" /></div>
                </div>
				<div class="divTableRow">
                    <div class="divTableCellLabel">Notes:</div>
                    <div class="divTableCellField"><input name="notes" type="text" placeholder="Some notes." /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellSubmit">
                        <input name="action" type="hidden" value="add" /> <input type="submit" class="saveButton" value="Save" />
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
                
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
