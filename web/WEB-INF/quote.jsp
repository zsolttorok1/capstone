        
        <h1 class="bodyheaderc">QUOTE</h1>
        
        <div class="rowHeader">
            <div class="rowitemHeader">Name</div>
            <div class="rowitemHeader">Email</div>
            <div class="rowitemHeader">Description</div>

            
        </div>
        <c:forEach var="quote" items="${quoteList}">
            <div class="rowWrapper">
                <div class="row">
                    <div class="rowitem" name="quoteName">${quote.name}</div>
                    <div class="rowitem" name="email">${quote.email}</div>
                    <div class="rowitem" name="description">${quote.description}</div>
                </div>
                <div class="listOptions">
                    <div class="listButton">
                        <form method="post" action="quote">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="quoteName" value="${quote.name}">
                            <input type="submit" value="View">
                        </form>
                    </div>
                    <div class="listButton">
                        <form method="post" action="quote">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="quoteName" value="${quote.name}">
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
        
        
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
