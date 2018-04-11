   
        <h1 class="bodyheaderc">ITEM VIEW FOR: ${item.itemName}</h1>

        <div class="viewcontent">
            <form method="post" action="viewItem">
                <div class="contentInfo">
                    <input type="hidden" name="itemName" value="${item.itemName}" />
                    <label for="category">Category:</label><br>
                    <input type="text" class="contentBodyInput" placeholder="Category" name="categoty" value="${item.category}" /><br>
                    <label for="desc">Description:</label><br>
                    <input type="text" class="contentBodyInput" placeholder="Description" name="description" value="${item.description}" /><br>
                    <label for="quant">Quantity:</label><br>
                    <input type="number" class="contentBodyInput" placeholder="Quantity" name="quantity" value="${item.quantity}" />
                </div>
                <c:if test="${role eq 'owner' or role eq 'manager'}">
                    <input type="hidden" name="action" value="save">
                    <input type="submit" class="saveChangesButton" name="save" value="Save Changes">
                </c:if>
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
