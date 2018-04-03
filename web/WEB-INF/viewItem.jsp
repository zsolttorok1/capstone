   
        <h1 class="bodyheaderc">ITEM VIEW</h1>

        <div class="viewcontent">
            <div>
                <img class="editIcon" src="res/editIcon.png" title="Edit" id="editIcon" />
            </div>
            <form method="post" action="viewItem">
                <div class="contentInfo">
                    <p class="contentHeader">Name and Category</p>
                    <p>${itemName} (${category})</p>
                    <input type="hidden" name="name" value="${itemName}" />
                    <input type="text" class="contentBodyInput" placeholder="Category" name="categoryEdit" value="${category}" />
                    <p class="contentHeader">Description</p>
                    <p class="contentBody">${description}</p>
                    <textarea cols="100" rows="5" class="contentBodyInput" placeholder="Description" name="descriptionEdit" >${description}</textarea>
                    <p class="contentHeader">Quantity</p>
                    <p class="contentBody">${quantity}</p>
                    <input type="number" class="contentBodyInput" placeholder="Quantity" name="quantityEdit" value="${quantity}" />
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
