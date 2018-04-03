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
          
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
