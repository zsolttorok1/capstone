package businesslogic;

import dataaccess.ReportBroker;
import domainmodel.Item;
import domainmodel.Job;
import java.text.SimpleDateFormat;
import domainmodel.Report;
import domainmodel.User;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.text.WordUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportService {

    public String generateFromJob(Job job, String description) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd");            
        String status = "";
        try {
            String phoneNumber = "";
            List<Long> phoneNum = job.getCustomer().getPhoneNumberList();
            for (int i = 0; phoneNum.size() > i; i++) {

                if (phoneNum.size() - 1 == i) {
                    phoneNumber = phoneNumber + phoneNum.get(i);
                } else {
                    phoneNumber = phoneNumber + phoneNum.get(i) + ",";
                }
            }
    
            PDDocument doc = new PDDocument(); // create pdf doc
            PDPage page1 = new PDPage();
            doc.addPage(page1); // adds page to pdf
            PDPageContentStream content = new PDPageContentStream(doc, page1);

            
            //SimpleDateFormat timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").getCalendar();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 12);
            content.newLineAtOffset(320, 760);
            content.showText("Report Date: "+ dateFormat.format(new Date().getTime()));
            content.endText();
            

            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 12);
            //moves rge next line displayed to this spot
            content.newLineAtOffset(25, 750);

            //displays this line to screen
            //content.showText("Job Name");
            content.showText("Job Name: " + job.getJobName());
            //close text
            content.endText();
            
            
            content.beginText();
            content.setFont(PDType1Font.COURIER, 10);
            content.newLineAtOffset(25, 740);
            content.showText(job.getHouseNumber()+", "+ job.getStreet()+ ", "+job.getCity());
            content.endText();
            
                        
            content.beginText();
            content.setFont(PDType1Font.COURIER, 10);
            content.newLineAtOffset(25, 730);
            content.showText(job.getPostalCode()+", "+job.getProvince()+", "+ job.getCountry());
            content.endText();
            
            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 12);
            content.newLineAtOffset(25, 700);
            //content.showText("Customer Name");
            content.showText("Customer: " + job.getCustomer().getFirstName() + " " + job.getCustomer().getLastName());
            content.endText();

            //Customer phone number
            content.beginText();
            content.setFont(PDType1Font.COURIER, 10);
            content.newLineAtOffset(25, 690);
            //displays this line to screen
            content.showText(phoneNumber);
            //close text
            content.endText();

            //Customer address
            content.beginText();
            content.setFont(PDType1Font.COURIER, 10);
            content.newLineAtOffset(25, 680);
            //displays this line to screen
            content.showText(job.getCustomer().getHouseNumber() + " " + job.getCustomer().getStreet());
            //close text
            content.endText();

            //Customer province
            content.beginText();
            content.setFont(PDType1Font.COURIER, 10);
            content.newLineAtOffset(25, 670);
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
            content.setFont(PDType1Font.COURIER_BOLD, 10);
            content.newLineAtOffset(25, 620);
            //content.showText("Date Started");
            content.showText("Date Started: " + dateFormatter.format(job.getDateStarted()) + "");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 10);
            content.newLineAtOffset(25, 600);
            //content.showText("Date Finished");

            content.showText("Date Finished: " + dateFormatter.format(job.getDateFinished()) + "");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 10);
            content.newLineAtOffset(25, 570);
            //content.showText("Job Description:");
            content.showText("Job Description: ");
            content.endText();
        
            String[] worT = null;
            int lineoffSet1 = 560;
            worT = WordUtils.wrap(job.getDescription(), 90).split("\\r?\\n");
            for (int j = 0; worT.length > j; j++) {
                content.beginText();
                content.setFont(PDType1Font.COURIER, 10);
                lineoffSet1 = lineoffSet1 - 15;
                content.newLineAtOffset(25, lineoffSet1);
                //content.showText("Job Description:");
                content.showText(worT[j]);
                content.endText();
            }

            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 10);
            content.newLineAtOffset(25, 470);
            content.showText("Report Description:");
            content.endText();
            
            String[] wrT = null;
            int lineoffSet = 460;
            wrT = WordUtils.wrap(description, 90).split("\\r?\\n");
            for (int j = 0; wrT.length > j; j++) {
                content.beginText();
                content.setFont(PDType1Font.COURIER, 10);
                lineoffSet = lineoffSet - 15;
                content.newLineAtOffset(25, lineoffSet);
                //content.showText("Job Description:");
                content.showText(wrT[j]);
                content.endText();
            }
            //Begin new text
            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 10);
            content.newLineAtOffset(25, 300);
            //content.showText("Job Status!!!!!!");
            content.showText("Job Status...........: " + job.getStatus());

            content.endText();

            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 10);
            content.newLineAtOffset(25, 320);
            //content.showText("Job Balance!!!!!!");
            content.showText("Job Balance..........: " + job.getBalance());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 10);
            content.newLineAtOffset(25, 230);
            content.showText("Users on the job:");
            content.endText();

            //String to hold all users in a forloop
            String users = "";
            List<User> userList = job.getUserList();
            int lineOffset = 210;
            for (int j = 0; userList.size() > j; j++) {
                content.beginText();
                content.setFont(PDType1Font.COURIER, 10);
                if (userList.size() - 1 == j) {

                    users = (userList.get(j).getFirstName() + " " + userList.get(j).getLastName());
                    users = String.format("%-20s",users).replaceAll("\\s",".");
                    users= users +" "+ userList.get(j).getHours();
                    content.newLineAtOffset(25, lineOffset);
                    content.showText(users);
                } else {
                    content.newLineAtOffset(25, lineOffset);
                    users = userList.get(j).getFirstName() + " " + userList.get(j).getLastName();
                    users = String.format("%-20s",users).replaceAll("\\s",".");
                    users= users +" "+ userList.get(j).getHours();
                    content.showText(users);
                    lineOffset = lineOffset - 15;
                }
                content.endText();
            }

            content.beginText();
            content.setFont(PDType1Font.COURIER_BOLD, 10);
            content.newLineAtOffset(300, 230);
            content.showText("Items on the job:");
            content.endText();
            
            
            String item = "";
            List<Item> itemList = job.getItemList();
            int lineOffset1 = 210;
            for (int j = 0; itemList.size() > j; j++) {
                content.beginText();
                content.setFont(PDType1Font.COURIER, 10);
                if (itemList.size() - 1 == j) {

                    item = itemList.get(j).getItemName();
                    item = String.format("%-30s",item).replaceAll("\\s",".");
                    item= item +" "+ itemList.get(j).getQuantity();
                    content.newLineAtOffset(300, lineOffset1);
                    content.showText(item);
                } else {
                    content.newLineAtOffset(300, lineOffset1);
                    item = itemList.get(j).getItemName();
                    item = String.format("%-30s",item).replaceAll("\\s",".");
                    item= item +" "+ itemList.get(j).getQuantity();
                    content.showText(item);
                    lineOffset1 = lineOffset1 - 15;
                }
                content.endText();
            }

            //close conetent Stream
            content.close();
            
            ReportBroker reportBroker = ReportBroker.getInstance();
            status = reportBroker.generateByJobName(job.getJobName());
            
            if (status != null & !status.contains("error")) {
                doc.save("c:/cap/reports/" + status + ".pdf"); // saves repoprt filename
                status = "Report Created";
            }
            
            doc.close(); //close file
            
            } catch (IOException ex) {
                 Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
