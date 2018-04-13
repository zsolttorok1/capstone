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
                <button type="button" class="deleteButton" name="action" value="" onclick="closeForm(this);">
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
                    <div class="divTableCellField"><input name="firstName" type="text" placeholder="First Name" value="${newCustomer.firstName}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Last Name:</div>
                    <div class="divTableCellField"><input name="lastName" type="text" placeholder="Last Name" value="${newCustomer.lastName}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Email:</div>
                    <div class="divTableCellField"><input name="emailAddress" type="text" placeholder="Email" value="${newCustomer.email}"/></div>
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
                    <div class="divTableCellField"><input name="houseNumber" type="text" placeholder="House Number" value="${houseNumber}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Street Name:</div>
                    <div class="divTableCellField"><input name="street" type="text" placeholder="Street Name" value="${newCustomer.street}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">City:</div>
                    <div class="divTableCellField"><input name="city" type="text" placeholder="City" value="${newCustomer.city}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Province:</div>
                    <div class="divTableCellField"><input name="province" type="text" placeholder="Province" value="${newCustomer.province}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Postal Code:</div>
                    <div class="divTableCellField"><input name="postalCode" type="text" placeholder="Postal Code" value="${newCustomer.postalCode}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Country:</div>
                    <div class="divTableCellField"><input name="country" type="text" placeholder="Country" value="${newCustomer.country}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Company:</div>
                    <div class="divTableCellField"><input name="companyName" type="text" placeholder="Company" value="${newCustomer.companyName}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Position:</div>
                    <div class="divTableCellField"><input name="position" type="text" placeholder="Position" value="${newCustomer.position}"/></div>
                </div>
				<div class="divTableRow">
                    <div class="divTableCellLabel">Notes:</div>
                    <div class="divTableCellField">
                        <textarea name="notes" placeholder="Some notes.">${newCustomer.notes}</textarea>
                    </div>
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
