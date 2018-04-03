<h1 class="bodyheaderc">Jobs</h1>
       
<div class="listWrapper">
    <div class="rowWrapper">
        <div class="rowHeader">
            <div class="rowitemHeader">Job Name</div>
            <div class="rowitemHeader">Customer</div>
            <div class="rowitemHeader">Description</div>
            <div class="rowitemHeader">Status</div>
        </div>
    </div>
       
    <div class="rowWrapper">   
        <div class="addRow" id="myBtn">
            <img class="addPlus" src="res/plus.png" />
        </div>
    </div>
        
        
    <c:forEach var="job" items="${jobList}">
        <div class="rowWrapper">
            <div class="row">
                <div class="rowitem" name="name">${job.jobName}</div>
                <div class="rowitem" name="customer">${job.customer.firstName} ${job.customer.lastName}</div>
                <div class="rowitem" name="description">${job.description}</div>
                <div class="rowitem" name="status">${job.status}</div>
                
                <form method="post" action="viewJob">
                    <input type="hidden" name="action" value="view">
                    <input type="hidden" name="selectedJobName" value="${job.jobName}">
                    <input type="submit" class="rowButton" value="">
                </form>
            </div>
                    
            <form method="post" action="job">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="selectedJobName" value="${job.jobName}">
                <button type="submit" class="deleteButton" name="action" value="" onclick="closeForm(this);">
            </form>
                        
        </div>
    </c:forEach>
</div>
                 
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
