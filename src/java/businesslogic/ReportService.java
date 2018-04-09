package businesslogic;

import dataaccess.ReportBroker;
import domainmodel.Item;
import domainmodel.Job;
import domainmodel.Report;
import domainmodel.User;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportService {

    public String generateFromJob(Job job, String description) {
         SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd");            
            String status = "";
            // request.setAttribute("description", report.getDescription());
            // request.setAttribute("dateCreated", report.getDateCreated());
            // String pdfPath= report.getPDFFilePath());

            //make job object(by jobname)
            // String selectedJobName = request.getParameter("JobName");
            //String pdfName = request.getParameter("pdfName");
            //job.setJobName(selectedJobName);

            //String to hold all phone numbbers in a forloop
            //String phoneNum;
            String phoneNumber = "";
            List<Long> phoneNum = job.getCustomer().getPhoneNumberList();
            for (int i = 0; phoneNum.size() > i; i++) {

                if (phoneNum.size() - 1 == i) {

                    phoneNumber = phoneNumber + phoneNum.get(i);
                } else {
                    phoneNumber = phoneNumber + phoneNum.get(i) + ",";

                }
            }
            /*
            String users = "";
            List<User> userList = job.getUserList();
            int lineOffset = 400;
            for (int j = 0; userList.size() > j; j++) {

                if (userList.size() - 1 == j) {

                    users = users + (userList.get(j).getFirstName() + " " + userList.get(j).getLastName());
                } else {

                    users = users + userList.get(j).getFirstName() + " " + userList.get(j).getLastName() + ",";
                    
                }
            }
             */

            //String to hold all items in a forloop
            /*
            String item = "";
            List<Item> itemList = job.getItemList();
            for (int j = 0; itemList.size() > j; j++) {

                if (itemList.size() - 1 == j) {

                    item = item + (itemList.get(j).getItemName() + "( " + itemList.get(j).getQuantity() + ")");
                } else {
                    item = item + itemList.get(j).getItemName() + "( " + itemList.get(j).getQuantity() + ")" + ",";

                }
            }
             */
            
            try {
            PDDocument doc = new PDDocument(); // create pdf doc
            PDPage page1 = new PDPage();
            doc.addPage(page1); // adds page to pdf
            PDPageContentStream content = new PDPageContentStream(doc, page1);

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 25);
            //moves rge next line displayed to this spot
            content.newLineAtOffset(25, 740);

            //displays this line to screen
            //content.showText("Job Name");
            content.showText("Job Name: " + job.getJobName());
            //close text
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 25);
            content.newLineAtOffset(25, 710);
            //content.showText("Customer Name");
            content.showText("Customer: " + job.getCustomer().getFirstName() + " " + job.getCustomer().getLastName());
            content.endText();

            //Customer phone number
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(25, 690);
            //displays this line to screen
            content.showText(phoneNumber);
            //close text
            content.endText();

            //Customer address
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(25, 670);
            //displays this line to screen
            content.showText(job.getCustomer().getHouseNumber() + " " + job.getCustomer().getStreet());
            //close text
            content.endText();

            //Customer province
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(25, 650);
            //displays this line to screen
            content.showText(job.getCustomer().getProvince() + " " + job.getCustomer().getCountry());
            //close text
            content.endText();

            //make a soild line
            content.setLineWidth(0.5f);
            content.moveTo(25, 640);
            content.lineTo(570f, 640f);
            content.closeAndStroke();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(25, 620);
            //content.showText("Date Started");
            content.showText("Date Started: " + dateFormatter.format(job.getDateStarted()) + "");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(25, 600);
            //content.showText("Date Finished");

            content.showText("Date Finished: " + dateFormatter.format(job.getDateFinished()) + "");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(25, 510);
            //content.showText("Job Description:");
            content.showText("Job Descriotion: " + job.getDescription());
            content.endText();

            //Begin new text
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(400, 620);
            //content.showText("Job Status!!!!!!");
            content.showText("Job Status: " + job.getStatus());

            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(400, 600);
            //content.showText("Job Balance!!!!!!");
            content.showText("Job Balance: " + job.getBalance());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(25, 420);
            content.showText("Users on the job:");
            content.endText();

            //String to hold all users in a forloop
            String users = "";
            List<User> userList = job.getUserList();
            int lineOffset = 400;
            for (int j = 0; userList.size() > j; j++) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 20);
                if (userList.size() - 1 == j) {
                    users = (userList.get(j).getFirstName() + " " + userList.get(j).getLastName());
                    content.newLineAtOffset(25, lineOffset);
                    content.showText(users);
                } else {
                    content.newLineAtOffset(25, lineOffset);
                    users = userList.get(j).getFirstName() + " " + userList.get(j).getLastName();
                    content.showText(users);
                    lineOffset = lineOffset - 20;
                }
                content.endText();
            }

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 20);
            content.newLineAtOffset(300, 420);
            content.showText("Items on the job:");
            content.endText();
             
            String item = "";
            List<Item> itemList = job.getItemList();
            int lineOffset1 = 400;
            for (int j = 0; itemList.size() > j; j++) {
                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 20);
                if (itemList.size() - 1 == j) {

                    item = itemList.get(j).getItemName() + "(" + itemList.get(j).getQuantity() + ")";
                    content.newLineAtOffset(300, lineOffset1);
                    content.showText(item);
                } else {
                    content.newLineAtOffset(300, lineOffset1);
                    item = itemList.get(j).getItemName() + "(" + itemList.get(j).getQuantity() + ")";
                    content.showText(item);
                    lineOffset1 = lineOffset1 - 20;
                }
                content.endText();
            }
            
            //close conetent Stream
            content.close();
            
            ReportBroker reportBroker = ReportBroker.getInstance();
            status = reportBroker.generateByJobName(job.getJobName());
            
            if (status != null & !status.contains("error")) {
                doc.save("c:/cap/reports/" + status + ".pdf");
            }

//            doc.save("c:/cap/reports/report.pdf"); // saves report filename
            doc.close(); //close file
            
            } catch (IOException ex) {
                 Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            System.out.println("your file created in : " + System.getProperty("user.dir"));
        return status;
    }
    
    public List<Report> getByJobName(String jobName) {        
        return  ReportBroker.getInstance().getByJobName(jobName);
    }
    
    public Report getById (String reportId) {
        int intReportId;
                
        try {
            intReportId = Integer.parseInt(reportId);
        }
        catch (NumberFormatException ex) {
            intReportId = 1;
        }
        
        return ReportBroker.getInstance().getById(intReportId);
    }
}
