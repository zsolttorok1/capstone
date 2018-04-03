<div class="listWrapper">  
        <h1 class="bodyheaderc">BACKUP</h1>
<!--        <p class="center"></p>-->
        <br/>
        
        <c:forEach var="backupFile" items="${backupFileList}">
            <form method="post" action="backup">
                <input type="hidden" name="action" value="restore">
                <input type="hidden" name="backupFileName" value="${backupFile}">
                <input type="submit" value="Restore from: ${backupFile}">
            </form>
        </c:forEach>
         -------<br/>
        
        
        
        <form method="post" action="backup">
            <input type="hidden" name="action" value="start">
            <input type="submit" value="Turn on Backupping Service">
        </form>
        
        <form method="post" action="backup">
            <input type="hidden" name="action" value="stop">
            <input type="submit" value="Turn off Backupping Service">
        </form>
</div>
         
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
