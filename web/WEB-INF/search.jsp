<h1 class="bodyheaderc">Search Results for: ${keyword}</h1>

<center>
    <form method="post" action="search">
        Search for: <input type="text" name="keyword" placeholder="Search..." value="${keyword}" />
        <input type="submit" value="Search">
    </form>
</center>
         
<c:if test="${!empty itemList}">     
    <div class="listWrapper">

        <div class="rowWrapper">   
            <div class="rowHeaderSearchBanner">
                <div>Item Inventory Results</div>
            </div>
        </div>

        <div class="rowWrapper">
            <div class="rowHeader">
                <div class="rowitemHeader">Item Name</div>
                <div class="rowitemHeader">Description</div>
                <div class="rowitemHeader">Category</div>
                <div class="rowitemHeader">Quantity</div>
            </div>
        </div>

        <c:forEach var="item" items="${itemList}">
            <div class="rowWrapper">
                <div class="row">
                    <div class="rowitem" name="name">${item.itemName}</div>
                    <div class="rowitem" name="description">${item.description}</div>
                    <div class="rowitem" name="category">${item.category}</div>
                    <div class="rowitem" name="quantity">${item.quantity}
                        <c:if test="${item.inventoryQuantity > 0}">
                            (allocated: ${item.inventoryQuantity})
                        </c:if> 
                    </div>

                    <form method="post" action="viewItem">
                        <input type="hidden" name="action" value="view">
                        <input type="hidden" name="selectedItemName" value="${item.itemName}">
                        <input type="submit" class="rowButton" value="">
                    </form>
                </div>

                <form method="post" action="item">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="selectedItemName" value="${item.itemName}">
                    <button type="submit" class="deleteButton" name="action" value="" onclick="closeForm(this);">
                </form>

            </div>
        </c:forEach>
    </div>
</c:if>
    
<c:if test="${!empty userList}">
    <div class="listWrapper">

        <div class="rowWrapper">
            <div class="rowHeaderSearchBanner">
                <div>Employee Results</div>
            </div>
        </div>

        <div class="rowWrapper">
            <div class="rowHeader">
                <div class="rowitemHeader">Username</div>
                <div class="rowitemHeaderEmail">Email</div>
                <div class="rowitemHeader">Phone</div>
                <div class="rowitemHeader">First Name</div>
                <div class="rowitemHeader">Last Name</div>
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
</c:if>
   
<c:if test="${!empty customerList}">
    <div class="listWrapper">

        <div class="rowWrapper">   
            <div class="rowHeaderSearchBanner">
                <div>Customer Results</div>
            </div>
        </div>

        <div class="rowWrapper">
            <div class="rowHeader">
                <div class="rowitemHeader">Name</div>
                <div class="rowitemHeader">Company</div>
                <div class="rowitemHeader">Phone</div>
                <div class="rowitemHeader">Email</div>
                <div class="rowitemHeader">Address</div>
            </div>
        </div>

        <c:forEach var="customer" items="${customerList}">
            <div class="rowWrapper">
                <div class="row">
                    <div class="rowitem" name="name">${customer.firstName} ${customer.lastName}</div>
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
</c:if>

        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
