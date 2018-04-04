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
                <input type="hidden" name="jobName" value="${job.jobName}">
                <button type="submit" class="deleteButton" name="action" value="" onclick="closeForm(this);">
            </form>
                        
        </div>
    </c:forEach>
</div>

<div id="myModal" class="modal">
    <div class="modal-content">
        <form method="post" action="job">
            <div class="divTable">
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableHead">New Job<div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Job Name:</div>
                        <div class="divTableCell"><input name="jobName" type="text" placeholder="Job Name" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">House Number:</div>
                        <div class="divTableCell"><input name="houseNumber" type="text" placeholder="House Number" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Street Name:</div>
                        <div class="divTableCell"><input name="street" type="text" placeholder="Street Name" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">City:</div>
                        <div class="divTableCell"><input name="city" type="text" placeholder="City" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Province:</div>
                        <div class="divTableCell"><input name="province" type="text" placeholder="Province" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Postal Code:</div>
                        <div class="divTableCell"><input name="postalCode" type="text" placeholder="Postal Code" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Country:</div>
                        <div class="divTableCell"><input name="country" type="text" placeholder="Country" /></div>
                    </div>       
                    <div class="divTableRow">
                        <div class="divTableCell">Customer:</div>
                        <div class="divTableCell">
                            <!--<input name="customer" type="text" placeholder="Customer" />-->
                            <select name="customerId">
                                <c:forEach var="customer" items="${customerList}">
                                    <option value="${customer.customerId}">${customer.firstName} ${customer.lastName} - ${customer.companyName}</option>
                                </c:forEach>
                            </select> 
                        </div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Description:</div>
                        <div class="divTableCell"><input name="description" type="text" placeholder="description" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Date Started</div>
                        <div class="divTableCell"><input name="dateStarted" type="date" placeholder="yyyy-mm-dd" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Date Finished</div>
                        <div class="divTableCell"><input name="dateFinished" type="date" placeholder="yyyy-mm-dd" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Balance</div>
                        <div class="divTableCell"><input name="balance" type="number" placeholder="0" /></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCell">Status</div>
                        <div class="divTableCell"><input name="jobStatus" type="text" placeholder="unpayed" /></div>
                    </div>
            
                    <div class="divTableRow">
                        <div class="divTableCell"><input name="action" type="hidden" value="add" /> <input type="submit" value="Save" /></div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
                 
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
