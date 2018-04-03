<h1 class="bodyheaderc">ITEM</h1>
        
<div class="listWrapper">
    <div class="rowWrapper">
        <div class="rowHeader">
            <div class="rowitemHeader">Item Name</div>
            <div class="rowitemHeader">Description</div>
            <div class="rowitemHeader">Category</div>
            <div class="rowitemHeader">Quantity</div>
        </div>
    </div>
        
    <div class="rowWrapper">   
        <div class="addRow" id="myBtn">
            <img class="addPlus" src="res/plus.png" />
        </div>
    </div>
        
    <c:forEach var="item" items="${itemList}">
        <div class="rowWrapper">
            <div class="row">
                <div class="rowitem" name="name">${item.itemName}</div>
                <div class="rowitem" name="description">${item.description}</div>
                <div class="rowitem" name="category">${item.category}</div>
                <div class="rowitem" name="quantity">${item.quantity}</div>
                
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

 <div id="myModal" class="modal">
    <div class="modal-content">
        <form method="post" action="item">
            <div class="divTable">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableHead">
                            New Item
                        </div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Item Name:</div>
                        <div class="divTableCell"><input name="name" type="text" placeholder="Item Name" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Description:</div>
                        <div class="divTableCell"><input name="description" type="text" placeholder="Description" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Category:</div>
                        <div class="divTableCell"><input name="category" type="text" placeholder="Category" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Quantity:</div>
                        <div class="divTableCell"><input name="quantity" type="number" placeholder="Quantity" /></div>
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
