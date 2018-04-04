 <h1 class="bodyheaderc">BACKUP</h1>
<br/>

<div class="listWrapper">
    <form method="post" action="backup">
            <input type="hidden" name="action" value="toggle">
            <input type="submit" value="Turn ${backupServiceState} Backupping Service">
    </form>
</div>
   
<div class="listWrapper">
    <div class="rowWrapper">
        <div class="rowHeader">
            <div class="rowitemHeader">Restore a backup from date:</div>
        </div>
    </div>

    <c:forEach var="backupFile" items="${backupFileList}">
        <div class="rowWrapper">
            <div class="row">
                <div class="rowitem">${backupFile}</div>
                
                <form method="post" action="backup">
                    <input type="hidden" name="action" value="restore">
                    <input type="hidden" name="backupFileName" value="${backupFile}">
                    <input type="submit" class="rowButton" value="">
                </form>
            </div>
        
        </div>
    </c:forEach>
</div>
<br/>
<br/>
         
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
