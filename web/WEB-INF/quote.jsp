<h1 class="bodyheaderc">QUOTE</h1>
       
<div class="listWrapper">
    <div class="rowWrapper">
        <div class="rowHeader">
            <div class="rowitemHeader">Name</div>
            <div class="rowitemHeader">Email</div>
            <div class="rowitemHeader">Description</div>
        </div>
    </div>

    <c:forEach var="quote" items="${quoteList}">
        <div class="rowWrapper">
            <div class="row">
                <div class="rowitem" name="quoteName">${quote.name}</div>
                <div class="rowitem" name="email">${quote.email}</div>
                <div class="rowitem" name="description">${quote.description}</div>
                
                <form method="post" action="quote">
                    <input type="hidden" name="action" value="view">
                    <input type="hidden" name="quoteName" value="${quote.name}">
                    <input type="submit" class="rowButton" value="">
                </form>
            </div>
            
            <form method="post" action="quote">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="quoteName" value="${quote.name}">
                <button type="submit" class="deleteButton" name="action" value="" onclick="closeForm(this);">
            </form>

        </div>
    </c:forEach>
</div>
         
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
