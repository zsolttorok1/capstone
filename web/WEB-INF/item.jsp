<h1 class="bodyheaderc">ITEM LIST</h1>

<div class="listWrapper">
    <div class="rowWrapper">
        <div class="rowHeader">
            <div class="rowitemHeader">Item Name</div>
            <div class="rowitemHeader">Description</div>
            <div class="rowitemHeader">Category</div>
            <div class="rowitemHeader">Quantity in Inventory</div>
        </div>
    </div>
    
    <c:if test="${role eq 'owner' or role eq 'manager'}">
        <div class="rowWrapper">
            <div class="addRow" id="myBtn">
                <img class="addPlus" src="res/plus.png" />
            </div>
        </div>
    </c:if>
    
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
                    <input type="hidden" name="itemName" value="${item.itemName}">
                    <input type="submit" class="rowButton" value="">
                </form>
            </div>
                    
            <c:if test="${role eq 'owner' or role eq 'manager'}">
                <form method="post" action="item">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="itemName" value="${item.itemName}">
                    <button type="button" class="deleteButton" name="action" value="" onclick="closeForm(this);">
                </form>
            </c:if>
            
        </div>
    </c:forEach>
</div>

<div id="myModal" class="modal newUser">
    <div class="modal-content">
        <form method="post" action="item" onsubmit="javascript:modalFormSubmitted();">
            <div class="divTable">
                <div class="divTableRow">
                    <div class="divTableHead">New Item</div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Item Name:</div>
                    <div class="divTableCellField"><input name="itemName" type="text" placeholder="Item Name" value="${newItem.itemName}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Category:</div>
                    <div class="divTableCellField"><input name="category" type="text" placeholder="Category" value="${newItem.category}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Quantity:</div>
                    <div class="divTableCellField"><input name="quantity" type="number" placeholder="Quantity" value="${quantity}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Description:</div>
                    <div class="divTableCellField">
                        <textarea name="description" placeholder="Description">${newItem.description}</textarea>
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
