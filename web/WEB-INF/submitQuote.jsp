
<div class="contentWrapperSubmit">
    <div class="contentSubmit">
        <h1>Submit a Quote</h1>

        <form  action="submitQuote" name= "submitQuote" method="post">

            <label for="name">Full Name</label><br>
            <input type="text" id="name" class="contentBodyInputBig" name="name"/><br>
            <label for="email">Email Address</label><br>
            <input type="text" id="email" class="contentBodyInputBig" name="email"/><br>       
            <label for="desc">Description</label><br>
            <textarea class="contentBodyInputBig" name="description" id="desc" rows="5"></textarea><br>

            <input type ="submit" value="Submit" >
            <input type="hidden" name="action" value="submit">
        </form>
    </div>
</div>
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
