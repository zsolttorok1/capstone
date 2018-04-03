
        
        <h1 class="bodyheaderc">View Job</h1>

        <div class="listWrapper">
            jobname: ${job.jobName}<br/>
            -----------------------------<br/>

            <c:forEach var="user" items="${job.userList}">
                username ${user.userName} - ${user.hours} hour(s)<br/>
                -------<br/>
            </c:forEach>
        </div>

                 
        <script src="javascript/sitefunctions.js" type="text/javascript"></script>
    </body>
</html>
