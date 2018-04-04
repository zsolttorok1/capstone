   
        <h1 class="bodyheaderc">ITEM VIEW FOR: ${itemName}</h1>

        <div class="viewcontent">
            <form method="post" action="viewItem">
                <div class="contentInfo">
                    <input type="hidden" name="name" value="${itemName}" />
                    <label for="category">Category:</label><br>
                    <input type="text" id="category" class="contentBodyInput" placeholder="Category" name="categoryEdit" value="${category}" /><br>
                    <label for="desc">Description:</label><br>
                    <input type="text" id="desc" class="contentBodyInput" placeholder="Description" name="descriptionEdit" value="${description}" /><br>
                    <label for="quant">Quantity:</label><br>
                    <input type="number" class="contentBodyInput" placeholder="Quantity" name="quantityEdit" id="quant" value="${quantity}" />
                    <p class="contentHeader">Additional Notes</p>
                    <p class="contentBody">${notes}</p>
                </div>
                <input type="hidden" name="action" value="save">
                <input type="submit" value="Save Changes">
            </form>
        </div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
