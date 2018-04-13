<h1 class="bodyheaderc">JOB LIST</h1>
       
<div class="listWrapper">
    <div class="rowWrapper">
        <div class="rowHeader">
            <div class="rowitemHeader">Job Name</div>
            <div class="rowitemHeader">Customer</div>
            <div class="rowitemHeader">Date Started</div>
            <div class="rowitemHeader">
                <c:if test="${role eq 'owner'}">Status</c:if>
            </div>
        </div>
    </div>
    
    <c:if test="${role eq 'owner' or role eq 'manager'}">
        <div class="rowWrapper">   
            <div class="addRow" id="myBtn">
                <img class="addPlus" src="res/plus.png" />
            </div>
        </div>
    </c:if>
        
    <c:forEach var="job" items="${jobList}">
        <div class="rowWrapper">
            <div class="row">
                <div class="rowitem" name="name">${job.jobName}</div>
                <div class="rowitem" name="customer">${job.customer.firstName}&nbsp;${job.customer.lastName}</div>
                <div class="rowitem" name="description">${job.dateStarted}</div>
                <div class="rowitem" name="status">
                    <c:if test="${role eq 'owner'}">${job.status}</c:if>
                </div>
                
                <form method="post" action="viewJob">
                    <input type="hidden" name="action" value="view">
                    <input type="hidden" name="jobName" value="${job.jobName}">
                    <input type="submit" class="rowButton" value="">
                </form>
            </div>
            
            <c:if test="${role eq 'owner' or role eq 'manager'}">
                <form method="post" action="job">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="jobName" value="${job.jobName}">
                    <button type="button" class="deleteButton" name="action" value="" onclick="closeForm(this);">
                </form>
            </c:if>    
       
        </div>
    </c:forEach>
</div>

<div id="myModal" class="modal newUser">
    <div class="modal-content">
        <form method="post" action="job" onsubmit="javascript:modalFormSubmitted();">
            <div class="divTable">
                <div class="divTableRow">
                    <div class="divTableHead">New Job</div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Job Name:</div>
                    <div class="divTableCellField"><input name="jobName" type="text" placeholder="Job Name" value="${newJob.jobName}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">House Number:</div>
                    <div class="divTableCellField"><input name="houseNumber" type="text" placeholder="House Number" value="${houseNumber}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Street Name:</div>
                    <div class="divTableCellField"><input name="street" type="text" placeholder="Street Name" value="${newJob.street}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">City:</div>
                    <div class="divTableCellField"><input name="city" type="text" placeholder="City" value="${newJob.city}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Province:</div>
                    <div class="divTableCellField"><input name="province" type="text" placeholder="Province" value="${newJob.province}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Postal Code:</div>
                    <div class="divTableCellField"><input name="postalCode" type="text" placeholder="Postal Code" value="${newJob.postalCode}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Country:</div>
                    <div class="divTableCellField"><input name="country" type="text" placeholder="Country" value="${newJob.country}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Customer:</div>
                    <div class="divTableCellField">
                        <select name="customerId">
                            <c:forEach var="customer" items="${customerList}">
                                <option value="${customer.customerId}">${customer.firstName} ${customer.lastName} - ${customer.companyName}</option>
                            </c:forEach>
                        </select> 
                    </div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Description::</div>
                    <div class="divTableCellField">
                        <textarea name="description" placeholder="description">${newJob.description}</textarea>
                    </div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Date Started:</div>
                    <div class="divTableCellField"><input name="dateStarted" type="date" placeholder="yyyy-mm-dd" value="${newJob.dateStarted}"/></div>
                </div>
                <div class="divTableRow">
                    <div class="divTableCellLabel">Date Finished:</div>
                    <div class="divTableCellField"><input name="dateFinished" type="date" placeholder="yyyy-mm-dd" value="${newJob.dateFinished}"/></div>
                </div>
                <c:if test="${role eq 'owner'}">
                    <div class="divTableRow">
                        <div class="divTableCellLabel">Balance:</div>
                        <div class="divTableCellField"><input name="balance" type="number" placeholder="0" value="${balance}"/></div>
                    </div>
                    <div class="divTableRow">
                        <div class="divTableCellLabel">Status:</div>
                        <div class="divTableCellField"><input name="jobStatus" type="text" placeholder="unpaid" value="${newJob.status}"/></div>
                    </div>
                </c:if>
                <c:if test="${role eq 'manager'}">
                    <input name="balance" type="hidden" value="0"/>
                    <input name="jobStatus" type="hidden" value="unpaid"/>
                </c:if>
                
                <div class="divTableRow">
                    <div class="divTableCellSubmit">
                        <input name="action" type="hidden" value="add" /> <input type="submit" class="saveButton" value="Save" />
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
                 
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
