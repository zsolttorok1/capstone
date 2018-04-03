        
        <h1 class="bodyheaderc">Search Results for: ${keyword}</h1>
        <p class="center">${message}</p>
        
        <form method="post" action="search">
            Search for: <input type="text" name="keyword" placeholder="Search..." value="${keyword}" /><br/>
            <input type="submit" value="Search">
        </form>
        
            
            <div class="resultContainer">
                <div class="rowHeaderSearchBanner">
                    <div>Item Inventory Results</div>
                </div>
                <div class="rowHeaderSearch">
                    <div class="rowitemHeaderSearch">Item Name</div>
                    <div class="rowitemHeaderSearch">Category</div>
                </div>
                <c:forEach var="item" items="${itemList}">
                    <div class="rowWrapper">
                        <div class="rowSearch">
                            <div class="rowitemSearch">
                                ${item.itemName}<br/>
                            </div>
                            <div class="rowitemSearch">
                                ${item.category}<br/>
                            </div>
                        </div>
                        <div class="listOptionsSearch">
                            <div class="listButton">
                                <form method="post" action="viewItem">
                                    <input type="hidden" name="action" value="view">
                                    <input type="hidden" name="selectedItemName" value="${item.itemName}">
                                    <input type="submit" value="View">
                                </form>
                            </div>
                            <div class="listButton">
                                <form method="post" action="item">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="selectedItemName" value="${item.itemName}">
                                    <input type="submit" value="Delete">
                                </form>
                            </div>
                            <div class="listButton">
                                <input type="hidden" name="action" value="cancel">
                                <input type="button" value="Cancel" id="cancelbox">
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="resultContainer">
                <div class="rowHeaderSearchBanner">
                    <div>Employee Results</div>
                </div>
                <div class="rowHeaderSearch">
                    <div class="rowitemHeaderSearch">Name</div>
                    <div class="rowitemHeaderSearch">Role</div>
                </div>
                <c:forEach var="user" items="${userList}">
                   <div class="rowWrapper">
                        <div class="rowSearch">
                            <div class="rowitemSearch">
                                ${user.firstName} ${user.lastName}<br/>
                            </div>
                            <div class="rowitemSearch">
                                ${user.role}<br/>
                            </div>
                        </div>
                        <div class="listOptionsSearch">
                            <div class="listButton">
                                <form method="post" action="viewUser">
                                    <input type="hidden" name="action" value="view">
                                    <input type="hidden" name="userName" value="${user.userName}">
                                    <input type="submit" value="View">
                                </form>
                            </div>
                            <div class="listButton">
                                <form method="post" action="user">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="userName" value="${user.userName}">
                                    <input type="submit" value="Delete">
                                </form>
                            </div>
                            <div class="listButton">
                                <input type="hidden" name="action" value="cancel">
                                <input type="button" value="Cancel" id="cancelbox">
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
            <div class="resultContainer">
                <div class="rowHeaderSearchBanner">
                    <div>Customer Results</div>
                </div>
                <div class="rowHeaderSearch">
                    <div class="rowitemHeaderSearch">Name</div>
                    <div class="rowitemHeaderSearch">Company</div>
                </div>
                <c:forEach var="customer" items="${customerList}">
                   <div class="rowWrapper">
                        <div class="rowSearch">
                            <div class="rowitemSearch">
                                ${customer.firstName} ${customer.lastName}<br/>
                            </div>
                            <div class="rowitemSearch">
                                ${customer.companyName}<br/>
                            </div>
                        </div>
                        <div class="listOptionsSearch">
                            <div class="listButton">
                                <form method="post" action="viewCustomer">
                                    <input type="hidden" name="action" value="view">
                                    <input type="hidden" name="customerId" value="${customer.customerId}">
                                    <input type="submit" value="View">
                                </form>
                            </div>
                            <div class="listButton">
                                <form method="post" action="customer">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="customerId" value="${customer.customerId}">
                                    <input type="submit" value="Delete">
                                </form>
                            </div>
                            <div class="listButton">
                                <input type="hidden" name="action" value="cancel">
                                <input type="button" value="Cancel" id="cancelbox">
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
