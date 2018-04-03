        
        <h1 class="bodyheaderc">Jobs</h1>
       
        <div class="listWrapper">
            <c:forEach var="job" items="${jobList}">
                jobname: ${job.jobName}<br>
                description ${job.description}<br>
                -----------------------------<br>
            </c:forEach>
        </div>    
                 
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
