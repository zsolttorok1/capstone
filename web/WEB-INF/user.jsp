<h1 class="bodyheaderc">USER</h1>

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

    <div class="rowWrapper">
        <div class="addRow" id="myBtn">
            <img class="addPlus" src="res/plus.png" />
        </div>
    </div>

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
 
            <form method="post" action="user">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="userName" value="${user.userName}">
                <button type="submit" class="deleteButton" name="action" value="" onclick="closeForm(this);">
            </form>
                
        </div>    
    </c:forEach>
</div>

<div id="myModal" class="modal">
    <div class="modal-content">
        <form method="post" action="user">
            <div class="divTable">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableHead">New User</div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Username:</div>
                        <div class="divTableCell"><input name="userNameAdd" type="text" placeholder="Username" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Password:</div>
                        <div class="divTableCell"><input name="password" type="password" placeholder="Password" /></div>
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
                        <div class="divTableCell">Role:</div>
                        <div class="divTableCell"><input name="role" type="text" placeholder="Role" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Pay Rate:</div>
                        <div class="divTableCell"><input name="hourlyRate" type="text" placeholder="Pay Rate" /></div>
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