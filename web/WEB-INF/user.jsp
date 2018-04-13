<h1 class="bodyheaderc">EMPLOYEE LIST</h1>

<div class="listWrapper">
    <div class="rowWrapper">
        <div class="rowHeader">
            <div class="rowitemHeader">Username</div>
            <div class="rowitemHeaderEmail">Email</div>
            <div class="rowitemHeader">Phone</div>
            <div class="rowitemHeader">First Name</div>
            <div class="rowitemHeader">Last Name</div>
        </div>
    </div>

    <c:if test="${role eq 'owner' or role eq 'manager'}">
        <div class="rowWrapper">
            <div class="addRow" id="myBtn">
                <img class="addPlus" src="res/plus.png" />
            </div>
        </div>
    </c:if>

    <c:forEach var="user" items="${userList}">
        <div class="rowWrapper">
            <div class="row">   
                <div class="rowitem" name="username">${user.userName}</div>
                <div class="rowitemEmail" name="email">${user.email}</div>
                <div class="rowitem" name="phone">
                    <c:forEach var="phone" items="${user.phoneNumberList}">
                        ${phone}<br>
                    </c:forEach>
                </div>
                <div class="rowitem" name="firstname">${user.firstName}</div>
                <div class="rowitem" name="lastname">${user.lastName}</div>

                <form method="post" action="viewUser">
                    <input type="hidden" name="action" value="view">
                    <input type="hidden" name="userName" value="${user.userName}">
                    <input type="submit" class="rowButton" value="">
                </form>
            </div>
 
            <c:if test="${role eq 'owner' or role eq 'manager'}">
                <form method="post" action="user">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="userName" value="${user.userName}">
                    <button type="button" class="deleteButton" name="action" value="" onclick="closeForm(this);">
                </form>
            </c:if>
                
        </div>
    </c:forEach>
</div>

<div id="myModal" class="modal newUser">
    <div class="modal-content">
        <form method="post" action="user" onsubmit="javascript:modalFormSubmitted();">
            <div class="divTable">
                <div class="divTableRow">
                    <div class="divTableHead">New User</div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Username:</div>
                    <div class="divTableCellField"><input name="userName" type="text" placeholder="Username" value="${newUser.userName}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Password:</div>
                    <div class="divTableCellField"><input name="password" type="password" placeholder="Password" /></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">First Name:</div>
                    <div class="divTableCellField"><input name="firstName" type="text" placeholder="First Name" value="${newUser.firstName}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Last Name:</div>
                    <div class="divTableCellField"><input name="lastName" type="text" placeholder="Last Name" value="${newUser.lastName}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Email:</div>
                    <div class="divTableCellField"><input name="emailAddress" type="text" placeholder="Email" value="${newUser.email}"/></div>
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
                    <div class="divTableCellField"><input name="street" type="text" placeholder="Street Name" value="${newUser.street}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">City:</div>
                    <div class="divTableCellField"><input name="city" type="text" placeholder="City" value="${newUser.city}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Province:</div>
                    <div class="divTableCellField"><input name="province" type="text" placeholder="Province" value="${newUser.province}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Postal Code:</div>
                    <div class="divTableCellField"><input name="postalCode" type="text" placeholder="Postal Code" value="${newUser.postalCode}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Country:</div>
                    <div class="divTableCellField"><input name="country" type="text" placeholder="Country" value="${newUser.country}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Role:</div>
                    <div class="divTableCellField">
                        <select name="role">
                            <option value="employee">employee</option>
                            <option value="manager">manager</option>
                            <option value="owner">owner</option>
                        </select>
                    </div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Pay Rate:</div>
                    <div class="divTableCellField"><input name="hourlyRate" type="text" placeholder="$" value="${hourlyRate}"/></div>
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
